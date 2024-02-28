package cn.iocoder.yudao.module.asc.controller.admin.tenant.vo;

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
 * 租户 Excel VO
 *
 * @author 超级管理员
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = false) // 设置 chain = false，避免用户导入有问题
public class TenantExcelVO {

    @ExcelProperty("主键，即租户id")
    private Long id;

    @ExcelProperty("租户名")
    private String name;

    @ExcelProperty(value = "租户级别， 黄金、白银、青铜", converter = DictConvert.class)
    @DictFormat("yj_tmp") // TODO 代码优化：建议设置到对应的 XXXDictTypeConstants 枚举类中
    private String level;

    @ExcelProperty(value = "状态", converter = DictConvert.class)
    @DictFormat("yj_tmp") // TODO 代码优化：建议设置到对应的 XXXDictTypeConstants 枚举类中
    private String tStatus;

    @ExcelProperty("问答token总数上限")
    private Long maxQuestionTokenCnt;

    @ExcelProperty("可以发出的问题的最大数量")
    private Long maxQuestionCnt;

    @ExcelProperty("文档token总数上限")
    private Long maxFileTokenCnt;

    @ExcelProperty("可以上传的最大的文件的个数")
    private Long maxFileCnt;

    @ExcelProperty("训练的文档类型, 多个以英文逗号分割")
    private String fileCategorys;

    @ExcelProperty("支持的、可以进行训练的文档格式, 多个以英文逗号分割，.pdf  .doc  .txt")
    private String fileTypes;

    @ExcelProperty("训练网站可以使用的token总数上限")
    private Long maxWebsiteTokenCnt;

    @ExcelProperty("训练的url格式, 多个以英文逗号分割，.com .gov .cn  .top")
    private String websiteTypes;

    @ExcelProperty("可以训练问题的Tokens总数量")
    private Long maxFaqTokenCnt;

    @ExcelProperty("可以设置FAQ的问题的最大数量")
    private Long maxFaqCnt;

    @ExcelProperty("C端用户数上限")
    private Long maxEndUser;

    @ExcelProperty("...")
    private Long other;

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
