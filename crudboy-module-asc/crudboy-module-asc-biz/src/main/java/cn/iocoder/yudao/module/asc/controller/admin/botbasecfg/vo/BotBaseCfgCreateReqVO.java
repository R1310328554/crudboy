package cn.iocoder.yudao.module.asc.controller.admin.botbasecfg.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.*;
import java.math.BigDecimal;

@Schema(description = "ASC接口 - 机器人的基础配置创建 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class BotBaseCfgCreateReqVO extends BotBaseCfgBaseVO {

}
