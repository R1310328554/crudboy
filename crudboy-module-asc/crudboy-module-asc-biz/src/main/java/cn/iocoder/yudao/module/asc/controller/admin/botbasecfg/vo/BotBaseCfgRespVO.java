package cn.iocoder.yudao.module.asc.controller.admin.botbasecfg.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalDate;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - 机器人的基础配置 Response VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class BotBaseCfgRespVO extends BotBaseCfgBaseVO {

    @Schema(description = "主键， 机器人id", requiredMode = Schema.RequiredMode.REQUIRED, example = "4029")
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
