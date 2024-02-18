package cn.iocoder.yudao.module.asc.controller.admin.thirdpartycfg.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalDate;

import java.time.LocalDateTime;
import java.time.LocalDateTime;
import javax.validation.constraints.*;

/**
 * 终端平台即第三方平台配置 Base VO，提供给添加、修改、详细的子 VO 使用
 * 如果子 VO 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成
 */
@Data
public class ThirdPartyCfgBaseVO {

    @Schema(description = "平台上的账号", example = "李四")
    private String name;

    @Schema(description = "平台上的头像")
    private String image;

    @Schema(description = "Wechat/Wechatcompany/WhatsApp", example = "2")
    private String thirdPartyType;

    @Schema(description = "key")
    private String key;

    @Schema(description = "secret")
    private String secret;

    @Schema(description = "企业名称", example = "赵六")
    private String companyName;

    @Schema(description = "企业url", example = "https://www.qq.com")
    private String companyUrl;

}
