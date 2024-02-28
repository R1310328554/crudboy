package cn.iocoder.yudao.module.asc.controller.admin.botbasecfg.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalDate;


@Schema(description = "ASC接口 - 机器人的基础配置更新 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class BotBaseCfgUpdateReqVO extends BotBaseCfgBaseVO {

    @Schema(description = "主键， 机器人id", requiredMode = Schema.RequiredMode.REQUIRED, example = "4029")
    @NotNull(message = "主键， 机器人id不能为空")
    private Long id;

}
