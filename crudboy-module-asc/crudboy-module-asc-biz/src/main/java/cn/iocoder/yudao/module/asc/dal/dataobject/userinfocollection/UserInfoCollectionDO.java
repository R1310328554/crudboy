package cn.iocoder.yudao.module.asc.dal.dataobject.userinfocollection;

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
 * 客户信息收集 DO
 *
 * @author 超级管理员
 */
@TableName("asc_user_info_collection")
@KeySequence("asc_user_info_collection_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserInfoCollectionDO extends TenantBaseDO {

    /**
     * 主键
     */
    @TableId
    private Long id;
    /**
     * 客户信息收集提示信息
     */
    private String needAskUser;
    /**
     * 客户信息收集提示信息-类型, 姓名|电话|微信, 多选一
     *
     * 枚举 {@link TODO yj_tmp 对应的类}
     */
    private String askType;
    /**
     * 备注
     */
    private String remark;

}
