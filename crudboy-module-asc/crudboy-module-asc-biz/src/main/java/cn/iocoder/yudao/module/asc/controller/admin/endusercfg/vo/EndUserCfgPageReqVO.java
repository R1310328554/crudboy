package cn.iocoder.yudao.module.asc.controller.admin.endusercfg.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

@Schema(description = "ASC接口 - 终端用户配置分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class EndUserCfgPageReqVO extends PageParam {

    @Schema(description = "创建者", example = "张三")
    private String name;

}
