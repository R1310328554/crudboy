package cn.iocoder.yudao.framework.sms.core.client;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import javax.validation.constraints.*;

/**
* msds 基本信息 Base VO，提供给添加、修改、详细的子 VO 使用
* 如果子 VO 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成
*/
@Data
public class MsdsBaseVO {

    @Schema(description = "序号", required = true)
    @NotNull(message = "序号不能为空")
    private String sort;

    @Schema(description = "编码", required = true)
    @NotNull(message = "编码不能为空")
    private String code;

    @Schema(description = "品名", required = true, example = "芋艿")
    @NotNull(message = "品名不能为空")
    private String name;

    @Schema(description = "别名", required = true)
    @NotNull(message = "别名不能为空")
    private String alias;

    @Schema(description = "CAS号", required = true)
    @NotNull(message = "CAS号不能为空")
    private String cas;

    @Schema(description = "备注", required = true, example = "你说的对")
    @NotNull(message = "备注不能为空")
    private String remark;

    @Schema(description = "类型id", required = true, example = "1")
    @NotNull(message = "类型id不能为空")
    private Byte type;

    @Schema(description = "是否可见", required = true)
    @NotNull(message = "是否可见不能为空")
    private Boolean visible;

    @Schema(description = "是否缓存", required = true)
    @NotNull(message = "是否缓存不能为空")
    private Boolean keepAlive;

    @Schema(description = "是否总是显示", required = true)
    @NotNull(message = "是否总是显示不能为空")
    private Boolean alwaysShow;

}
