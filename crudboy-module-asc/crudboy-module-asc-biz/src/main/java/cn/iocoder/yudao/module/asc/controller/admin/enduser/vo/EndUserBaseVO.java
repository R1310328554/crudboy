package cn.iocoder.yudao.module.asc.controller.admin.enduser.vo;

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
 * 终端用户 Base VO，提供给添加、修改、详细的子 VO 使用
 * 如果子 VO 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成
 */
@Data
public class EndUserBaseVO {

    @Schema(description = "用户名", example = "李四")
    private String username;

    @Schema(description = "用户昵称", example = "李四")
    private String nickname;

    @Schema(description = "用户头像")
    private String avatar;

    @Schema(description = "在第三方平台类型上的名称", example = "李四")
    private String thirdPartyName;

    @Schema(description = "第三方平台类型，即用户使用的微信还是WhatsApp..", example = "2")
    private String thirdPartyType;

    @Schema(description = "在第三方平台上的id，比如微信的openid、unionid WhatsApp_id 等等", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "在第三方平台上的id不能为空")
    private String thirdPartyId;

    @Schema(description = "备注", example = "你猜")
    private String remark;

}
