** 应急管理局 **

## 🐶 需求列表



-Dyjgl.cron.stat="10 */1 * * * ?"  -Dspring.profiles.active2=test2  -Dspring.profiles.active=local -Dspring.redis.password=123456
 

java   -Dspring.profiles.active=local  -Dspring.redis.password=123456 
-Dspring.datasource.dynamic.datasource.master.username=yjgldb   -Dspring.datasource.dynamic.datasource.master.password=yjgldb   
-Dspring.datasource.dynamic.datasource.slave.username=yjgldb   -Dspring.datasource.dynamic.datasource.slave.password=yjgldb   
-jar  crudboy-framework.jar

 
jar xf crudboy-framework.jar
mv -f yudao-module-yjgl-biz-1.8.0-snapshot.jar BOOT-INF/lib/
jar cf0M crudboy-framework.jar BOOT-INF META-INF org
./server.sh restart

不需要保留。 直接mv即可
# /bin/cp  -f yudao-module-yjgl-biz-1.8.0-snapshot.jar BOOT-INF/lib/


因为   nohup java -cp yudao-module-yjgl-biz-1.8.0-snapshot.jar  -jar $args $app  >yjgl.log  2>&1    &
其实， 似乎可以不用 jar cf0M yjgl.jar BOOT-INF META-INF org ！！


# todo：
出错的时候， 需要给前端返回更加友好、准确的错误提示。

云上直接codegen（ 需要一个 云开发环境、 云中有源代码）、下载、编译、打包 、安装代码、自动重启、热启动



[//]: # (调查人、单位、时间， 应该是不可以编辑的 ， 只能查看。。。)


-- 我不能快速的 静态的 生成代码吗？ 非要启动服务器、 前后端 吗？



我后台看到这个错， 这个通常是因为。。
Caused by: com.mysql.cj.exceptions.DataConversionException: Cannot determine value type from string '林业局'

为何 mvn clean 之后， infra 需要先单独的进行编译？

每次都在这个日期格式上遇到问题， 真的是烦。。 为什么不能一劳永逸的彻底的处理好？
date-format: yyyy-MM-dd HH:mm:ss


// 能不能一次性搞定啊， 别反复调试 修改、 上传、测试。。。




Caused by: org.springframework.beans.factory.UnsatisfiedDependencyException: Error creating bean with name 'repositoryServiceBean' defined in class path resource [org/flowable/spring/boot/ProcessEngineServicesAutoConfiguration.class]: Unsatisfied dependency expressed through method 'repositoryServiceBean' parameter 0; nested exception is org.springframework.beans.factory.BeanCreationException: Error creating bean with name 'processEngine': FactoryBean threw exception on object creation; nested exception is org.flowable.common.engine.api.FlowableWrongDbException: version mismatch: library version is '6.8.0.0', db version is 5.99.0.0 Hint: Set <property name="databaseSchemaUpdate" to value="true" or value="create-drop" (use create-drop for testing only!) in bean processEngineConfiguration in flowable.cfg.xml for automatic schema creation
at org.springframework.beans.factory.support.ConstructorResolver.createArgumentArray(ConstructorResolver.java:800)

