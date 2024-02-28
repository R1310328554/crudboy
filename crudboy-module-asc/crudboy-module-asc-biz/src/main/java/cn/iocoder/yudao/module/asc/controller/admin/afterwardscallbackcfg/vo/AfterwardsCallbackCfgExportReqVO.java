package cn.iocoder.yudao.module.asc.controller.admin.afterwardscallbackcfg.vo;

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

@Schema(description = "ASC接口 - 后续主动沟通的询问方案 Excel 导出 Request VO，参数和 AfterwardsCallbackCfgPageReqVO 是一致的")
@Data
public class AfterwardsCallbackCfgExportReqVO {

    @Schema(description = "比如， 方案1、2 这样的名称", example = "张三")
    private String name;

    @Schema(description = "后续主动沟通的信息")
    private String afterwardsReply;

    @Schema(description = "接触时机, 最后沟通后的多少小时后？")
    private Integer callbackDelay;

    @Schema(description = "是否需要结合所有上下文, Y or N")
    private String isNeedCtx;

    @Schema(description = "备注", example = "你说的对")
    private String remark;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}
