package cn.iocoder.yudao.module.asc.dal.dataobject.afterwardscallbackcfg;

import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import cn.iocoder.yudao.framework.tenant.core.db.TenantBaseDO;
import java.time.LocalDate;

import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 后续主动沟通的询问方案 DO
 *
 * @author 超级管理员
 */
@TableName("asc_afterwards_callback_cfg")
@KeySequence("asc_afterwards_callback_cfg_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AfterwardsCallbackCfgDO extends TenantBaseDO {

    /**
     * 主键
     */
    @TableId
    private Long id;
    /**
     * 比如， 方案1、2 这样的名称
     */
    private String name;
    /**
     * 后续主动沟通的信息
     */
    private String afterwardsReply;
    /**
     * 接触时机, 最后沟通后的多少小时后？
     */
    private Integer callbackDelay;
    /**
     * 是否需要结合所有上下文, Y or N
     */
    private String isNeedCtx;
    /**
     * 备注
     */
    private String remark;

}
