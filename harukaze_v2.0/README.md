# 接口规范

## 1.登录

### 1.1.请求验证码

请求地址：api/app/captcha

请求类型：GET

请求头：

请求参数：

```json
{}
```

返回参数：

```json
{
	"code": 200,
    "msg": "sussces",
    "data": {
    	"imgCode":  "data:image/jpeg;base64,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",  
        // base64 验证码图片
    	"key": "c19a6efc-af45-40ca-8057-1b811aad854a"		// 获取验证码的 key
    }
}
```

### 1.2.登录

请求地址：api/app/login

请求类型：GET

请求头：Content-Type: application/x-www-form-urlencoded

请求参数：

```json
{
    "account": "admin", 	// 账号
    "password": "123456",	// 密码
    "code": "1363",			// 验证码
    "key": "c19a6efc-af45-40ca-8057-1b811aad854a"
}
```

返回参数：

```json
{
    "msg": "登录成功",
    "code": 200,
    "data": {
        "user_info": {
            "id": 1,
            "account": "admin",
            "avatar": null,
            "createDate": 1654073427219,
            "email": null,
            "lastLogin": null,
            "nickname": "admin",
            "state": 0,
            "summary": null,
            "roles": [],
            "permissions": []
        },
        "token": "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImlhdCI6MTY1NDU3Njk2NCwiZXhwIjoxNjU1MTgxNzY0fQ.xchkbPupeWjd6J7v4be9lYiubveKdYsl8czosYjWlXBSospVJLn5Wmrqr1nGpcJGfk7VW5MECq2sZg7skvso6w"
    }
}
```

### 1.3.退出登录

请求地址：api/app/logout

请求类型：POST

请求头：Authorization: {token}

请求参数：

```json
{}
```

返回参数：

```json
{
    "msg": "success",
    "code": 200,
    "data": null
    }
}
```

## 2.文章

### 2.1.请求文章列表

请求地址：api/app/article/list

请求类型：POST

请求头：Authorization: {token}

请求参数：

```json
{
	"key": "summer",	// 文章标题关键字，标签名，分类名
    "category": "1",	// 分类 id
    "tags": [1, 3],		// 标签 id 
    "limit": 10,		// 分页大小
    "page": 1,			// 当前页
    "sort": {			// 排序
        "time": "desc",
        "view": "asc",
        "comment": "desc"
    }
}
```

返回参数：

```json
{
    "msg": "success",
    "code": 200,
    "data": {
        "totalCount": 3,
        "pageSize": 10,
        "totalPage": 1,
        "currPage": 1,
        "list": [
            {
                "id": 1,
                "title": "test",
                "summary": "test",
                "cover": null,
                "viewCounts": 0,
                "commentCounts": 0,
                "weight": 1,
                "state": 0,
                "createDate": 1654073427219,
                "updateDate": null,
                "body": null,
                "author": {
                    "id": 1,
                    "account": "admin",
                    "avatar": null,
                    "createDate": 1654073427219,
                    "email": null,
                    "lastLogin": null,
                    "nickname": "admin",
                    "state": 0,
                    "summary": null,
                    "roles": [],
                    "permissions": []
                },
                "category": {
                    "id": 1,
                    "avatar": null,
                    "categoryName": "tech",
                    "categoryDesc": "技术文章/学习笔记"
                },
                "tags": [
                    {
                        "id": 2,
                        "tagName": "java"
                    },
                    {
                        "id": 1,
                        "tagName": "mysql"
                    }
                ]
            },
            {
                "id": 2,
                "title": "test2",
                "summary": "test2",
                "cover": null,
                "viewCounts": 0,
                "commentCounts": 0,
                "weight": 1,
                "state": 0,
                "createDate": 1654073427218,
                "updateDate": null,
                "body": null,
                "author": {
                    "id": 1,
                    "account": "admin",
                    "avatar": null,
                    "createDate": 1654073427219,
                    "email": null,
                    "lastLogin": null,
                    "nickname": "admin",
                    "state": 0,
                    "summary": null,
                    "roles": [],
                    "permissions": []
                },
                "category": {
                    "id": 1,
                    "avatar": null,
                    "categoryName": "tech",
                    "categoryDesc": "技术文章/学习笔记"
                },
                "tags": [
                    {
                        "id": 1,
                        "tagName": "mysql"
                    },
                    {
                        "id": 4,
                        "tagName": "数据库"
                    }
                ]
            },
            {
                "id": 6,
                "title": "test123",
                "summary": "hahahhah",
                "cover": "",
                "viewCounts": 0,
                "commentCounts": 0,
                "weight": 1,
                "state": 0,
                "createDate": 1654655194452,
                "updateDate": 1654655697786,
                "body": null,
                "author": {
                    "id": 1,
                    "account": "admin",
                    "avatar": null,
                    "createDate": 1654073427219,
                    "email": null,
                    "lastLogin": null,
                    "nickname": "admin",
                    "state": 0,
                    "summary": null,
                    "roles": [],
                    "permissions": []
                },
                "category": {
                    "id": 1,
                    "avatar": null,
                    "categoryName": "tech",
                    "categoryDesc": "技术文章/学习笔记"
                },
                "tags": [
                    {
                        "id": 2,
                        "tagName": "java"
                    },
                    {
                        "id": 11,
                        "tagName": "test123"
                    }
                ]
            }
        ]
    }
}
```

### 2.2.请求文章详情

请求地址：api/app/article/info/{id}

请求类型：GET

请求参数：

```json
{
	id: "1"
}
```

返回参数：

```json
{
    "msg": "success",
    "code": 200,
    "article": {
        "id": 1,
        "title": "test",
        "summary": "test",
        "cover": null,
        "viewCounts": 0,
        "commentCounts": 0,
        "weight": 1,
        "state": 0,
        "createDate": 1654073427219,
        "updateDate": null,
        "user": {
            "id": 1,
            "account": "admin",
            "avatar": null,
            "createDate": 1654073427219,
            "email": null,
            "lastLogin": null,
            "nickname": "admin",
            "state": 0,
            "summary": null,
            "roles": [],
            "permissions": []
        },
        "body": null,
        "category": {
            "id": 1,
            "avatar": null,
            "categoryName": "tech",
            "categoryDesc": "技术文章/学习笔记"
        }
    }
}
```

### 2.3.写文章

请求地址：api/app/article/sava

请求类型：POST

请求头：Authorization: {token}

请求参数：

```json
{
	"title": "test3",
	"summary": "testtest",
	"cover": "",
	"weight": 1,
	"state": 0,
	"body": {
		"id": "",
		"content": "test from postman",
		"contentHtml": "<h1>test from postman</h1>"
	},
	"category": {
		"id": "1",
		"avatar": "",
		"categoryName": "",
		"categoryDesc": ""
	},
	"tags": [
		{
			"id": "1"
		},
		{
			"id": "",
			"tagName": "test"
		}
	]
}
```

返回参数：

```json
{
    "msg": "success",
    "code": 200
}
```

### 2.4.修改文章

请求地址：api/app/article/update

请求类型：PUT

请求头：Authorization: {token}

请求参数：

```json
{
	"id": "6",
	"title": "test123",
	"summary": "hahahhah",
	"cover": "",
	"weight": 1,
	"state": 0,
	"body": {
		"id": "5",
		"content": "test from postman, update",
		"contentHtml": "<h1>test from postman, update</h1>"
	},
	"category": {
		"id": "1"
	},
	"tags": [
		{
			"id": "2"
		},
		{
			"id": "",
			"tagName": "test123"
		}
	]
}
```

返回参数：

```json
{
    "msg": "success",
    "code": 200
}
```

### 2.5.设置文章状态

请求地址：api/app/article/set_state

请求类型：PUT

请求头：Authorization: {token}

请求参数：

| id   | flag  |
| ---- | ----- |
| "6"  | false |

返回参数：

```json
{
    "msg": "success",
    "code": 200
}
```



### 2.6.文章归档

前端获取数据实现





## 3.文章分类

### 3.1.分页查询分类

请求地址：api/app/category/list

请求类型：GET

请求参数：

```json
{
	"key": "te",
	"page": 1,
	"limit": 10
}
```

返回参数：

```json
{
    "msg": "success",
    "code": 200,
    "page": {
        "totalCount": 2,
        "pageSize": 10,
        "totalPage": 1,
        "currPage": 1,
        "list": [
            {
                "id": 1,
                "avatar": null,
                "categoryName": "tech",
                "categoryDesc": "技术文章/学习笔记"
            },
            {
                "id": 3,
                "avatar": null,
                "categoryName": "note",
                "categoryDesc": "随笔"
            }
        ]
    }
}
```

### 3.2.新增分类

请求地址：api/app/category/save

请求类型：POST

请求参数：

```json
{
	"id": "",
	"avatar": "",
	"categoryName": "test",
	"categoryDesc": "test from postman"
}
```

返回参数：

```json
{
    "msg": "success",
    "code": 200
}
```

### 3.3.修改分类

请求地址：api/app/category/update

请求类型：PUT

请求参数：

```json
{
	"id": "5",
	"avatar": "",
	"categoryName": "test123",
	"categoryDesc": "test from postman, update"
}
```

返回参数：

```json
{
    "msg": "success",
    "code": 200
}
```

### 3.4.删除分类

> 分类下没有文章，可删除

请求地址：api/app/category/delete/{id}

请求类型：DELETE

请求参数：5

返回参数：

```json
{
    "msg": "success",
    "code": 200
}
```

### 3.5.查询分类下的文章、评论、观看数

请求地址：api/app/category/{id}/articles

请求类型：GET

请求参数：1

返回参数：

```json
{
    "msg": "success",
    "code": 200,
    "data": {
        "articleCount": 3,
        "viewCount": 0,
        "commentCount": 0
    }
}
```



## 4.文章标签

### 4.1.查询标签列表

请求地址：api/app/tag/list

请求类型：GET

请求参数：

```json
{
	"key": "te",
	"page": 1,
	"limit": 10
}
```

返回参数：

```json
{
    "msg": "success",
    "code": 200,
    "data": {
        "totalCount": 7,
        "pageSize": 10,
        "totalPage": 1,
        "currPage": 1,
        "list": [
            {
                "id": 1,
                "tagName": "mysql"
            },
            {
                "id": 2,
                "tagName": "java"
            },
            {
                "id": 3,
                "tagName": "docker"
            },
            {
                "id": 4,
                "tagName": "数据库"
            },
            {
                "id": 5,
                "tagName": "nginx"
            },
            {
                "id": 9,
                "tagName": "test"
            },
            {
                "id": 11,
                "tagName": "test123"
            }
        ]
    }
}
```

### 4.2.新增标签

请求地址：api/app/tag/save

请求类型：POST

请求参数：

```json
{
	"id": "",
	"tagName": "test234"
}
```

返回参数：

```json
{
    "msg": "success",
    "code": 200
}
```

### 4.3.修改标签

请求地址：api/app/tag/update

请求类型：PUT

请求参数：

```json
{
	"id": "11",
	"tagName": "test345"
}
```

返回参数：

```json
{
    "msg": "success",
    "code": 200
}
```

### 4.4.删除标签

> 标签下没有文章，可删除

请求地址：api/app/tag/delete/{id}

请求类型：DELETE

请求参数：12

返回参数：

```json
{
    "msg": "success",
    "code": 200
}
```

### 4.5.查询标签下的文章、评论、观看数

请求地址：api/app/category/{id}/articles

请求类型：GET

请求参数：1

返回参数：

```json
{
    "msg": "success",
    "code": 200,
    "data": {
        "articleCount": 3,
        "viewCount": 0,
        "commentCount": 0
    }
}
```



## 5.评论

### 5.1.查询评论列表

请求地址：api/app/comment/list/{articleId}

请求类型：GET

请求参数：1

返回参数：

```json
{
    "msg": "success",
    "code": 200,
    "data": {
        "totalCount": 1,
        "pageSize": 5,
        "totalPage": 1,
        "currPage": 1,
        "list": [
            {
                "id": 7,
                "content": null,
                "level": 1,
                "createDate": null,
                "state": 0,
                "author": {
                    "id": 1,
                    "account": "admin",
                    "avatar": null,
                    "createDate": 1654073427219,
                    "email": null,
                    "lastLogin": null,
                    "nickname": "admin",
                    "state": 0,
                    "summary": null,
                    "roles": [],
                    "permissions": []
                },
                "toUserNickname": null,
                "weight": 1,
                "children": [
                    {
                        "id": 9,
                        "content": null,
                        "level": 2,
                        "createDate": null,
                        "state": 0,
                        "author": {
                            "id": 2,
                            "account": "test",
                            "avatar": null,
                            "createDate": null,
                            "email": null,
                            "lastLogin": null,
                            "nickname": "test",
                            "state": 0,
                            "summary": null,
                            "roles": [],
                            "permissions": []
                        },
                        "toUserNickname": "admin",
                        "weight": 1,
                        "children": null
                    },
                    {
                        "id": 10,
                        "content": null,
                        "level": 2,
                        "createDate": null,
                        "state": 0,
                        "author": {
                            "id": 2,
                            "account": "test",
                            "avatar": null,
                            "createDate": null,
                            "email": null,
                            "lastLogin": null,
                            "nickname": "test",
                            "state": 0,
                            "summary": null,
                            "roles": [],
                            "permissions": []
                        },
                        "toUserNickname": "admin",
                        "weight": 1,
                        "children": null
                    }
                ]
            }
        ]
    }
}
```

### 5.2.新增评论

请求地址：api/app/comment/save

请求类型：POST

请求参数：

```json
{
	"id": "1",
	"content": "test from post man",
	"articleId": "1",
	"author": {
		"id": "",
		"name": "test"
	},
	"parentId": "7",
	"toUser": {
		"id": "",
		"name": "lisi"
	}
}
```

返回参数：

```json
{
    "msg": "success",
    "code": 200
}
```

### 5.2.修改评论

> 未登录不能修改评论，可联系管理员删除

请求地址：api/app/comment/update

请求类型：PUT

请求参数：

````json
{
	"id": "14",
	"content": "test content from post man",
	"state": 1,		// 逻辑删除字段，可不传。0 正常，1 逻辑删除
	"weight": 10	// 置顶字段，可不传。1 正常，10 置顶
}
````

返回参数：

```json
{
    "msg": "success",
    "code": 200
}
```



## 6.用户

### 6.1.查询用户列表

请求地址：api/app/user/list

请求类型：GET

请求参数：

```json
{
	"key": "",
	"page": 1,
	"limit": 5
}
```

返回参数：

```json
{
    "msg": "success",
    "code": 200,
    "data": {
        "totalCount": 2,
        "pageSize": 10,
        "totalPage": 1,
        "currPage": 1,
        "list": [
            {
                "id": 1,
                "account": "admin",
                "avatar": null,
                "createDate": 1654073427219,
                "email": null,
                "lastLogin": null,
                "nickname": "admin",
                "state": 0,
                "summary": null,
                "roles": [],
                "permissions": []
            },
            {
                "id": 2,
                "account": "test",
                "avatar": null,
                "createDate": null,
                "email": null,
                "lastLogin": null,
                "nickname": "test",
                "state": 0,
                "summary": null,
                "roles": [],
                "permissions": []
            }
        ]
    }
}
```

### 6.2.获取当前用户信息

请求地址：api/app/user/info

请求类型：GET

请求参数：

返回参数：

```json
{
    "msg": "success",
    "code": 200,
    "data": {
        "id": 1,
        "account": "admin",
        "avatar": null,
        "createDate": 1654073427219,
        "email": null,
        "lastLogin": null,
        "nickname": "admin",
        "state": 0,
        "summary": null,
        "roles": [],
        "permissions": []
    }
}
```

### 6.3.新增用户信息

请求地址：api/app/user/save

请求类型：POST

请求参数：

```json
{
	"account": "test123",
	"avatar": "",
	"email": "",
	"nickname": "test123",
	"password": "123456",
	"state": 0,
	"summary": "sdsax"
}
```

返回参数：

```json
{
    "msg": "success",
    "code": 200
}
```

### 6.4.修改用户信息

请求地址：api/app/user/update

请求类型：PUT

请求参数：

```json
{
	"id": "3",
	"avatar": "",
	"email": "",
	"nickname": "test234",
	"state": 0,
	"summary": "test from postman"
}
```

返回参数：

```json
{
    "msg": "success",
    "code": 200
}
```



## 7.角色

### 7.1.查询角色列表

请求地址：api/app/role/list

请求类型：GET

请求参数：

```json
{
	"key": "ad",
	"page": 1,
	"limit": 5
}
```

返回参数：

```json
{
    "msg": "success",
    "code": 200,
    "data": {
        "totalCount": 1,
        "pageSize": 5,
        "totalPage": 1,
        "currPage": 1,
        "list": [
            {
                "id": 1,
                "roleName": "admin",
                "roleDesc": "admin",
                "permissions": [
                    {
                        "id": 1,
                        "path": "api/",
                        "name": "user:add"
                    },
                    {
                        "id": 2,
                        "path": null,
                        "name": "user:update"
                    },
                    {
                        "id": 3,
                        "path": null,
                        "name": "user:select"
                    },
                    {
                        "id": 6,
                        "path": null,
                        "name": "role:add"
                    },
                    {
                        "id": 7,
                        "path": null,
                        "name": "role:update"
                    }
                ]
            }
        ]
    }
}
```

### 7.2.查询角色信息

请求地址：api/app/role/info/{id}

请求类型：GET

请求参数：1

返回参数：

```json
{
    "msg": "success",
    "code": 200,
    "data": {
        "id": 1,
        "roleName": "admin",
        "roleDesc": "admin",
        "permissions": [
            {
                "id": 1,
                "path": "api/",
                "name": "user:add"
            },
            {
                "id": 2,
                "path": null,
                "name": "user:update"
            },
            {
                "id": 3,
                "path": null,
                "name": "user:select"
            },
            {
                "id": 6,
                "path": null,
                "name": "role:add"
            },
            {
                "id": 7,
                "path": null,
                "name": "role:update"
            }
        ]
    }
}
```

### 7.2.新增角色

请求地址：api/app/role/save

请求类型：POST

请求参数：

```json
{
	"roleName": "test123",
	"roleDesc": "test123"
}
```

返回参数：

```json
{
    "msg": "success",
    "code": 200
}
```

### 7.3.修改角色

请求地址：api/app/role/update

请求类型：PUT

请求参数：

```json
{
	"id": "3",
	"roleName": "test234",
	"roleDesc": "rest234",
	"permissions": [1, 2, 3, 10]
}
```

返回参数：

```json
{
	"roleName": "test123",
	"roleDesc": "test123"
}
```



## 8.权限

### 8.1.查询权限列表

请求地址：api/app/permission/list

请求类型：GET

请求参数：

```json
{
	"key": "",
	"page": 1,
	"limit": 10
}
```

返回参数：

```json
{
    "msg": "success",
    "code": 200,
    "data": {
        "totalCount": 8,
        "pageSize": 10,
        "totalPage": 1,
        "currPage": 1,
        "list": [
            {
                "id": 1,
                "path": "api/",
                "name": "user:add"
            },
            {
                "id": 2,
                "path": null,
                "name": "user:update"
            },
            {
                "id": 3,
                "path": null,
                "name": "user:select"
            },
            {
                "id": 4,
                "path": null,
                "name": "article:add"
            },
            {
                "id": 5,
                "path": null,
                "name": "article:update"
            },
            {
                "id": 6,
                "path": null,
                "name": "role:add"
            },
            {
                "id": 7,
                "path": null,
                "name": "role:update"
            },
            {
                "id": 8,
                "path": null,
                "name": null
            }
        ]
    }
}
```

### 8.2.查询权限信息

请求地址：api/app/permission/info/{id}

请求类型：GET

请求参数：1

返回参数：

```json
{
    "msg": "success",
    "code": 200,
    "data": {
        "id": 1,
        "path": "api/",
        "name": "user:add"
    }
}
```

### 8.3.新增权限

请求地址：api/app/permission/save

请求类型：POST

请求参数：

```json
{
	"path": "",
	"name": "test"
}
```

返回参数：

```json
{
    "msg": "success",
    "code": 200
}
```

### 8.3.修改权限

请求地址：api/app/permission/update

请求类型：PUT

请求参数：

```json
{
	"id": "9",
	"path": "",
	"name": "test123"
}
```

返回参数：

```json
{
    "msg": "success",
    "code": 200
}
```



## 9.日志

### 9.1.查询日志列表

请求地址：api/app/log/list

请求类型：GET

请求参数：

```json
{
	"key": "",
	"page": 1,
	"limit": 5
}
```

返回参数：

```json
{
    "msg": "success",
    "code": 200,
    "data": {
        "totalCount": 9,
        "pageSize": 5,
        "totalPage": 2,
        "currPage": 1,
        "list": [
            {
                "id": 1,
                "ip": 2130706433,
                "module": "文章",
                "method": "info",
                "address": "内网IP|内网IP",
                "operation": "查询文章详情",
                "params": null,
                "time": 334,
                "userId": null,
                "browser": "PostmanRuntime 7.6.0",
                "createDate": 1654791413234,
                "os": "Unknown ??"
            },
            {
                "id": 2,
                "ip": 2130706433,
                "module": "文章",
                "method": "info",
                "address": "内网IP|内网IP",
                "operation": "查询文章详情",
                "params": null,
                "time": 322,
                "userId": null,
                "browser": "PostmanRuntime 7.6.0",
                "createDate": 1654791793906,
                "os": "Unknown ??"
            },
            {
                "id": 3,
                "ip": 2130706433,
                "module": "文章",
                "method": "info",
                "address": "内网IP|内网IP",
                "operation": "查询文章详情",
                "params": null,
                "time": 306,
                "userId": null,
                "browser": "PostmanRuntime 7.6.0",
                "createDate": 1654791872137,
                "os": "Unknown ??"
            },
            {
                "id": 4,
                "ip": 2130706433,
                "module": "文章",
                "method": "info",
                "address": "内网IP|内网IP",
                "operation": "查询文章详情",
                "params": null,
                "time": 302,
                "userId": null,
                "browser": "PostmanRuntime 7.6.0",
                "createDate": 1654792183818,
                "os": "Unknown ??"
            },
            {
                "id": 5,
                "ip": 2130706433,
                "module": "文章",
                "method": "info",
                "address": "内网IP|内网IP",
                "operation": "查询文章详情",
                "params": null,
                "time": 277,
                "userId": null,
                "browser": "PostmanRuntime 7.6.0",
                "createDate": 1654793001133,
                "os": "Unknown ??"
            }
        ]
    }
}
```

### 9.2.删除日志

请求地址：api/app/log/delete/{id}

请求类型：DELETE

请求参数：1

返回参数：

```json
{
    "msg": "success",
    "code": 200
}
```



## 10.todoList

### 10.1.查询todo列表

请求地址：api/app/todo/list

请求类型：GET

请求参数：

```json
{
	"key": "",
	"days": 7,
	"page": 1,
	"limit": 5
}
```

返回参数：

```json
{
    "msg": "success",
    "code": 200,
    "data": {
        "totalCount": 1,
        "pageSize": 5,
        "totalPage": 1,
        "currPage": 1,
        "list": [
            {
                "id": 1,
                "content": "完成博客后端",
                "isDone": 0,
                "createDate": 1654793521873
            }
        ]
    }
}
```

### 10.2.新增todo

请求地址：api/app/todo/save

请求类型：POST

请求参数：

```json
{
	"content": "3"
}
```

返回参数：

```json
{
    "msg": "success",
    "code": 200
}
```

### 10.3.修改todo

请求地址：api/app/todo/update

请求类型：PUT

请求参数：

```json
{
	"id": "2",
	"content": "3leetcode",
	"done": true
}
```

返回参数：

```json
{
    "msg": "success",
    "code": 200
}
```

### 10.3.删除todo

请求地址：api/app/todo/delete/2

请求类型：DELETE

请求参数：2

返回参数：

```json
{
    "msg": "success",
    "code": 200
}
```



## 11.友链

### 11.1.查询友链列表

请求地址：api/app/friendlink/list

请求类型：GET

请求参数：

```json
{
	"key": "",
	"page": 1,
	"limit": 5
}
```

返回参数：

```json
{
    "msg": "success",
    "code": 200,
    "data": {
        "totalCount": 0,
        "pageSize": 5,
        "totalPage": 0,
        "currPage": 1,
        "list": []
    }
}
```

### 11.2.新增友链

请求地址：api/app/friendlink/save

请求类型：POST

请求参数：

```json
{
	"name": "百度",
	"url": "www.baidu.com",
	"avatar": "",
	"siteDesc": "百度一下"
}
```

返回参数：

```json
{
    "msg": "success",
    "code": 200
}
```

### 11.3.修改友链

请求地址：api/app/friendlink/update

请求类型：PUT

请求参数：

```json
{
	"id": "1",
	"name": "百度",
	"url": "www.baidu.com",
	"avatar": "",
	"siteDesc": "百度一下，你就知道",
	"state": 0
}
```

返回参数：

```json
{
    "msg": "success",
    "code": 200
}
```



## 12.anime



## 13.网站导航

