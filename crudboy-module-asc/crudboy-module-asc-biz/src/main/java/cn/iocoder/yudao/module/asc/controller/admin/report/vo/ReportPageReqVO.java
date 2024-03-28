package cn.iocoder.yudao.module.asc.controller.admin.report.vo;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Schema(description = "ASC接口 - 机器人的基础配置分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ReportPageReqVO extends PageParam {

    @Schema(description = "机器人代号")
    private String code;

    @Schema(description = "机器人名称", example = "王五")
    private String name;

    @Schema(description = "0草稿、1上岗、2下岗、3维护中、4删除、", example = "2")
    private String status;

}
