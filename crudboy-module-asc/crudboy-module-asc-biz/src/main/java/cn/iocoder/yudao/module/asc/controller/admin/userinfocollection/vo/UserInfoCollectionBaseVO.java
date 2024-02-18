package cn.iocoder.yudao.module.asc.controller.admin.userinfocollection.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalDate;

import java.time.LocalDateTime;
import java.time.LocalDateTime;
import javax.validation.constraints.*;

/**
 * 客户信息收集 Base VO，提供给添加、修改、详细的子 VO 使用
 * 如果子 VO 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成
 */
@Data
public class UserInfoCollectionBaseVO {

    @Schema(description = "客户信息收集提示信息", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "客户信息收集提示信息不能为空")
    private String needAskUser;

    @Schema(description = "客户信息收集提示信息-类型, 姓名|电话|微信, 多选一", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    @NotNull(message = "客户信息收集提示信息-类型, 姓名|电话|微信, 多选一不能为空")
    private String askType;

    @Schema(description = "备注", example = "你猜")
    private String remark;

}
