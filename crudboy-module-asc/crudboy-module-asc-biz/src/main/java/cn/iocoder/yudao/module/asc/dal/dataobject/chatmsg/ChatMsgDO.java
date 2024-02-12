package cn.iocoder.yudao.module.asc.dal.dataobject.chatmsg;

import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import cn.iocoder.yudao.framework.tenant.core.db.TenantBaseDO;
import java.time.LocalDate;

import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 单个聊天消息 DO
 *
 * @author 超级管理员
 */
@TableName("asc_chat_msg")
@KeySequence("asc_chat_msg_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChatMsgDO extends TenantBaseDO {

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
     * 方向，即问还是答，分别是：1,2
     *
     * 枚举 {@link TODO yj_tmp 对应的类}
     */
    private Boolean direction;
    /**
     * 如果是发出问题，那么是终端用户id； 如果是回复，那么就是机器人id
     */
    private Long fromUserId;
    /**
     * 消息类型, 1文本、2图片、3语音、4视频
     *
     * 枚举 {@link TODO yj_tmp 对应的类}
     */
    private Integer type;
    /**
     * 消息状态： 1送达、2已读、3发送失败、4重复、 5涉及敏感词被拦截、6涉及敏感词被替换、7其他异常， 等等
     *
     * 枚举 {@link TODO biz_status 对应的类}
     */
    private Integer status;
    /**
     * 消耗token数量
     */
    private Integer tokenCnt;
    /**
     * 消息大小（Byte）
     */
    private Integer len;
    /**
     * 序号，即当前会话中的第几条消息（包括提问的回复的）
     */
    private Integer sequence;
    /**
     * 消息内容，即问题或回答，
     */
    private String message;

}
