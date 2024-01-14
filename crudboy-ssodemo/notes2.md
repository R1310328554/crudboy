为什么没有修改， 却有 changes？


C:\Users\xd\.jdks\corretto-1.8.0_342\bin\java.exe -Dmaven.multiModuleProjectDirectory=D:\d\git\crudboy\crudboy-framework -Djansi.passthrough=true "-Dmaven.home=D:\d\tool\idea\IntelliJ IDEA 2022.3.2\plugins\maven\lib\maven3" "-Dclassworlds.conf=D:\d\tool\idea\IntelliJ IDEA 2022.3.2\plugins\maven\lib\maven3\bin\m2.conf" "-Dmaven.ext.class.path=D:\d\tool\idea\IntelliJ IDEA 2022.3.2\plugins\maven\lib\maven-event-listener.jar" "-javaagent:D:\d\tool\idea\IntelliJ IDEA 2022.3.2\lib\idea_rt.jar=53713:D:\d\tool\idea\IntelliJ IDEA 2022.3.2\bin" -Dfile.encoding=UTF-8 -classpath "D:\d\tool\idea\IntelliJ IDEA 2022.3.2\plugins\maven\lib\maven3\boot\plexus-classworlds-2.6.0.jar;D:\d\tool\idea\IntelliJ IDEA 2022.3.2\plugins\maven\lib\maven3\boot\plexus-classworlds.license" org.codehaus.classworlds.Launcher -Didea.version=2022.3.2 -DskipTests=true package
[INFO] Scanning for projects...
[WARNING]
[WARNING] Some problems were encountered while building the effective model for com.luokup:crudboy-framework:jar:0.0.1-SNAPSHOT
[WARNING] 'dependencies.dependency.systemPath' for com.lk:ww:jar should use a variable instead of a hard-coded path D:/d/xg/yjgl/edu.mit.jwi_2.4.0_jdk.jar @ line 598, column 25
[WARNING] 'dependencies.dependency.(groupId:artifactId:type:classifier)' must be unique: org.projectlombok:lombok:jar -> duplicate declaration of version (?) @ line 766, column 21
[WARNING] 'dependencies.dependency.(groupId:artifactId:type:classifier)' must be unique: com.github.binarywang:weixin-java-pay:jar -> duplicate declaration of version (?) @ line 994, column 21
[WARNING] 'dependencies.dependency.(groupId:artifactId:type:classifier)' must be unique: org.springdoc:springdoc-openapi-ui:jar -> duplicate declaration of version (?) @ line 1013, column 21
[WARNING] 'dependencies.dependency.(groupId:artifactId:type:classifier)' must be unique: org.apache.skywalking:apm-toolkit-trace:jar -> duplicate declaration of version (?) @ line 1020, column 21
[WARNING] 'dependencies.dependency.(groupId:artifactId:type:classifier)' must be unique: org.projectlombok:lombok:jar -> duplicate declaration of version (?) @ line 1026, column 21
[WARNING] 'dependencies.dependency.(groupId:artifactId:type:classifier)' must be unique: org.mapstruct:mapstruct:jar -> duplicate declaration of version (?) @ line 1031, column 21
[WARNING] 'dependencies.dependency.(groupId:artifactId:type:classifier)' must be unique: org.mapstruct:mapstruct-jdk8:jar -> duplicate declaration of version (?) @ line 1035, column 21
[WARNING] 'dependencies.dependency.(groupId:artifactId:type:classifier)' must be unique: org.mapstruct:mapstruct-processor:jar -> duplicate declaration of version (?) @ line 1039, column 21
[WARNING] 'dependencies.dependency.(groupId:artifactId:type:classifier)' must be unique: com.google.guava:guava:jar -> duplicate declaration of version (?) @ line 1044, column 21
[WARNING] 'dependencies.dependency.(groupId:artifactId:type:classifier)' must be unique: com.fasterxml.jackson.core:jackson-databind:jar -> duplicate declaration of version (?) @ line 1050, column 21
[WARNING] 'dependencies.dependency.(groupId:artifactId:type:classifier)' must be unique: com.fasterxml.jackson.core:jackson-core:jar -> duplicate declaration of version (?) @ line 1055, column 21
[WARNING] 'dependencies.dependency.(groupId:artifactId:type:classifier)' must be unique: org.slf4j:slf4j-api:jar -> duplicate declaration of version (?) @ line 1066, column 21
[WARNING] 'dependencies.dependency.(groupId:artifactId:type:classifier)' must be unique: cn.hutool:hutool-all:jar -> duplicate declaration of version (?) @ line 1078, column 21
[WARNING] 'dependencies.dependency.(groupId:artifactId:type:classifier)' must be unique: com.alibaba:transmittable-thread-local:jar -> duplicate declaration of version (?) @ line 1083, column 21
[WARNING] 'dependencies.dependency.(groupId:artifactId:type:classifier)' must be unique: com.dameng:DmJdbcDriver18:jar -> duplicate declaration of version (?) @ line 1114, column 21
[WARNING] 'dependencies.dependency.(groupId:artifactId:type:classifier)' must be unique: com.alibaba:druid-spring-boot-starter:jar -> duplicate declaration of version (?) @ line 1118, column 21
[WARNING] 'dependencies.dependency.(groupId:artifactId:type:classifier)' must be unique: com.baomidou:mybatis-plus-boot-starter:jar -> duplicate declaration of version (?) @ line 1122, column 21
[WARNING] 'dependencies.dependency.(groupId:artifactId:type:classifier)' must be unique: com.baomidou:dynamic-datasource-spring-boot-starter:jar -> duplicate declaration of version (?) @ line 1126, column 21
[WARNING] 'dependencies.dependency.(groupId:artifactId:type:classifier)' must be unique: com.github.yulichang:mybatis-plus-join-boot-starter:jar -> duplicate declaration of version (?) @ line 1131, column 21
[WARNING] 'dependencies.dependency.(groupId:artifactId:type:classifier)' must be unique: com.aliyun:aliyun-java-sdk-core:jar -> version ${aliyun-java-sdk-core.version} vs (?) @ line 1142, column 21
[WARNING] 'dependencies.dependency.(groupId:artifactId:type:classifier)' must be unique: com.aliyun:aliyun-java-sdk-dysmsapi:jar -> version ${aliyun-java-sdk-dysmsapi.version} vs (?) @ line 1152, column 21
[WARNING] 'dependencies.dependency.(groupId:artifactId:type:classifier)' must be unique: com.tencentcloudapi:tencentcloud-sdk-java-sms:jar -> version ${tencentcloud-sdk-java.version} vs (?) @ line 1187, column 21
[WARNING] 'dependencies.dependency.(groupId:artifactId:type:classifier)' must be unique: cn.hutool:hutool-all:jar -> version (?) vs 5.8.20 @ line 1202, column 21
[WARNING] 'dependencies.dependency.(groupId:artifactId:type:classifier)' must be unique: com.xingyuv:spring-boot-starter-justauth:jar -> version ${justauth.version} vs (?) @ line 1223, column 21
[WARNING] 'dependencyManagement.dependencies.dependency.(groupId:artifactId:type:classifier)' must be unique: org.webjars:webjars-locator-core:jar -> duplicate declaration of version 0.50 @ line 525, column 25
[WARNING] 'build.plugins.plugin.version' for org.apache.maven.plugins:maven-compiler-plugin is missing. @ line 1287, column 21
[WARNING]
[WARNING] It is highly recommended to fix these problems because they threaten the stability of your build.
[WARNING]
[WARNING] For this reason, future Maven versions might no longer support building such malformed projects.
[WARNING]
[INFO]
[INFO] --------------------< com.luokup:crudboy-framework >--------------------
[INFO] Building crudboy-framework 0.0.1-SNAPSHOT
[INFO] --------------------------------[ jar ]---------------------------------
[INFO]
[INFO] --- maven-resources-plugin:2.6:resources (default-resources) @ crudboy-framework ---
[INFO] Using 'UTF-8' encoding to copy filtered resources.
[INFO] Copying 106 resources
[INFO]
[INFO] --- maven-compiler-plugin:3.1:compile (default-compile) @ crudboy-framework ---
[INFO] Changes detected - recompiling the module!
[INFO] Compiling 1209 source files to D:\d\git\crudboy\crudboy-framework\target\classes
[WARNING] /D:/d/git/crudboy/crudboy-framework/src/main/java/org/springframework/web/servlet/mvc/method/annotation/RequestMappingHandlerMapping.java: 某些输入文件使用或覆盖了已过时的 API。
[WARNING] /D:/d/git/crudboy/crudboy-framework/src/main/java/org/springframework/web/servlet/mvc/method/annotation/RequestMappingHandlerMapping.java: 有关详细信息, 请使用 -Xlint:deprecation 重新编译。
[WARNING] /D:/d/git/crudboy/crudboy-framework/src/main/java/cn/iocoder/yudao/module/system/controller/admin/oauth2/OAuth2OpenController.java: 某些输入文件使用了未经检查或不安全的操作。
[WARNING] /D:/d/git/crudboy/crudboy-framework/src/main/java/cn/iocoder/yudao/module/system/controller/admin/oauth2/OAuth2OpenController.java: 有关详细信息, 请使用 -Xlint:unchecked 重新编译。
[INFO]
[INFO] --- maven-resources-plugin:2.6:testResources (default-testResources) @ crudboy-framework ---
[INFO] Using 'UTF-8' encoding to copy filtered resources.
[INFO] Copying 9 resources
[INFO]
[INFO] --- maven-compiler-plugin:3.1:testCompile (default-testCompile) @ crudboy-framework ---
[INFO] Changes detected - recompiling the module!
[INFO] Compiling 77 source files to D:\d\git\crudboy\crudboy-framework\target\test-classes
[WARNING] /D:/d/git/crudboy/crudboy-framework/src/test/java/cn/iocoder/yudao/framework/sms/core/client/impl/aliyun/AliyunSmsClientTest.java: D:\d\git\crudboy\crudboy-framework\src\test\java\cn\iocoder\yudao\framework\sms\core\client\impl\aliyun\AliyunSmsClientTest.java使用了未经检查或不安全的操作。
[WARNING] /D:/d/git/crudboy/crudboy-framework/src/test/java/cn/iocoder/yudao/framework/sms/core/client/impl/aliyun/AliyunSmsClientTest.java: 有关详细信息, 请使用 -Xlint:unchecked 重新编译。
[INFO]
[INFO] --- maven-surefire-plugin:2.12.4:test (default-test) @ crudboy-framework ---
[INFO] Tests are skipped.
[INFO]
[INFO] --- maven-jar-plugin:2.4:jar (default-jar) @ crudboy-framework ---
[INFO] Building jar: D:\d\git\crudboy\crudboy-framework\target\crudboy-framework.jar
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time:  01:41 min
[INFO] Finished at: 2024-01-05T13:22:28+08:00
[INFO] ------------------------------------------------------------------------

Process finished with exit code 0
