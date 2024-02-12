package cn.iocoder.yudao.module.asc.dal.dataobject.filematchcfg;

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
 * 关键信息和文件链接匹配 DO
 *
 * @author 超级管理员
 */
@TableName("asc_file_match_cfg")
@KeySequence("asc_file_match_cfg_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FileMatchCfgDO extends TenantBaseDO {

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
     * 触发关键信息，只有聊天信息匹配这些意图， 那么下一步发送文件， 多个问题以换行符分割
     */
    private String questions;
    /**
     * 匹配到关键信息后发送的文件， 多个文件以逗号分割
     */
    private String sendFiles;
    /**
     * 匹配到关键信息后发送的链接，如自己的小程序的链接或url， 多个文件以逗号分割
     */
    private String sendLinks;
    /**
     * 备注
     */
    private String remark;

}
