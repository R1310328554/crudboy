# curdboy 4 java, with everything opensource

#### 介绍
十年JAVA两茫茫，不思量，自难忘。

千里屎山，无处话凄凉。

纵使转行应不识，尘满面，鬓如霜。

（可怜虫啊， 搞到了最后竟然还是一无所有！）

集成所有的一切的（java 相关的）优秀的开源项目、工具。 一网打尽， 不再重复造轮子！

集成 所有的牛x技术！ 让你一次性 学个够， 学不完！ 打造史上最强技术！

打造极致开发体验、极致开发效率！ 让你的技术快速提升，而不是陷入蝇营狗苟莫名其妙的琐碎微小的错误。

更多请参考我的目标： 
[/topGoals/]: # topGoals(https://gitee.com/r1310328554/curdboy/blob/master/topGoals.md)

#### 软件架构
本项目基本上全部迁移于ruoyi-vue-pro （https://gitee.com/zhijiantianya/ruoyi-vue-pro） 的最新代码；

但是在那个基础之上做了很多简化或改进。保证简单而且足够的傻->KISS 原则！

仅仅依赖于数据库和Redis！ 没有其他特殊的需求！

本项目的每一个一级目录就是一个独立的 maven工程，都是可以独立运行的！ 没有很多错综复杂的依赖关系！


1.  crudboy-framework 是基础的框架代码，包含了大多数常用的技术和底层支持
2.  crudboy-emall 是基于crudboy-framework的商城
3.  crudboy-ssodemo 是一个简单的基于oauth2的sso demo工程


#### 安装教程

1.  下载源码
2.  执行建表语句
3.  修改数据库、redis的地址、端口、公众号、配置支付网关等 
4.  启动Main方法即可

#### 使用说明

1.  很简单，简单过任何一个框架
2.  没有文档，不需要那么多文档
3.  适合于小项目，估计大项目也可以用
4.  没有Spring Cloud, 没有烦人的东西 
5.  想怎么折腾，就怎么折腾。

#### 第三方技术

json、二进制 格式化编辑、预览
2/8/16/10、bcd 进制转换
MD5、sha hmac 加密解密，web在线操作，当然，也支持程序方式。
cron 解析
bejson 那样的

--- 统一了 多个支付类型： 阿里支付宝、微信支付。 参见 PayChannelEnum 




#### 特技

crudboy 4 java, with everything opensource

## TODO
1. framework中去掉对流程即bpm的依赖，做成可选
2. 各个功能做成可选可配置，任何东西都配置化
3. 区分开来哪些是核心功能模块，哪些是附加可选功能模块；必要的时候剥离核心功能，加快启动速度，争取3s内启动完毕
4. 集成各开源技术， 一网打尽。