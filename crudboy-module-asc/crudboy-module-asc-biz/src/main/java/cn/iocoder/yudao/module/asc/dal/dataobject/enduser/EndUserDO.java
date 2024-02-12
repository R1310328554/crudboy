package cn.iocoder.yudao.module.asc.dal.dataobject.enduser;

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
 * 终端用户 DO
 *
 * @author 超级管理员
 */
@TableName("asc_end_user")
@KeySequence("asc_end_user_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EndUserDO extends TenantBaseDO {

    /**
     * 主键，即用户id
     */
    @TableId
    private Long id;
    /**
     * 用户名
     */
    private String username;
    /**
     * 用户昵称
     */
    private String nickname;
    /**
     * 用户头像
     */
    private String avatar;
    /**
     * 在第三方平台类型上的名称
     */
    private String thirdPartyName;
    /**
     * 第三方平台类型，即用户使用的微信还是WhatsApp..
     *
     * 枚举 {@link TODO yj_tmp 对应的类}
     */
    private String thirdPartyType;
    /**
     * 在第三方平台上的id，比如微信的openid、unionid WhatsApp_id 等等
     */
    private String thirdPartyId;
    /**
     * 备注
     */
    private String remark;

}
