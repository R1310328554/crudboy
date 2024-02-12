package cn.iocoder.yudao.module.asc.controller.admin.filematchcfg.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.*;
import java.math.BigDecimal;

@Schema(description = "管理后台 - 关键信息和文件链接匹配创建 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class FileMatchCfgCreateReqVO extends FileMatchCfgBaseVO {

}
