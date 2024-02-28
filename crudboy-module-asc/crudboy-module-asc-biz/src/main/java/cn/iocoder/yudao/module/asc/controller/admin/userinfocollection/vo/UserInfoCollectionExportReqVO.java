package cn.iocoder.yudao.module.asc.controller.admin.userinfocollection.vo;

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

@Schema(description = "ASC接口 - 客户信息收集 Excel 导出 Request VO，参数和 UserInfoCollectionPageReqVO 是一致的")
@Data
public class UserInfoCollectionExportReqVO {

    @Schema(description = "客户信息收集提示信息")
    private String needAskUser;

    @Schema(description = "客户信息收集提示信息-类型, 姓名|电话|微信, 多选一", example = "2")
    private String askType;

    @Schema(description = "备注", example = "你猜")
    private String remark;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}
