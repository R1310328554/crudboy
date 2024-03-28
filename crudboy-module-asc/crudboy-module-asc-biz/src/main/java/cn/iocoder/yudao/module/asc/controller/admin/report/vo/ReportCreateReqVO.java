package cn.iocoder.yudao.module.asc.controller.admin.report.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Schema(description = "ASC接口 - 机器人的基础配置创建 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ReportCreateReqVO extends ReportBaseVO {

}
