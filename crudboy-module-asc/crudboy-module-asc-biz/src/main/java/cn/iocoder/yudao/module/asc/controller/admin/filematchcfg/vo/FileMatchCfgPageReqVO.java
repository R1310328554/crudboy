package cn.iocoder.yudao.module.asc.controller.admin.filematchcfg.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

@Schema(description = "管理后台 - 关键信息和文件链接匹配分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class FileMatchCfgPageReqVO extends PageParam {

    @Schema(description = "比如， 方案1、2 这样的名称", example = "李四")
    private String name;

}
