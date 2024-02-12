package cn.iocoder.yudao.module.asc.controller.admin.consumedtokens.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalDate;

import javax.validation.constraints.*;

/**
 * 租户的token消耗情况 Base VO，提供给添加、修改、详细的子 VO 使用
 * 如果子 VO 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成
 */
@Data
public class ConsumedTokensBaseVO {

    @Schema(description = "聊天消耗的Tokens总数量, 动态更新", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "聊天消耗的Tokens总数量, 动态更新不能为空")
    private Long totalChatTokenCnt;

    @Schema(description = "受训文件消耗的Tokens总数量, 动态更新", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "受训文件消耗的Tokens总数量, 动态更新不能为空")
    private Long totalFileTokenCnt;

    @Schema(description = "受训网站消耗的Tokens总数量, 动态更新", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "受训网站消耗的Tokens总数量, 动态更新不能为空")
    private Long totalWebsiteTokenCnt;

    @Schema(description = "受训百问百答消耗的Tokens总数量, 动态更新", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "受训百问百答消耗的Tokens总数量, 动态更新不能为空")
    private Long totalFaqTokenCnt;

}
