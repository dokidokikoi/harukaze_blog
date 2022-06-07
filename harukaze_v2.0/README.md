# 接口规范

## 1.登录

### 1.1.请求验证码

请求地址：api/app/captcha

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
    	"imgCode":  "data:image/jpeg;base64,iVBORw0KGgoAAAANSUhEUgAAAMgAAAAyCAIAAACWMwO2AAAWG0lEQVR42u2ceVhUV5rGidlMYkI0RgyuMUSWAAlqEkHBREMQULagaEBckrhEiRgXBFSMSVQQRMQVxJBENsGFVQIKZqbTPdM9Y0/3dGJnuqcnOp0ZHae7p3ume3omy9O/+OWeHKuKooAqQuXh+6OeW1X3nnvOPe953/c799zrcv7GeN+IvzLir7X4nhEffPBBUlJSenp6Y2Pj9434gRF/Y8TfavFDI35kxN8Z8fdaXLhwoa6uLi0trb6+/sdG/IMWPzHip0b8oxY/M+JDIz7S4qIRPzfiYyP+SYtfGPFLI/5Zi18Z8S9afKLFJSMuG/GvWvzaiE+N+Dcj/l2LK0ZcNeI/tLhmxH8a8RstfmvE77T4Ly1+b8QfjPhvI/5Hiz8a8Scj/leLPxvxf0b8vxYuFlFlEVjf06KhoWHAgAEuLi58+vv7r1y5sqqqygqwfqhFp8ACVZS8ceNGc2D9RAtzYP1MC3NgXdTCccC6pIU5sH6thYOA9VstLALr91o4CFifffaZi3W6sggs6AqucjGL0aNHQ2PFxcWwV0fA+pEW5sC6cD3Cw8Mprby83AqwfqqFFbqyCKyfa2EOrF9oYQ6sX2lhHViXtbBCVxaBdUULc2Bd06InwPqDFubA+qMW5sD6sxb2BBYAEsayGIMHD549e3Zubi6Hd6qD5sAaMWLEwIED+d0WYNlFBy0C65da9IIOWgTWVS2s0JVFYHVDBy0C609aWKGrrgGrI4NF4K4yMzODg4MBQUcIu/3229lh06ZN7733no062NrayoETJkzoN1jOa7BuAJbtBusDI8Szt7e35+TkzJo1C6LqCGHQ26OPPpqSklJTU2MdWLt372b/xYsX9xss5zVYloHVqQ6aAEvlg/yCwVqwYMGYMWNcOg7+TU5OPnr0KARmroOLFi1in/z8/H6D5bwGy87A0vPB6upq+AmWsmLFhgwZEhUVBUVRggIWIshf586d6zdYzmuwOgGWFYOlo8r6DFZzc/PmzZunTZtm3YqFhIRkZWVhxdht5MiR/QbLqQ3WN8DqtsHSUWV9Boty8vLyoCiIysVq+Pn5VVVVcWy/wXJSg2UBWHbRQeszWPyIwcJmWbdiaOjYsWNDQ0OXL1+em5tbW1sLzvoNllMYLEcBy8Y7OcTJkydXr16NFbvppptcOovbbrvN09MzMjKSQwoLC9FNhS1zYOHSpJL4NiAl8HKEwaJY/qWBbPTQYMlRVImzsCe/O6nBsgYsuxgs228RggN8GOiJiYnBbJFaBgYG3n///dahduedd/r7+8fGxm7YsIGEtL29XQFr/fr1DQ0NS5cu3bt371tvvdXW1gbs7KuD/AiwAPfZs2f5euDAAc7IjwpbXTJY7FNZWbl9+/YlS5bQooyMjDfeeINRQclOZ7C+BlYvGKxOgUU1Bl0PTq07d76im5mZmQkJCRMnTrz33nutQ83V1XXSpEmzZs0CoJMnTwZ5jz/++AMPPBAdHU0dzIEFkwmq+FFQhdry1RZgsQM4wDhSuLu7+9tvvz1z5kyq/frrr9MiDrHRYLFNSymKqg4dOpQm3HrrrePHjx8xYgQ/gleOci6DZQqsXjBYHd0ihFqABZ8/1sJiPgj3FBUVQVFxcXHQFdBxsSFuueUW+js7O5vKwGdACnZZtWoV+kXDoTQQVl9fD2dwXhDDPlYMFp8c1djYiPkjFwFblA+8vLy8wERZWdnGjRshs3379glvWTFY/MvVoy133HEH2TGWAHPJJ7rPp7e3N04UoHOsExkshwDLdoOlgIVb4jreddddOl3ZOIPFNofv378/NTV19uzZDHS61grC6C1ojw4DlFSstLQ0MTFx3bp1BQUFYWFhOTk5a9euXbNmDaiiMh3RFb9wZcLDw9Gsm64HmABbTz75JNvx8fHPPvvs6dOnuaS7du2yooNstLS0gEKLw0NAxpWhQE7qRAbr888/d/nWDVZdXd19993HdQwICLCIKttnsPi9qalp/vz59NPAgQNVQmAxM+BHPByn9vX1RTrZf8qUKaQFo0aNAl4zZsw4dOgQ+ijeXAcWcgnbJSUl3XPPPcIu6hQ333wzP959990rV6708fGhmenp6exvEVh8sgPyx2CQEghK4HA+BWqCLXT23XffpRxnMVhfAevbNVgYCCwFV9DDw4Oxa52urM9gcWoULS0tDeYwIS2w8vzzz/P53HPP0d/sAMHgYEwAJzcJIM7hw4eTqJaUlGCbzBmLk+bn5w8bNoxypAQO5CgwChcKFIDp4MGDk5OTKeHgwYNASnhLAYuvNKeiokJVlQO5FHPnzp0zZw42EXFUFcMjglQyaGcxWDcAq/cNFuI14HogPXI/x0YdNAEWX6nM4cOHGfoQgJSpeoX+BgSRkZEknmfOnCHVmjp1alRUFKxAXwIONtifT3NWg36Cg4NfeeUVBgApJwiDNth46aWX5HAOBA2cAu177bXXwAS+jR8BLp5p5MiRK1aswDhyNUwYi5ofP34ckhZgURQboaGhNKSmpgZztmnTJlUN8BoUFISRdxaDZX9g2aiDFIihkSQOxTFHle23CMVjYcwxWNKdYnslOMW4ceMmTJgQEhICOOh7SAu4PPHEE9Kd4ANswS6kY7pK8klP85fJ7Iafnx+lsT/oueV6UDKySHNOnTpF+YsXLwZe7AmHcaJ58+Zt2bJlx44dMoOqdBCAvvnmm1RVgAX0UWSE+MqVK5KZcpQ+gccOWDFnMViWgeVog4WpElfh6elJInbBiG4YLMqkkjDfww8/TGcDCPrykUceefDBBxWwwK4wFhwAJURERMBYQmkYKRA2ceJEQAklsBsSJn9xLIjE4MNA5JKQFpyEmJqoJ7CAlhISEoAU7Tp27BimCgJ2c3ND3IHdzJkzYayMjAyZTVV0xZVEtRkJQBBwQ7SgEyMFsGROi3xCBzrYMgFWXzZYnQDLEQZLmSquOKWpRQ2d6qBFYCFtO3fulDUU4qPJy7KyskjW6DMdAa+++irV27Zt2+bNmxcsWMC/6AufgBtuABbTp0/nKByYUkYCwJHW4YTEYJEqlpeXBwYGAl+lpCaUhh8CjgBLCA8svvjiiySttFTpICSN8UpJScGnsycI5uxbt2798MMP5U6OLB+CfRWwQDyVdBaD9Q2wesdgQfXKVOlrsLptsCC8ZcuWDRo0SPgJlBQUFOBdIAlIS/U3MsdwJ8WjYpWVlSBp8uTJ9BwqlpiYCCEdOXIEpuHY6OjoYddDckZ0k52pDxdBnDvuh6OGDBkCQYIbWoSLwqSzAf+ZJwSAA+mEL0E2V09sFtyJIwTl0CHMlJeXRxfotwiLioqAL7BTwPLy8gKdzmKw7AwsKzqIDWJcijBBWiaL+7ptsCiBdE/yKdTk6NGjeHn6HnHkq+pd+pUubGxs/Pjjj4EX9SkuLma3+fPnQ0jsjC+mZBAJu4APoQq0b+HChRcvXgQEnAhUUU/2QU/hD7QJspkyZQolcAGRObmNA+uQCa5fvz4mJgagmywWgtJQXkqgcLiWBprce/7oo49aW1vRVkhR7JcwHyzb1NSEOXMKg2UBWI4wWCTnOFlJ0DAiJmuRu22wJCUkP6eP8ct0Of/SMRj5DRs2YKRUd5Kr0zEIkLqTI4sdaPjZs2ehBziDhlAUx2K3IR42AA3+mgQQPhO64itd/tRTTz300EMUC0QYLaAE2w7hZWZmUgf2VDcKRT25gOAYkkb9R48erVMaFwS1BWHsA2hojiStUKzyiJwLfQfcmHqQ5xQGyxqw7GKwmpubue4y04OnbmlpMV/k3m2DJdNX9Cik1dDQIEsbABYSA7CAheo/rLeQk8m9Z0EYGxzIBgJKTodjg1fAlre3d2FhITyhZrBoEVqJSMmSMuwUcknTYEeOGj58OCDgF8xQbW0t+1u8RchJgXJUVNS0adP0W5+UiUAjefr0FRcNK1ZVVcW1ciKD9TWwHGSwcBLwOReIlK20tLSjh716MoNFtLW1mazBAg04Hhy06iFoBsRAnBaXygjPYdcgFfAh0kPgiqqrq8+fP6+AxS/kd6iYMvjsJnddZKJfgjwRjpGJe4FXR2uw+IrwYdsZfuZ3dWRahFQR18XOTmSw7AksXQdPnDiBTMgEDDLEv1aeIuy2wepoySjshVXS56XwOkAN224CLEpmf0wxUoWYgkVZezj5eqCPVBLkUX+1ogHV8/X1VUmoh4cHFCUzHfrsABEXF7d8+XKgaeMid0YI+1Oaif3HRURERDBaaKmzGCxTYPXcYLEbWRJ4Egty8uRJ6w979dBgWQQWhcjMuN49a9eupRzzNViQCt0JMQQEBMjNPkgIz37s2DEME/UnK8T3AFa597xnzx6kUFAlc11gKzY2FuXFMKkJAmWhyEZpPrXqdC0yFQPiDACknM+xY8ea3+LkXCSzJSUl7NyXDdYXX3zhYheDxWjDBZOUiaslT0YHbXk8tYcGq6NF7pgqdWMHOhk0aNDLL7/c0eI+1A3xwiFJCoYA+fn5kdZhlfBqlFZWVkYDARanOH36dFBQkKBq0qRJuCu4LS0tDRnF1LNtcp8bI0UeAG/ZssgdlHM6TkSjpFYyG0c5o0aNQqZlxErgxhg/cjezrxmsr4BlFx0k56JLZIRhQeS5Z1te09BDg2W+yJ1fOIQEDSnEgMsoxwJTQwQa4jExWHQ5uV5wcDCDwdXVFSUid3vmmWcACqhKTU1FyslkZY0oTqugoIAOFu1zc3OD59iBEQWTUT6unLxv2LBhMglMgGn8Vk5ODulhlxa5Y624kjJQCTwco4U6QKJYPShWzc1SE0YCI6eiouLSpUt9wWDZB1jYTxlJMASd1KXXNNjdYHEgZWZnZ9MTkjqQbfn4+JDNNTY27tu3zwRYJFyIHbAAImT+KBoEQzqGJoI2BJSi5s2bxzWhUzmW/Z9++mlltDkKbtu5c2dycvLly5fp1/Ly8vj4+LCwMCVk5C6o7TvvvNOlpwiBKZksVVLogSDJstlTRJDTMSpAG3VQus8GX1HSmpoacPxtGawbgNVtg7VkyRJpFRe3S+/BcoTBYoN/Dx8+rOaBZHUAJMTOZ86cMdFBaoWph2LT09PpDMRl1apVjH7giGGCMCAweE4mSImWlhb9CTZ3d3e6lpSTomT6in3AFmwHsmU2gZyAfajAJ598QhtNdJBAAQVYbKtHJxBfridZiP4yH8atcle6wQLQtIJqQ12q1VAaxMbYqK2tpdjeNFiWgdUlgwVdyeQnnI9edOk9WA4yWJwR/5uYmKi6BCMsdwOBCP9yFJ8XL14EDTKJxQYnpe2oHn2DDDH0aRdiSveAJP4VKaQVMByOR3UeOQqNBTSyyJ0+bm9vnzt37q3Xg32wSpgw4AWAIDwBlqzQkosD561bt45q8IsCFs1EZIG1ngoAUAq3PtFAPck8li1bBk8rkCEpkDGDh6FFUulog9UJsGzRQUVXL7zwQlffg2V3gyVBJelsMKRPCO3evRsGgpCysrLIqjBPVBLV41zooyxyxypRBxwMrggbDmhw9PgzcAb+1KJk9AhVUmsDARlpI0cBO3l0ghoil1xSBT5fX1/gBebOnTsna0epJFknEom7x5OxPyWANpFFNpBCftGfU6L+XG2yB9tnsGhXaWkpXYMc6+VwUoYHlWF/RxisHgGLocN1kXthDG7oqqvvwbK7wVKPp4aEhEASero+Z84cFArN4i+6Gat+5MgRehSuAhbqeS/2gVcSEhJ01zJjxgx2U8+mgi34b/z48Xr5O3bsUMBCTLHYNFPSUpk7jYmJQaDFYEEb4CMwMJCayJIYNBd6Q2fr6+vxggALh4fHMplxiI2NRbW7N9GAhjKikFf4W58kQ6MZYO+//z40Zi+D9Q2wumGwcKOqfgzxrr5o1BEGSwHr1KlTCIGs+FNdSz/hxEno4CHBDeCLjIykkpAQ1x3E5OfnI0Dh4eHq5jH9LZMO+kPPBw4c8PT0lKXJEnAAI43kf+rUqUAWvoGiJKfB6YMwzgJtcB3QQfBB09BZ8C3g4yxUicqEhoYCOIYB/kyVIK3A2EVERFACQtnDGSy5s45bUJouvE7DSUQQItirJwbLArBsN1hbt25VdWK7qy8adZDBUk/Tyxpl6RL839ChQ1FDHC4dj77I/BDGFnwUFRUhdrgf6kD59K6bm5uiisceewy0URMdWDRWlkLQ2bJAWRYxY5YZb1wo1IfUcty4cQJQCAlKw1xzZQAWmgtDoKfmL0oBYbKyWe4aqSc1CGoOF3L1MEl2vEVIk/fv348plJk8le6QkObl5cHf3dBBa8Dq1GAxslU96Jg+YrAEVeyGQUHp9IV+0dHRGRkZTU1N+CcyLMgAt4HZAh8YMmRi+/btubm5YWFh6gkZmZkEkZzC5DUNyBkH4sC8vb0RFFnljD4eOnQIj4zpxifAPVIU3ggXVVhYiKTK89NwHiiB50xW6Ft8IpKgtkuXLsUwcaEcd4uQi7937964uDh1Q4y29zawMCKq8X3KYBHFxcXIHKNQ1ZCLhUknVwI6KSkpXDhZCQiXLFq0CO9VU1OzevVqWZ6gVid7eXlVVFSYoEqAJcsMcSfyUI16VAt4UQg+JiAgQKw3aEtKSgKFqA82jsMhLYwU0CTLQxAFlwOMkJxAeAs8gWy4hFHB4ej15cuXe+EWIZ90GagSxuqqwfoaWN2bwcIFq8nlPmWwCLoNQg0KChozZoy7uzvdTH9jKVpbW/lr165d6B3dJrqDlcGeo4mYMPRLiSBH4R3BBH7Z4nuwSBVpNRQFhjw8PEhF1fMXsuqBQFOQMEpmHHIg/aSmr6Au3Do6i7OBzyiB2lINoMYntUJbcdawHVRXUFAA2129elXuQPfZW4SWgdWlGSyVwbLR1wwW/6JuIAn/hCTRK1Ry4cKF8vxFW1sbDfT390eqZG27uiWnbA08AYExZDmko9cV8aOcFx6CvdBEWdOM38JdobbAC14EVVCXrI2mUQpYn376KYVQW6oK3KkqiIeWDh48SM64Z8+euro68gBZ1MCxfX8NlkLVl19+6dLtGSyISoAFdfUpgyWvK+ITrWGDdlETRIR2caCsaKDCHEgTcF2gCiiIYQdkrq6u8fHxbEyfPl3q2el7sChwxYoVYCIqKoodyCIhIdgLnJHWAKyYmBhZIGrxFiEhj7CS7skam2vXrsFnfFJtUMXvTrEGyw7Akpdmq2V0fcpg2fIeLM7Iv1QMq46J9vPzQ84QPiCFGvr4+Jw/f17eZyRL/Dp9H6QsBqRdeDUKiY2N3bJlS3V1NdaKDJFWfCdfNNqRwfoKWN0zWPok1po1a/qUwbLxfZDy3qLjx49DMJgYeAVTf+LEiZycnLKyMtSTJnf1RaOILG0sLy9vbm6GeLD227Zt48fv6otGOzJYNwCrSwYrOztbAYue6FMGy/YXjcp2ZWVlSUkJO2NrMFVwFeMEJerhi0apuVzY7/CLRjvSQcvAssVgpaam6pNYfc1gdfVFo5RJPeXlH3IKu7xoVB59/g6/aNT+wGpvbwdPcBU6yBB3LoPV/yZ3RxusToBl9xeN9h2D1f8md4carG+A1TsvGu1TBqv/Te6OM1jEXwDG9SYnebvxzQAAAABJRU5ErkJggg==",  
        // base64 验证码图片
    	"key": "c19a6efc-af45-40ca-8057-1b811aad854a"		// 获取验证码的 key
    }
}
```

### 1.2.登录

请求地址：api/app/login

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

### 



## 2.文章

### 2.1.请求文章列表

请求地址：api/app/article/list

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
        "totalCount": 2,
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
                "authorId": 1,
                "bodyId": null,
                "categoryId": 1
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
                "authorId": 1,
                "bodyId": null,
                "categoryId": 1
            }
        ]
    }
}
```

### 2.2.请求文章详情

请求地址：api/app/article/info/{id}

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

### 