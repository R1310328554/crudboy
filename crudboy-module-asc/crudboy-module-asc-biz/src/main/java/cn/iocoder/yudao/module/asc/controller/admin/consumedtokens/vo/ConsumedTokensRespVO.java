package cn.iocoder.yudao.module.asc.controller.admin.consumedtokens.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalDate;


@Schema(description = "ASC接口 - 租户的token消耗情况 Response VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ConsumedTokensRespVO extends ConsumedTokensBaseVO {

    @Schema(description = "主键, 流水号", requiredMode = Schema.RequiredMode.REQUIRED, example = "1188")
    private Long id;

}
