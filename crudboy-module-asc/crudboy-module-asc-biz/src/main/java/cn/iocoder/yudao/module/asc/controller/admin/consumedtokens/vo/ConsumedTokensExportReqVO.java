package cn.iocoder.yudao.module.asc.controller.admin.consumedtokens.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import java.math.BigDecimal;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import java.time.LocalDateTime;
import java.time.LocalDate;

@Schema(description = "ASC接口 - 租户的token消耗情况 Excel 导出 Request VO，参数和 ConsumedTokensPageReqVO 是一致的")
@Data
public class ConsumedTokensExportReqVO {

    @Schema(description = "聊天消耗的Tokens总数量, 动态更新")
    private Long totalChatTokenCnt;

    @Schema(description = "受训文件消耗的Tokens总数量, 动态更新")
    private Long totalFileTokenCnt;

    @Schema(description = "受训网站消耗的Tokens总数量, 动态更新")
    private Long totalWebsiteTokenCnt;

    @Schema(description = "受训百问百答消耗的Tokens总数量, 动态更新")
    private Long totalFaqTokenCnt;

}
