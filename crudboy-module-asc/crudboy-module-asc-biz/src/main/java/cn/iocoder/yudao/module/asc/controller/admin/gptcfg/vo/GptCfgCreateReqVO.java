package cn.iocoder.yudao.module.asc.controller.admin.gptcfg.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.*;
import java.math.BigDecimal;

@Schema(description = "管理后台 - GPT 专用的基础的配置创建 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class GptCfgCreateReqVO extends GptCfgBaseVO {

}
