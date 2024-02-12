package cn.iocoder.yudao.module.asc.controller.admin.consumedtokens.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalDate;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.experimental.Accessors;

/**
 * 租户的token消耗情况 Excel VO
 *
 * @author 超级管理员
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = false) // 设置 chain = false，避免用户导入有问题
public class ConsumedTokensExcelVO {

    @ExcelProperty("主键, 流水号")
    private Long id;

    @ExcelProperty("聊天消耗的Tokens总数量, 动态更新")
    private Long totalChatTokenCnt;

    @ExcelProperty("受训文件消耗的Tokens总数量, 动态更新")
    private Long totalFileTokenCnt;

    @ExcelProperty("受训网站消耗的Tokens总数量, 动态更新")
    private Long totalWebsiteTokenCnt;

    @ExcelProperty("受训百问百答消耗的Tokens总数量, 动态更新")
    private Long totalFaqTokenCnt;

}
