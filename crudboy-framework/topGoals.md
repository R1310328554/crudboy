# low code

# 名称简化
方法名类名文件名简化

createNotice --> create , 不需要增加实体名

# 减少类的数量
3 直接一个 NoticeService 即可, 不需要 NoticeServiceImpl; 减少类数量 !!
NoticeService 需要继承 CrudService, 提供基本的增删改查功能!!  提供default !!

即, 无需再这样:
NoticeServiceImpl implements NoticeService

-- 减少目录嵌套，目录太深，看到就烦。 特别是点好几次，发现这个目录只有一两个文件，没有什么含义， 不如放一起。
-- 

因为一般的service 其实也没有几个实现类!! 一般就一个!!

# 减少字段
业务 entity, 保留最核心的字段;
其他所有都继承这个....

现在是 CreateReqVO 继承 baseBo, CreateDo 无继承;

应该是:
CreateDo/ CreateReqVO 都 继承baseBo, BaseVO 一定是包含了所有的业务字段， 而且是纯业务字段， 其他都不包括。 其他都从这里继承。 Do类对应表，但是Do 也要从BaseVO继承。
———— 确定吗？ 
CreateDo 主要是增加 新增 修改人/时间
delete 当然, 不需要do, 一个 Serializable 字段即可, 或 Key 对象

-- 都继承baseBo， 但如果有不想继承的字段， 怎么办？ exclude 一下 ？
-- 干脆全部使用 baseBo， 一个类即可， 不需要继承来继承去。对有 between查询需要的字段，比如需要比较大小的int或日期等，如 private LocalDateTime[] createTime;
-- 那么就不得已还是需要继承一下。

-- 也不需要 exclude 一下，多余的字段， 你想要就要， 不要就空着， 不影响。。

creator / createdBy 变成可选, 而不是必选!!
通过注解实现 createTime 等!!

--- 通过额外的日志表来记录 创建、修改人、时间
--- 因为java不支持像go那样的嵌入struct的语法， 如果支持的话， 那么我可以给每个java类嵌入一个 基础类，这样的话，我就可以嵌入id、create、update、
delete、status、updateby 等等业务属性不是很强的字段。 为什么id 也要？ 

--- ExcelProperty这样的注解肯定是不需要的， 不过就是 easyExcel 独创的， 侵入性太大。 共用 swagger的注解不就好了？
--- 对应 @NotNull 这样的验证字段， 如果有不同的需求，应该就 通过group 来区分。 比如查询的时候，肯定不需要。新增的时候不需要id， 修改的时候需要id。。


    private void validateMsdsExists(Long id) {
//        msdsMapper.selectCount()
//        LambdaQueryWrapperX<MsdsDO> wrapper = new LambdaQueryWrapperX<>();
//        wrapper.eq("id", id);
Long count = msdsMapper.selectCount("id", id);
if (count == null || count == 0) {
throw exception(MSDS_NOT_EXISTS);
}
//        if (msdsMapper.selectById(id) == null) {
//            throw exception(MSDS_NOT_EXISTS);
//        }
}



# 减少代码量

## 数据库校验层面验证，而不是应用层面 
6 减少代码量, 之间统一处理数据库异常; 使用数据库的异常来做校验, 而不是代码层面的! 为什么呢? 因为如果我们在数据库都某字段长度做了校验, 但是
服务代码层面没有或没有来得及校验, 那么还是会走到数据库层.. 再者, 如果我们在数据库取消了/修改了某个字段的校验, 服务代码还还保留, 会导致
不必要的 低层次的代码改动, 这些都要求改代码, 带来一些麻烦! 如果只是数据库校验, 那么就不需要改代码!!


# 实时互动 jrebel

5 数据库-实体-服务之间互动.

再者, 如果数据库修改了某个字段的类型, 那么代码应该可以能够及时的 同步!! 删除/增加字段也是如此!!

当然, 对于某些复杂的表关系, 那么要求我们事先做好 数据库关系的设计!

同时, 我们可以启用一些外键!! 增强... 让数据库做校验, 父类不存在, 那么关联子类不允许创建; 父类删除, 则数据库自动删除相关的子类数据行!

;
数据库修改某个字段的注释, 字段名, 所有相关前后端代码都应该跟着变动!!


直接物理删除, 而不是修改deleted..

减少 NoticeConvert.INSTANCE.convert 这样的转换! 转来转去也反而降低性能!!  应该是

让 ServiceImpl层消失 ! 多写那么多没用的类, 也是耽误时间..


## 去掉类似 validateNoticeExists 这样的校验, 因为它会查询所有字段, 效率会很低的哦.., 还不如直接...

    @VisibleForTesting
    public void validateNoticeExists(Long id) {
        if (id == null) {
            return;
        }
        NoticeDO notice = noticeMapper.selectById(id); // 这个会查询所有字段, 效率会很低的哦.. 
        if (notice == null) {
            throw exception(NOTICE_NOT_FOUND);
        }
    }

## id字段是必须的吗? 其实有些时候, 也不是! 因为, 某些业务会要求每行数据有唯一的索引, 这个索引, 其实就可以用来做id !!


# 减少臃肿代码
各种枚举类, 必要性不是很大, 直接存汉字或英文,不可以吗?
不过, CommonStatusEnum 枚举类 这种的基础枚举, 可能还是有一点作用!!

--- 如此大量重复的没有意义没有营养的curd service类，有存在的意义吗？


##  controller层, 不需要指定 GetMapping PostMapping 等等 -- 通一个注解搞定！  这个不容易实现， 可能有点麻烦、需要。。
--- 去掉一起重复的代码！

## 进一步 发挥lombok 的优势！！
创造更多的更加遍历、优秀的 lombok 注解！

##  transactional 不需要, 而是声明式 !!


## 对于普通对象，不需要 getter setter。
不需要 dao、mapper层、
--- 全面简化各个类、
--- 去除所有的do、vo类，或 简单的do、vo类 放一起。。


出错的时候， 需要给前端返回更加友好、准确的错误提示。

## 提供一个工具，实时的同步数据库、应用层代码。 

云上直接codegen（ 需要一个 云开发环境、 云中有源代码）、下载、编译、打包 、安装代码、自动重启、热启动
--- 我不能快速的 静态的 生成代码吗？ 非要启动服务器、 前后端 吗？ 


--- 做一个框架，需要经得起严格的推敲 ！！！
---  严谨！ 合理的！
--- 全部整合到一起不好吗？ 非要那么的分散，修改、调试起来非常的不方便，烦死了！ 

--- 几十几百个的pom，依赖来依赖去，真的太伤神。 难道需要我们各个都是pom专家？ 即使是maven专家看了都会流泪。
--- maven 烦不胜烦，亲眼看到很多的team和项目被这个鬼东西折腾得死去活来。 经常陷入无穷无尽的冲突和动荡之中，调来改去
努力改了半天改好了，后面人稍微一改动，又不行了。 高手坐镇，仍旧半死不活。
--- 做开发的应该永远不考虑或改动任何pom问题， pom 有任何问题，直接丢给架构师。
--- 那么多好玩有趣的技术和新概念没时间去接触， 非要就着这个破玩意反复折腾， 真的要吐了。

java之父看了都摇头。 把时间全部浪费在一些不是很重要的技术、歪门邪道之上，真的是国内技术人的悲哀。

--- 启动应用快一点不好吗？ 就不能愉快的玩耍吗？ 编程就不能爽到飞起吗？ 

[//]: # (很明显，全部整合到一起更加有利于学习！ 否则，效率会非常的低下。)


单独一个 api 工程？ 不需要的！ 动不动这里一个maven工程，那里一个api maven工程，看到就烦死。

--- 你是想让我精通maven吗？ 抱歉， 那玩意实在无趣啊。。 那破玩意维护起来极其的麻烦、 费力不讨好。。

莫名其妙的复杂度！ 复杂到一定程度，就是无穷尽的报错、异常。 低级。。
简单问题复杂化，真的是草泥马。 浪费我多少的青春啊！~ 哥都老了，还在crud

--- 一个小小问题卡住半天，重试一次又得10分钟起步。 一天不知不觉就过去了。。

--- 分那么细干嘛呀！
几十个pom.xml ， 虽然不复杂，但是也不简单啊！ 这种错综复杂的依赖关系，是专坑国人的吗？

说你没有做什么，你却说做了很多，仔细一看，确实很多各种各样的代码、xml，但是这些pom 跟我的业务交付有半毛钱关系？
总的看起来， 其实产出的很少，更多的时候只是做一些屌丝炫技的自欺欺人的技术工作。

# 加速前进

## 加快开发、测试速度
不要一个简单功能搞半天！


## 加快启动、运行速度
为什么idea+启动的时候需要进行长时间的+indexing+library 


# 分页
分页 要革命！ 不需要精准的分页，而是！
