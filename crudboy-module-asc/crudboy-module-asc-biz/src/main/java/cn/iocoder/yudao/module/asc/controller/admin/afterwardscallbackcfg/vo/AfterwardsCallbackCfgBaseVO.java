package cn.iocoder.yudao.module.asc.controller.admin.afterwardscallbackcfg.vo;

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
 * 后续主动沟通的询问方案 Base VO，提供给添加、修改、详细的子 VO 使用
 * 如果子 VO 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成
 */
@Data
public class AfterwardsCallbackCfgBaseVO {

    @Schema(description = "比如， 方案1、2 这样的名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "张三")
    @NotNull(message = "比如， 方案1、2 这样的名称不能为空")
    private String name;

    @Schema(description = "后续主动沟通的信息", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "后续主动沟通的信息不能为空")
    private String afterwardsReply;

    @Schema(description = "接触时机, 最后沟通后的多少小时后？")
    private Integer callbackDelay;

    @Schema(description = "是否需要结合所有上下文, Y or N")
    private String isNeedCtx;

    @Schema(description = "备注", example = "你说的对")
    private String remark;

}
