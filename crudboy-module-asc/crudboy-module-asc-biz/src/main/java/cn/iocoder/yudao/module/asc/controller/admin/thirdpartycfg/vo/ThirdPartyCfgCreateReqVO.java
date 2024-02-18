package cn.iocoder.yudao.module.asc.controller.admin.thirdpartycfg.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.*;
import java.math.BigDecimal;

@Schema(description = "管理后台 - 终端平台即第三方平台配置创建 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ThirdPartyCfgCreateReqVO extends ThirdPartyCfgBaseVO {

}
