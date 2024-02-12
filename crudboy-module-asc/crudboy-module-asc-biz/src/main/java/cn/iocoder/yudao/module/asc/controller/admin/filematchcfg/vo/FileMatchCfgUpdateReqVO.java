package cn.iocoder.yudao.module.asc.controller.admin.filematchcfg.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalDate;


@Schema(description = "管理后台 - 关键信息和文件链接匹配更新 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class FileMatchCfgUpdateReqVO extends FileMatchCfgBaseVO {

    @Schema(description = "主键", requiredMode = Schema.RequiredMode.REQUIRED, example = "14518")
    @NotNull(message = "主键不能为空")
    private Long id;

}
