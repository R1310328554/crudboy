package cn.iocoder.yudao.module.asc.controller.admin.report.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.validation.constraints.NotNull;


@Schema(description = "ASC接口 - 机器人的基础配置更新 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ReportUpdateReqVO extends ReportBaseVO {

    @Schema(description = "主键， 机器人id", requiredMode = Schema.RequiredMode.REQUIRED, example = "4029")
    @NotNull(message = "主键， 机器人id不能为空")
    private Long id;

}
