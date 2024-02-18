package cn.iocoder.yudao.module.asc.dal.dataobject.thirdpartycfg;

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
 * 终端平台即第三方平台配置 DO
 *
 * @author 超级管理员
 */
@TableName("asc_third_party_cfg")
@KeySequence("asc_third_party_cfg_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ThirdPartyCfgDO extends TenantBaseDO {

    /**
     * 主键
     */
    @TableId
    private Long id;
    /**
     * 聊天的会话id
     */
    private Long chatId;
    /**
     * 平台上的账号
     */
    private String name;
    /**
     * 平台上的头像
     */
    private String image;
    /**
     * Wechat/Wechatcompany/WhatsApp
     *
     * 枚举 {@link TODO yj_tmp 对应的类}
     */
    private String thirdPartyType;
    /**
     * key
     */
    private String key;
    /**
     * secret
     *
     * 枚举 {@link TODO yj_tmp 对应的类}
     */
    private String secret;
    /**
     * 企业id
     */
    private String companyId;
    /**
     * 企业名称
     */
    private String companyName;
    /**
     * 企业url
     */
    private String companyUrl;

}
