package cn.iocoder.yudao.module.asc.controller.admin.websitetraining.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalDateTime;

import com.alibaba.excel.annotation.ExcelProperty;
import cn.iocoder.yudao.framework.excel.core.annotations.DictFormat;
import cn.iocoder.yudao.framework.excel.core.convert.DictConvert;
import lombok.experimental.Accessors;


/**
 * 网站内容爬取训练 Excel VO
 *
 * java: 找不到符号
 *   符号: 类 Accessors   todo
 *
 * @author 超级管理员
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = false) // 设置 chain = false，避免用户导入有问题
public class WebsiteTrainingExcelVO {

    @ExcelProperty("主键")
    private Long id;

    @ExcelProperty("网站url")
    private String url;

    @ExcelProperty(value = "训练状态， 如训练失败， 训练完成", converter = DictConvert.class)
    @DictFormat("yj_training_status") // TODO 代码优化：建议设置到对应的 XXXDictTypeConstants 枚举类中
    private String trainingStatus;

    @ExcelProperty("训练的当前网站的Tokens总数量")
    private Long tokenCnt;

    @ExcelProperty("耗时，总共时间跨度(秒)")
    private Integer rateLimitDuration;

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

}
