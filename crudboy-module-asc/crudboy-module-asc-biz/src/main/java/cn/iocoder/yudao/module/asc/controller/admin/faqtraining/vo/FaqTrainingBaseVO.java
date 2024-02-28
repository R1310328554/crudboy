package cn.iocoder.yudao.module.asc.controller.admin.faqtraining.vo;

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
 * 百问百答训练 Base VO，提供给添加、修改、详细的子 VO 使用
 * 如果子 VO 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成
 */
@Data
public class FaqTrainingBaseVO {

    @Schema(description = "问题", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "问题不能为空")
    private String question;

    @Schema(description = "答案", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "答案不能为空")
    private String answer;

    @Schema(description = "训练状态， 如训练失败， 训练完成", example = "1")
    private String trainingStatus;

    @Schema(description = "训练的当前问题的Tokens总数量")
    private Long tokenCnt;

    @Schema(description = "标签", example = "销售知识库")
    private String tag;

    @Schema(description = "备注", example = "你猜")
    private String remark;

}
