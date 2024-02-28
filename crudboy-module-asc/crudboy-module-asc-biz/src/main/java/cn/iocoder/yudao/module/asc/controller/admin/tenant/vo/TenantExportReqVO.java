package cn.iocoder.yudao.module.asc.controller.admin.tenant.vo;

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

@Schema(description = "ASC接口 - 租户 Excel 导出 Request VO，参数和 TenantPageReqVO 是一致的")
@Data
public class TenantExportReqVO {

    @Schema(description = "租户名", example = "李四")
    private String name;

    @Schema(description = "租户级别， 黄金、白银、青铜")
    private String level;

    @Schema(description = "状态", example = "1")
    private String tStatus;

    @Schema(description = "问答token总数上限")
    private Long maxQuestionTokenCnt;

    @Schema(description = "可以发出的问题的最大数量")
    private Long maxQuestionCnt;

    @Schema(description = "文档token总数上限")
    private Long maxFileTokenCnt;

    @Schema(description = "可以上传的最大的文件的个数")
    private Long maxFileCnt;

    @Schema(description = "训练的文档类型, 多个以英文逗号分割")
    private String fileCategorys;

    @Schema(description = "支持的、可以进行训练的文档格式, 多个以英文逗号分割，.pdf  .doc  .txt")
    private String fileTypes;

    @Schema(description = "训练网站可以使用的token总数上限")
    private Long maxWebsiteTokenCnt;

    @Schema(description = "训练的url格式, 多个以英文逗号分割，.com .gov .cn  .top")
    private String websiteTypes;

    @Schema(description = "可以训练问题的Tokens总数量")
    private Long maxFaqTokenCnt;

    @Schema(description = "可以设置FAQ的问题的最大数量")
    private Long maxFaqCnt;

    @Schema(description = "C端用户数上限")
    private Long maxEndUser;

    @Schema(description = "...")
    private Long other;

    @Schema(description = "备注", example = "随便")
    private String remark;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}
