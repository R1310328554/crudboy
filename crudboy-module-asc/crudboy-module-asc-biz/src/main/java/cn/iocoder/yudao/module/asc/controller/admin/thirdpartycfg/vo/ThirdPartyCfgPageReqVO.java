package cn.iocoder.yudao.module.asc.controller.admin.thirdpartycfg.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

@Schema(description = "ASC接口 - 终端平台即第三方平台配置分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ThirdPartyCfgPageReqVO extends PageParam {

    @Schema(description = "平台上的账号", example = "李四")
    private String name;

}
