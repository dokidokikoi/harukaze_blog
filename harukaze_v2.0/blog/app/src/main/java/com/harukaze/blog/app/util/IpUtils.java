package com.harukaze.blog.app.util;

import cn.hutool.core.util.StrUtil;
import com.harukaze.blog.app.entity.UserEntity;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.lionsoul.ip2region.DataBlock;
import org.lionsoul.ip2region.DbConfig;
import org.lionsoul.ip2region.DbSearcher;
import org.lionsoul.ip2region.Util;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.FileCopyUtils;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.lang.reflect.Method;

@Slf4j
public class IpUtils {

    private static DbSearcher searcher;
    private static Method method;
    /*
    * 使用nginx等反向代理软件，则不能通过request.getRemoteAddr()获取IP地址
    * */
    public static String getIpAddr(HttpServletRequest request) {
        String ip = null, unknown = "unknown", seperator = ",";
        int maxLength = 15;
        try {
            ip = request.getHeader("x-forwarded-for");
            if (StrUtil.isBlank(ip) || unknown.equalsIgnoreCase(ip)) {
                ip = request.getHeader("Proxy-Client-IP");
            }
            if (StrUtil.isBlank(ip) || ip.length() == 0 || unknown.equalsIgnoreCase(ip)) {
                ip = request.getHeader("WL-Proxy-Client-IP");
            }
            if (StrUtil.isBlank(ip) || unknown.equalsIgnoreCase(ip)) {
                ip = request.getHeader("HTTP-CLIENT_IP");
            }
            if (StrUtil.isBlank(ip) || unknown.equalsIgnoreCase(ip)) {
                ip = request.getHeader("HTTP_x_FORWARDED_FOR");
            }
            if (StrUtil.isBlank(ip) || unknown.equalsIgnoreCase(ip)) {
                ip = request.getRemoteAddr();
            }
        } catch (Exception e) {
            log.error("IpUtils ERROR ", e);
        }

        //使用代理，则获取第一个IP地址
        if (StrUtil.isBlank(ip) && ip.length() > maxLength) {
            int idx = ip.indexOf(seperator);
            if (idx > 0) {
                ip = ip.substring(0, idx);
            }
        }
        return ip;
    }

    /**
     * 在服务启动时加载 ip2region.db 到内存中
     * 解决打包jar后找不到 ip2region.db 的问题
     */
    static {
        try {
            InputStream inputStream = new ClassPathResource("/ip2region.db").getInputStream();
            //将 ip2region.db 转为 ByteArray
            byte[] dbBinStr = FileCopyUtils.copyToByteArray(inputStream);
            DbConfig dbConfig = new DbConfig();
            searcher = new DbSearcher(dbConfig, dbBinStr);
            //二进制方式初始化 DBSearcher，需要使用基于内存的查找算法 memorySearch
            method = searcher.getClass().getMethod("memorySearch", String.class);
        } catch (Exception e) {
            log.error("initIp2regionResource exception:", e);
        }
    }

    /**
     * 根据ip从 ip2region.db 中获取地理位置
     *
     * @param ip
     * @return
     */
    public static String getCityInfo(String ip) {
        if (ip == null || !Util.isIpAddress(ip)) {
            log.error("Error: Invalid ip address");
            return null;
        }
        try {
            DataBlock dataBlock = (DataBlock) method.invoke(searcher, ip);
            String ipInfo = dataBlock.getRegion();
            if (!StringUtils.isEmpty(ipInfo)) {
                ipInfo = ipInfo.replace("|0", "");
                ipInfo = ipInfo.replace("0|", "");
            }
            return ipInfo;
        } catch (Exception e) {
            log.error("getCityInfo exception:", e);
        }
        return null;
    }

    public static Long getIpAddrNum(HttpServletRequest request) {
        String ipAddr = getIpAddr(request);
        if ("0:0:0:0:0:0:0:1".equals(ipAddr) || StrUtil.isBlank(ipAddr)) {
            return 0L;
        }
        String[] split = ipAddr.split("\\.");
        long ipNum = 0;
        for (int i = 3; i >= 0; i--) {
            long tmp = Long.parseLong(split[i]);
            ipNum |= (tmp << (3-i) * 8);
        }
        return ipNum;
    }

    public static Long getIpAddrNum(String ip) {
        String[] split = ip.split("\\.");
        long ipNum = 0;
        for (int i = 3; i >= 0; i--) {
            long tmp = Long.parseLong(split[i]);
            ipNum |= (tmp << (3-i) * 8);
        }
        return ipNum;
    }

    public static String ipToString(long ip) {
        StringBuilder sb = new StringBuilder();
        long tmp = 0xff;
        for (int i = 3; i >= 0; i--) {
            sb.append(((tmp << i * 8) & ip) >> i * 8).append(".");
        }
        return sb.deleteCharAt(sb.length()-1).toString();
    }

}


















