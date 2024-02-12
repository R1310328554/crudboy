package cn.iocoder.yudao.module.asc.controller.admin.enduser.vo;

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

@Schema(description = "管理后台 - 终端用户 Excel 导出 Request VO，参数和 EndUserPageReqVO 是一致的")
@Data
public class EndUserExportReqVO {

    @Schema(description = "用户名", example = "李四")
    private String username;

    @Schema(description = "用户昵称", example = "李四")
    private String nickname;

    @Schema(description = "用户头像")
    private String avatar;

    @Schema(description = "在第三方平台类型上的名称", example = "李四")
    private String thirdPartyName;

    @Schema(description = "第三方平台类型，即用户使用的微信还是WhatsApp..", example = "2")
    private String thirdPartyType;

    @Schema(description = "备注", example = "你猜")
    private String remark;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}
