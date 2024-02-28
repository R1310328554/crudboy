package cn.iocoder.yudao.module.asc.controller.admin.chatsession.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalDate;


@Schema(description = "ASC接口 - 聊天的会话更新 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ChatSessionUpdateReqVO extends ChatSessionBaseVO {

    @Schema(description = "聊天的会话id", requiredMode = Schema.RequiredMode.REQUIRED, example = "4633")
    @NotNull(message = "聊天的会话id不能为空")
    private Long id;

}
