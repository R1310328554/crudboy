package cn.iocoder.yudao.module.asc.controller.admin.websitetraining.vo;

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

@Schema(description = "ASC接口 - 网站内容爬取训练 Excel 导出 Request VO，参数和 WebsiteTrainingPageReqVO 是一致的")
@Data
public class WebsiteTrainingExportReqVO {

    @Schema(description = "网站url", example = "https://www.qq.com")
    private String url;

    @Schema(description = "训练状态， 如训练失败， 训练完成", example = "2")
    private String trainingStatus;

    @Schema(description = "训练的当前网站的Tokens总数量")
    private Long tokenCnt;

    @Schema(description = "耗时，总共时间跨度(秒)")
    private Integer rateLimitDuration;

    @Schema(description = "备注", example = "你说的对")
    private String remark;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}
