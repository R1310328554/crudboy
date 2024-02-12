package cn.iocoder.yudao.module.asc.dal.dataobject.consumedtokens;

import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import cn.iocoder.yudao.framework.tenant.core.db.TenantBaseDO;
import java.time.LocalDate;

import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 租户的token消耗情况 DO
 *
 * @author 超级管理员
 */
@TableName("asc_consumed_tokens")
@KeySequence("asc_consumed_tokens_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ConsumedTokensDO extends TenantBaseDO {

    /**
     * 主键, 流水号
     */
    @TableId
    private Long id;
    /**
     * 聊天消耗的Tokens总数量, 动态更新
     */
    private Long totalChatTokenCnt;
    /**
     * 受训文件消耗的Tokens总数量, 动态更新
     */
    private Long totalFileTokenCnt;
    /**
     * 受训网站消耗的Tokens总数量, 动态更新
     */
    private Long totalWebsiteTokenCnt;
    /**
     * 受训百问百答消耗的Tokens总数量, 动态更新
     */
    private Long totalFaqTokenCnt;

}
