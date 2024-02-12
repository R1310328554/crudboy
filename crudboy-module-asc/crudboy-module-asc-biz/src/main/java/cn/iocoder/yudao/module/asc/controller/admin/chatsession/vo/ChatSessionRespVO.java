package cn.iocoder.yudao.module.asc.controller.admin.chatsession.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalDate;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - 聊天的会话 Response VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ChatSessionRespVO extends ChatSessionBaseVO {

    @Schema(description = "聊天的会话id", requiredMode = Schema.RequiredMode.REQUIRED, example = "4633")
    private Long id;

    @Schema(description = "创建者")
    private String creator;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    private LocalDateTime createTime;

    @Schema(description = "更新者")
    private String updater;

    @Schema(description = "更新时间", requiredMode = Schema.RequiredMode.REQUIRED)
    private LocalDateTime updateTime;

}
