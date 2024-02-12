package cn.iocoder.yudao.module.asc.controller.admin.consumedtokens.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.*;
import java.math.BigDecimal;

@Schema(description = "管理后台 - 租户的token消耗情况创建 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ConsumedTokensCreateReqVO extends ConsumedTokensBaseVO {

}
