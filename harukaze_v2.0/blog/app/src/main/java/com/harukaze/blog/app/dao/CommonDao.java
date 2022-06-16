package com.harukaze.blog.app.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.harukaze.blog.app.entity.MenuEntity;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface CommonDao extends BaseMapper<MenuEntity> {

    @Select("select id from hk_ip_pool where ip = #{ipAddrNum}")
    Long selectIp(@Param("ipAddrNum") Long ipAddrNum);

    @Insert("insert into hk_ip_pool(ip) values(#{ipAddrNum})")
    void insertIp(@Param("ipAddrNum") Long ipAddrNum);

    @Select("select id from hk_os where os = #{os}")
    Long selectOs(@Param("os") String os);

    @Insert("insert into hk_os(os) values(#{os})")
    void insertOs(@Param("os") String os);

    @Select("select id from hk_browser where browser = #{browser}")
    Long selectBrowser(@Param("browser") String browser);

    @Insert("insert into hk_browser(browser) values(#{browser})")
    void insertBrowser(@Param("browser") String browser);
}
