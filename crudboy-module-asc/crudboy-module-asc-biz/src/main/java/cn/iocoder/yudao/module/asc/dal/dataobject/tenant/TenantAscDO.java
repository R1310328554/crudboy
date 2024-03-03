package cn.iocoder.yudao.module.asc.dal.dataobject.tenant;

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
 * 租户 DO
 *
 * @author 超级管理员
 */
//@TableName("asc_tenant")
//@KeySequence("asc_tenant_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
//@Data
//@EqualsAndHashCode(callSuper = true)
//@ToString(callSuper = true)
//@Builder
//@NoArgsConstructor
//@AllArgsConstructor
public class TenantAscDO extends TenantBaseDO {

    /**
     * 主键，即租户id
     */
    @TableId
    private Long id;
    /**
     * 租户名
     */
    private String name;
    /**
     * 租户级别， 黄金、白银、青铜
     *
     * 枚举 {@link TODO yj_tmp 对应的类}
     */
    private String level;
    /**
     * 状态
     *
     * 枚举 {@link TODO yj_tmp 对应的类}
     */
    private String tStatus;
    /**
     * 问答token总数上限
     */
    private Long maxQuestionTokenCnt;
    /**
     * 可以发出的问题的最大数量
     */
    private Long maxQuestionCnt;
    /**
     * 文档token总数上限
     */
    private Long maxFileTokenCnt;
    /**
     * 可以上传的最大的文件的个数
     */
    private Long maxFileCnt;
    /**
     * 训练的文档类型, 多个以英文逗号分割
     */
    private String fileCategorys;
    /**
     * 支持的、可以进行训练的文档格式, 多个以英文逗号分割，.pdf  .doc  .txt
     */
    private String fileTypes;
    /**
     * 训练网站可以使用的token总数上限
     */
    private Long maxWebsiteTokenCnt;
    /**
     * 训练的url格式, 多个以英文逗号分割，.com .gov .cn  .top
     */
    private String websiteTypes;
    /**
     * 可以训练问题的Tokens总数量
     */
    private Long maxFaqTokenCnt;
    /**
     * 可以设置FAQ的问题的最大数量
     */
    private Long maxFaqCnt;
    /**
     * C端用户数上限
     */
    private Long maxEndUser;
    /**
     * ...
     */
    private Long other;
    /**
     * 备注
     */
    private String remark;

}
