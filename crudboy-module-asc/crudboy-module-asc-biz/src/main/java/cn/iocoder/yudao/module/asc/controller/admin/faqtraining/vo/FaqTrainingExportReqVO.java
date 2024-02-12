package cn.iocoder.yudao.module.asc.controller.admin.faqtraining.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import java.math.BigDecimal;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import java.time.LocalDateTime;
import java.time.LocalDate;
import java.time.LocalDateTime;
import org.springframework.format.annotation.DateTimeFormat;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 百问百答训练 Excel 导出 Request VO，参数和 FaqTrainingPageReqVO 是一致的")
@Data
public class FaqTrainingExportReqVO {

    @Schema(description = "问题")
    private String question;

    @Schema(description = "答案")
    private String answer;

    @Schema(description = "训练状态， 如训练失败， 训练完成", example = "1")
    private String trainingStatus;

    @Schema(description = "训练的当前问题的Tokens总数量")
    private Long tokenCnt;

    @Schema(description = "备注", example = "你猜")
    private String remark;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}
