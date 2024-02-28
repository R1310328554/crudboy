package cn.iocoder.yudao.module.asc.controller.admin.enduser.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.*;
import java.math.BigDecimal;

@Schema(description = "ASC接口 - 终端用户创建 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class EndUserCreateReqVO extends EndUserBaseVO {

}
