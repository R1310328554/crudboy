package cn.iocoder.yudao.module.asc.controller.admin.filematchcfg.vo;

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

@Schema(description = "ASC接口 - 关键信息和文件链接匹配 Excel 导出 Request VO，参数和 FileMatchCfgPageReqVO 是一致的")
@Data
public class FileMatchCfgExportReqVO {

    @Schema(description = "比如， 方案1、2 这样的名称", example = "李四")
    private String name;

    @Schema(description = "触发关键信息，只有聊天信息匹配这些意图， 那么下一步发送文件， 多个问题以换行符分割")
    private String questions;

    @Schema(description = "匹配到关键信息后发送的文件， 多个文件以逗号分割")
    private String sendFiles;

    @Schema(description = "匹配到关键信息后发送的链接，如自己的小程序的链接或url， 多个文件以逗号分割")
    private String sendLinks;

    @Schema(description = "备注", example = "你说的对")
    private String remark;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}
