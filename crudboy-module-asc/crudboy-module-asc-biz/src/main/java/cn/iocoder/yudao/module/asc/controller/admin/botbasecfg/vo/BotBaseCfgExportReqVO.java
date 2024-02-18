package cn.iocoder.yudao.module.asc.controller.admin.botbasecfg.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import java.math.BigDecimal;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import java.time.LocalDateTime;
import java.time.LocalDate;
import java.time.LocalDateTime;
import org.springframework.format.annotation.DateTimeFormat;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 机器人的基础配置 Excel 导出 Request VO，参数和 BotBaseCfgPageReqVO 是一致的")
@Data
public class BotBaseCfgExportReqVO {

    @Schema(description = "机器人代号")
    private String code;

    @Schema(description = "机器人名称", example = "王五")
    private String name;

    @Schema(description = "头像的url、或本地文件目录")
    private String avatar;

    @Schema(description = "角色设定，从 asc_bot_prompt 表选择")
    private String role;

    @Schema(description = "毕业院校，即gpt模型")
    private String model;

    @Schema(description = "表达风格，即 回复是自由度")
    private BigDecimal temperature;

    @Schema(description = "上下文记忆量，超过则舍弃")
    private Integer maxCtxLen;

    @Schema(description = "岗位")
    private String post;

    @Schema(description = "回复语速(秒)")
    private Integer speed;

    @Schema(description = "人工智能雇员会在客户发送最末信息（单条或多条）后 多少 秒后，整体理解，再统一回复")
    private Integer replyDelay;

    @Schema(description = "限流设置，总共时间跨度(秒)")
    private Integer rateLimitDuration;

    @Schema(description = "限流设置，多少个问题")
    private Integer rateLimitQuestions;

    @Schema(description = "沟通频率和数量超过限制时，应出现的提示语")
    private String rateLimitPrompt;

    @Schema(description = "客户信息收集提示信息")
    private String userInfoCollector;

    @Schema(description = "客户信息收集提示信息, 姓名|电话|微信, 多选一", example = "电话")
    private String userInfoCollectorType;

    @Schema(description = "初始信息， 比如，用户开始聊天，第一句话是‘你好’之类的问候语， 那么就回复这个初始信息")
    private String initReply;

    @Schema(description = "备注", example = "你说的对")
    private String remark;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

    @Schema(description = "0草稿、1上岗、2下岗、3维护中、4删除、", example = "2")
    private String status;

}
