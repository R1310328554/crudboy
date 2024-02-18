package cn.iocoder.yudao.module.asc.controller.admin.whatsappcfg.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

@Schema(description = "管理后台 - WhatsApp 平台配置分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class WhatsappCfgPageReqVO extends PageParam {

    @Schema(description = "创建者", example = "赵六")
    private String name;

}
