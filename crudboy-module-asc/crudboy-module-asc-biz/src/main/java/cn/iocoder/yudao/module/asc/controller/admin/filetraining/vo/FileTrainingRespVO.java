package cn.iocoder.yudao.module.asc.controller.admin.filetraining.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalDate;

import java.time.LocalDateTime;

@Schema(description = "ASC接口 - 文档训练 Response VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class FileTrainingRespVO extends FileTrainingBaseVO {

    @Schema(description = "训练状态， 如训练失败， 训练完成", example = "1")
    private String trainingStatus;

    @Schema(description = "训练的当前文档的Tokens总数量")
    private Long tokenCnt;


    @Schema(description = "主键", requiredMode = Schema.RequiredMode.REQUIRED, example = "1163")
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
