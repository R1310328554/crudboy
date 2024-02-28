package cn.iocoder.yudao.module.asc.controller.admin.enduser.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalDate;


@Schema(description = "ASC接口 - 终端用户更新 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class EndUserUpdateReqVO extends EndUserBaseVO {

    @Schema(description = "主键，即用户id", requiredMode = Schema.RequiredMode.REQUIRED, example = "14299")
    @NotNull(message = "主键，即用户id不能为空")
    private Long id;

}
