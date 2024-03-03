package cn.iocoder.yudao.module.asc.controller.admin.filetraining.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalDate;


@Schema(description = "ASC接口 - 文档训练更新 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class FileTrainingUpdateReqVO extends FileTrainingBaseVO {

    @Schema(description = "主键", requiredMode = Schema.RequiredMode.REQUIRED, example = "1163")
    @NotNull(message = "主键不能为空")
    private Long id;

    @Schema(description = "训练状态， 如训练失败， 训练完成", example = "1")
    private String trainingStatus;

    @Schema(description = "训练的当前文档的Tokens总数量")
    private Long tokenCnt;
}
