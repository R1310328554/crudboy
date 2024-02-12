package cn.iocoder.yudao.module.asc.controller.admin.chatmsg.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalDate;


@Schema(description = "管理后台 - 单个聊天消息更新 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ChatMsgUpdateReqVO extends ChatMsgBaseVO {

    @Schema(description = "主键", requiredMode = Schema.RequiredMode.REQUIRED, example = "16235")
    @NotNull(message = "主键不能为空")
    private Long id;

}
