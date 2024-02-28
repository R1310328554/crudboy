package cn.iocoder.yudao.module.asc.controller.admin.websitetraining.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalDate;


@Schema(description = "ASC接口 - 网站内容爬取训练更新 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class WebsiteTrainingUpdateReqVO extends WebsiteTrainingBaseVO {

    @Schema(description = "主键", requiredMode = Schema.RequiredMode.REQUIRED, example = "28682")
    @NotNull(message = "主键不能为空")
    private Long id;

}
