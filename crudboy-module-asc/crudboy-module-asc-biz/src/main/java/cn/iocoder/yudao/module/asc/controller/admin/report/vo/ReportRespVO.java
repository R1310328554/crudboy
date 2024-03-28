package cn.iocoder.yudao.module.asc.controller.admin.report.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.time.LocalDateTime;

@Schema(description = "ASC接口 - 机器人的基础配置 Response VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ReportRespVO extends ReportBaseVO {

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
