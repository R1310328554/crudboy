package cn.iocoder.yudao.module.asc.controller.admin.whatsappcfg.vo;

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

@Schema(description = "管理后台 - WhatsApp 平台配置 Excel 导出 Request VO，参数和 WhatsappCfgPageReqVO 是一致的")
@Data
public class WhatsappCfgExportReqVO {

    @Schema(description = "创建者", example = "赵六")
    private String name;

    @Schema(description = "创建者")
    private String image;

    @Schema(description = "Wechat/Wechatcompany/WhatsApp", example = "1")
    private String thirdPartyType;

    @Schema(description = "key")
    private String key;

    @Schema(description = "secret")
    private String secret;

    @Schema(description = "secret", example = "张三")
    private String companyName;

    @Schema(description = "secret", example = "https://www.qq.com")
    private String companyUrl;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}
