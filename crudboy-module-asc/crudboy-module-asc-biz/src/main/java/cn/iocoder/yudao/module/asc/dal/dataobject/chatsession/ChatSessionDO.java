package cn.iocoder.yudao.module.asc.dal.dataobject.chatsession;

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
 * 聊天的会话 DO
 *
 * @author 超级管理员
 */
@TableName("asc_chat_session")
@KeySequence("asc_chat_session_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChatSessionDO extends TenantBaseDO {

    /**
     * 聊天的会话id
     */
    @TableId
    private Long id;
    /**
     * 机器人ID, 即asc_bot_base_cfg的id
     */
    private Long botId;
    /**
     * 终端用户id，即问题发出者
     */
    private Long endUserId;
    /**
     * 终端用户名称，即问题发出者的昵称或姓名等， 冗余
     */
    private String endUserName;
    /**
     * 对方id， 通常就是机器人id， 可选
     */
    private String toUserId;
    /**
     * question总数量
     */
    private Long questionCnt;
    /**
     * answer总数量
     */
    private Long answerCnt;
    /**
     * 总消息条数
     */
    private Long cnt;
    /**
     * 总消息大小（Byte）
     */
    private Long len;
    /**
     * 消耗token数量
     */
    private Integer tokenCnt;
    /**
     * ...
     */
    private Long other;
    /**
     * 类型, 普通、高级、
     *
     * 枚举 {@link TODO yj_tmp 对应的类}
     */
    private String type;
    /**
     * 聊天标题，可以手动设置，否则自动生成
     */
    private String chatName;
    /**
     * 备注
     */
    private String remark;

}
