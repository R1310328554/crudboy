package cn.iocoder.yudao.module.asc.dal.dataobject.botbasecfg;

import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import cn.iocoder.yudao.framework.tenant.core.db.TenantBaseDO;
import java.time.LocalDate;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 机器人的基础配置 DO
 *
 * @author 超级管理员
 */
@TableName("asc_bot_base_cfg")
@KeySequence("asc_bot_base_cfg_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BotBaseCfgDO extends TenantBaseDO {

    /**
     * 主键， 机器人id
     */
    @TableId
    private Long id;
    /**
     * 后续主动沟通的询问方案的id
     */
    private Long afterwardsReplyCfgId;
    /**
     * 关键信息和文件链接匹配方案的id
     */
    private Long fileMatchCfgId;
    /**
     * 机器人代号
     */
    private String code;
    /**
     * 机器人名称
     */
    private String name;
    /**
     * 头像的url、或本地文件目录
     */
    private String avatar;
    /**
     * 角色设定，从 asc_bot_prompt 表选择
     */
    private String role;
    /**
     * 毕业院校，即gpt模型
     */
    private String model;
    /**
     * 表达风格，即 回复是自由度
     */
    private BigDecimal temperature;
    /**
     * 上下文记忆量，超过则舍弃
     */
    private Integer maxCtxLen;
    /**
     * 岗位
     */
    private String post;
    /**
     * 回复语速(秒)
     */
    private Integer speed;
    /**
     * 人工智能雇员会在客户发送最末信息（单条或多条）后 多少 秒后，整体理解，再统一回复
     */
    private Integer replyDelay;
    /**
     * 限流设置，总共时间跨度(秒)
     */
    private Integer rateLimitDuration;
    /**
     * 限流设置，多少个问题
     */
    private Integer rateLimitQuestions;
    /**
     * 沟通频率和数量超过限制时，应出现的提示语
     */
    private String rateLimitPrompt;
    /**
     * 客户信息收集提示信息
     */
    private String userInfoCollector;
    /**
     * 客户信息收集提示信息, 姓名|电话|微信, 多选一
     *
     * 枚举 {@link TODO yj_tmp 对应的类}
     */
    private String userInfoCollectorType;
    /**
     * 初始信息， 比如，用户开始聊天，第一句话是‘你好’之类的问候语， 那么就回复这个初始信息
     */
    private String initReply;
    /**
     * 备注
     */
    private String remark;
    /**
     * 0草稿、1上岗、2下岗、3维护中、4删除、
     *
     * 枚举 {@link TODO biz_status 对应的类}
     */
    private String status;

}
