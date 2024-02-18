package cn.iocoder.yudao.module.asc.controller.admin.gptcfg.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalDate;


@Schema(description = "管理后台 - GPT 专用的基础的配置 Response VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class GptCfgRespVO extends GptCfgBaseVO {

    @Schema(description = "主键", requiredMode = Schema.RequiredMode.REQUIRED, example = "2639")
    private Long id;

}
