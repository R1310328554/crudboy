package cn.iocoder.yudao.module.asc.dal.dataobject.gptcfg;

import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import cn.iocoder.yudao.framework.tenant.core.db.TenantBaseDO;
import java.time.LocalDate;

import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * GPT 专用的基础的配置 DO
 *
 * @author 超级管理员
 */
@TableName("asc_gpt_cfg")
@KeySequence("asc_gpt_cfg_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GptCfgDO extends TenantBaseDO {

    /**
     * 主键
     */
    @TableId
    private Long id;
    /**
     * 大模型的key
     */
    private String openaiKey;
    /**
     * endpoint url, 终端访问路径
     */
    private String endpointUrl;
    /**
     * 当前openai_key 支持的model， 可以是本地大模型
     */
    private String openaiModel;

}
