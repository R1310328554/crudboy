package cn.iocoder.yudao.module.asc.controller.admin.afterwardscallbackcfg.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalDate;


@Schema(description = "ASC接口 - 后续主动沟通的询问方案更新 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class AfterwardsCallbackCfgUpdateReqVO extends AfterwardsCallbackCfgBaseVO {

    @Schema(description = "主键", requiredMode = Schema.RequiredMode.REQUIRED, example = "16767")
    @NotNull(message = "主键不能为空")
    private Long id;

}
