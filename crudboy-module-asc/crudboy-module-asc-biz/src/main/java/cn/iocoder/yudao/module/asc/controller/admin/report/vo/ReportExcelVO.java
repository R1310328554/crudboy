package cn.iocoder.yudao.module.asc.controller.admin.report.vo;

import cn.iocoder.yudao.framework.excel.core.annotations.DictFormat;
import cn.iocoder.yudao.framework.excel.core.convert.DictConvert;
import com.alibaba.excel.annotation.ExcelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.time.LocalDateTime;


/**
 * 机器人的基础配置 Excel VO
 *
 * @author 超级管理员
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = false) // 设置 chain = false，避免用户导入有问题
public class ReportExcelVO {

    @ExcelProperty("主键， 机器人id")
    private Long id;

    @ExcelProperty("机器人代号")
    private String code;

    @ExcelProperty("机器人名称")
    private String name;

    @ExcelProperty("头像的url、或本地文件目录")
    private String avatar;

    @ExcelProperty("角色设定，从 asc_bot_prompt 表选择")
    private String role;

    @ExcelProperty("毕业院校，即gpt模型")
    private String model;

    @ExcelProperty("表达风格，即 回复是自由度")
    private BigDecimal temperature;

    @ExcelProperty("上下文记忆量，超过则舍弃")
    private Integer maxCtxLen;

    @ExcelProperty("岗位")
    private String post;

    @ExcelProperty("回复语速(秒)")
    private Integer speed;

    @ExcelProperty("人工智能雇员会在客户发送最末信息（单条或多条）后 多少 秒后，整体理解，再统一回复")
    private Integer replyDelay;

    @ExcelProperty("限流设置，总共时间跨度(秒)")
    private Integer rateLimitDuration;

    @ExcelProperty("限流设置，多少个问题")
    private Integer rateLimitQuestions;

    @ExcelProperty("沟通频率和数量超过限制时，应出现的提示语")
    private String rateLimitPrompt;

    @ExcelProperty("客户信息收集提示信息")
    private String userInfoCollector;

    @ExcelProperty(value = "客户信息收集提示信息, 姓名|电话|微信, 多选一", converter = DictConvert.class)
    @DictFormat("yj_tmp") // TODO 代码优化：建议设置到对应的 XXXDictTypeConstants 枚举类中
    private String userInfoCollectorType;

    @ExcelProperty("初始信息， 比如，用户开始聊天，第一句话是‘你好’之类的问候语， 那么就回复这个初始信息")
    private String initReply;

    @ExcelProperty("备注")
    private String remark;

    @ExcelProperty("创建者")
    private String creator;

    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

    @ExcelProperty("更新者")
    private String updater;

    @ExcelProperty("更新时间")
    private LocalDateTime updateTime;

    @ExcelProperty(value = "0草稿、1上岗、2下岗、3维护中、4删除、", converter = DictConvert.class)
    @DictFormat("biz_status") // TODO 代码优化：建议设置到对应的 XXXDictTypeConstants 枚举类中
    private String status;

}
