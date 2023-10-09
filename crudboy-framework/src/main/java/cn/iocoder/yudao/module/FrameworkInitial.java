package cn.iocoder.yudao.module;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ConfigurableBootstrapContext;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationContextInitializedEvent;
import org.springframework.boot.context.event.ApplicationEnvironmentPreparedEvent;
import org.springframework.boot.context.event.ApplicationPreparedEvent;
import org.springframework.boot.context.event.ApplicationStartingEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;

import static cn.iocoder.yudao.framework.common.exception.enums.GlobalErrorCodeConstants.NOT_IMPLEMENTED;

/**
 * 项目的启动类
 *
 *
 * @author luo
 */
public class FrameworkInitial  implements ApplicationListener<ApplicationContextInitializedEvent> {

    @Value("${yudao.web.methodNameAsPaths:true}")
    private boolean methodNameAsPaths;

    @Override
    public void onApplicationEvent(ApplicationContextInitializedEvent event) {
        System.out.println("=================启动========");
        ConfigurableApplicationContext applicationContext = event.getApplicationContext();
        String methodNameAsPaths2 = applicationContext.getEnvironment().getProperty("yudao.web.methodNameAsPaths");
        System.out.println("bootstrapContext = " + event);
        System.out.println("methodNameAsPaths = " + methodNameAsPaths); // 行不通， 因为不会扫描 FrameworkInitial 中的 @value
        System.out.println("methodNameAsPaths2 = " + methodNameAsPaths2);
//        RequestMappingInfo.methodNameAsPathsaa = Boolean.parseBoolean(methodNameAsPaths2);
    }
}
