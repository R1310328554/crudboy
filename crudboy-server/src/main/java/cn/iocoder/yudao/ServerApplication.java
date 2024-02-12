package cn.iocoder.yudao;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static cn.iocoder.yudao.framework.common.exception.enums.GlobalErrorCodeConstants.NOT_IMPLEMENTED;

/**
 * 项目的启动类
 *
 * @author 芋道源码
 */
@SuppressWarnings("SpringComponentScan") // 忽略 IDEA 无法识别 ${yudao.info.base-package}
@SpringBootApplication(scanBasePackages = {"${yudao.info.base-package}.framework", "${yudao.info.base-package}.module"})
//@SpringBootApplication
public class ServerApplication {

    public static void main(String[] args) {
        // 如果你碰到启动的问题，请认真阅读 https://doc.iocoder.cn/quick-start/ 文章
        // 如果你碰到启动的问题，请认真阅读 https://doc.iocoder.cn/quick-start/ 文章
        // 如果你碰到启动的问题，请认真阅读 https://doc.iocoder.cn/quick-start/ 文章

        SpringApplication.run(ServerApplication.class, args);
//        new SpringApplicationBuilder(YudaoServerApplication.class)
//                .applicationStartup(new BufferingApplicationStartup(20480))
//                .run(args);

        // 如果你碰到启动的问题，请认真阅读 https://doc.iocoder.cn/quick-start/ 文章
        // 如果你碰到启动的问题，请认真阅读 https://doc.iocoder.cn/quick-start/ 文章
        // 如果你碰到启动的问题，请认真阅读 https://doc.iocoder.cn/quick-start/ 文章
    }

    /**
     * 默认 Controller，解决部分 module 未开启时的 404 提示。
     * 例如说，/bpm/** 路径，工作流
     *
     * @author 芋道源码
     */
    @RestController
    public static class DefaultController {

        @RequestMapping("/admin-api/bpm/**")
        public CommonResult<Boolean> bpm404() {
            return CommonResult.error(NOT_IMPLEMENTED.getCode(),
                    "[工作流模块 yudao-module-bpm - 已禁用][参考 https://doc.iocoder.cn/bpm/ 开启]");
        }

        @RequestMapping("/admin-api/mp/**")
        public CommonResult<Boolean> mp404() {
            return CommonResult.error(NOT_IMPLEMENTED.getCode(),
                    "[微信公众号 yudao-module-mp - 已禁用][参考 https://doc.iocoder.cn/mp/build/ 开启]");
        }

        @RequestMapping(value = {"/admin-api/product/**", // 商品中心
                "/admin-api/trade/**", // 交易中心
                "/admin-api/promotion/**"})  // 营销中心
        public CommonResult<Boolean> mall404() {
            return CommonResult.error(NOT_IMPLEMENTED.getCode(),
                    "[商城系统 yudao-module-mall - 已禁用][参考 https://doc.iocoder.cn/mall/build/ 开启]");
        }

        @RequestMapping(value = {"/admin-api/report/**"})
        public CommonResult<Boolean> report404() {
            return CommonResult.error(NOT_IMPLEMENTED.getCode(),
                    "[报表模块 yudao-module-report - 已禁用][参考 https://doc.iocoder.cn/report/ 开启]");
        }

        @RequestMapping(value = {"/admin-api/pay/**"})
        public CommonResult<Boolean> pay404() {
            return CommonResult.error(NOT_IMPLEMENTED.getCode(),
                    "[支付模块 yudao-module-pay - 已禁用][参考 https://doc.iocoder.cn/pay/build/ 开启]");
        }


        @RequestMapping
        public CommonResult<Boolean> maaaaaaa() {
            return CommonResult.error(NOT_IMPLEMENTED.getCode(),
                    "[微信公众号 yudao-module-mp - 已禁用][参考 https://doc.iocoder.cn/mp/build/ 开启]");
        }

    }
}