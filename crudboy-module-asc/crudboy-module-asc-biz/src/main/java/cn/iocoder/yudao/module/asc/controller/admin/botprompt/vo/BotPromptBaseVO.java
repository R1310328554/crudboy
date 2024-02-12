package cn.iocoder.yudao.module.asc.controller.admin.botprompt.vo;

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
 * 预设提示词 Base VO，提供给添加、修改、详细的子 VO 使用
 * 如果子 VO 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成
 */
@Data
public class BotPromptBaseVO {

    @Schema(description = "提示信息", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "提示信息不能为空")
    private String prompt;

    @Schema(description = "备注", example = "你说的对")
    private String remark;

}
