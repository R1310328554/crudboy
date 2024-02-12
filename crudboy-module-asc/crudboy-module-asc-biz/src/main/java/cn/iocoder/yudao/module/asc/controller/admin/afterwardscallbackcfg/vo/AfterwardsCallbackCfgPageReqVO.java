package cn.iocoder.yudao.module.asc.controller.admin.afterwardscallbackcfg.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

@Schema(description = "管理后台 - 后续主动沟通的询问方案分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class AfterwardsCallbackCfgPageReqVO extends PageParam {

    @Schema(description = "比如， 方案1、2 这样的名称", example = "张三")
    private String name;

}
