package cn.iocoder.yudao.module.asc.dal.dataobject.filetraining;

import io.swagger.v3.oas.annotations.media.Schema;
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
 * 文档训练 DO
 *
 * @author 超级管理员
 */
@TableName("asc_file_training")
@KeySequence("asc_file_training_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FileTrainingDO extends TenantBaseDO {

    /**
     * 主键
     */
    @TableId
    private Long id;
    /**
     * 文件名，冗余
     */
    private String fileName;


    /**
     * 文件大小，冗余
     */
    private Integer fileSize;

    /**
     * 文件id、存储路径或url
     */
    private String fileId;
    /**
     * 训练状态， 如训练失败， 训练完成
     *
     * 枚举 {@link TODO yj_tmp 对应的类}
     */
    private String trainingStatus;
    /**
     * 训练的当前文档的Tokens总数量
     */
    private Long tokenCnt;

    /**
     * 所属的知识库
     */
    private String fileCategory;

    /**
     * 备注
     */
    private String remark;

}
