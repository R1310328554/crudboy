package cn.iocoder.yudao.module.asc.controller.admin.filetraining.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.*;
import java.math.BigDecimal;

@Schema(description = "ASC接口 - 文档训练创建 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class FileTrainingCreateReqVO extends FileTrainingBaseVO {

    @Schema(description = "训练状态， 如训练失败， 训练完成", example = "1")
    private String trainingStatus;

    @Schema(description = "训练的当前文档的Tokens总数量")
    private Long tokenCnt;

}
