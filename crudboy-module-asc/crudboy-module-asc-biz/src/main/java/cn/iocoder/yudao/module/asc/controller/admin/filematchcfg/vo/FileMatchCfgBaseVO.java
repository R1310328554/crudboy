package cn.iocoder.yudao.module.asc.controller.admin.filematchcfg.vo;

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
 * 关键信息和文件链接匹配 Base VO，提供给添加、修改、详细的子 VO 使用
 * 如果子 VO 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成
 */
@Data
public class FileMatchCfgBaseVO {

    @Schema(description = "比如， 方案1、2 这样的名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "李四")
    @NotNull(message = "比如， 方案1、2 这样的名称不能为空")
    private String name;

    @Schema(description = "触发关键信息，只有聊天信息匹配这些意图， 那么下一步发送文件， 多个问题以换行符分割", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "触发关键信息，只有聊天信息匹配这些意图， 那么下一步发送文件， 多个问题以换行符分割不能为空")
    private String questions;

    @Schema(description = "匹配到关键信息后发送的文件， 多个文件以逗号分割")
    private String sendFiles;

    @Schema(description = "匹配到关键信息后发送的链接，如自己的小程序的链接或url， 多个文件以逗号分割")
    private String sendLinks;

    @Schema(description = "备注", example = "你说的对")
    private String remark;

}
