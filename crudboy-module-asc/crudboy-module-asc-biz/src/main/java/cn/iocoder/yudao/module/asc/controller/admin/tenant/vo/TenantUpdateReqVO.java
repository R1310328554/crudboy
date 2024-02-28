package cn.iocoder.yudao.module.asc.controller.admin.tenant.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalDate;


@Schema(description = "ASC接口 - 租户更新 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class TenantUpdateReqVO extends TenantBaseVO {

    @Schema(description = "主键，即租户id", requiredMode = Schema.RequiredMode.REQUIRED, example = "7684")
    @NotNull(message = "主键，即租户id不能为空")
    private Long id;

}
