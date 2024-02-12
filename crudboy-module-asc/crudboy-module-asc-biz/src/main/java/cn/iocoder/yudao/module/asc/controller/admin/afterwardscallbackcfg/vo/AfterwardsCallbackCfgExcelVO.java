package cn.iocoder.yudao.module.asc.controller.admin.afterwardscallbackcfg.vo;

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
 * 后续主动沟通的询问方案 Excel VO
 *
 * @author 超级管理员
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = false) // 设置 chain = false，避免用户导入有问题
public class AfterwardsCallbackCfgExcelVO {

    @ExcelProperty("主键")
    private Long id;

    @ExcelProperty("比如， 方案1、2 这样的名称")
    private String name;

    @ExcelProperty("后续主动沟通的信息")
    private String afterwardsReply;

    @ExcelProperty("接触时机, 最后沟通后的多少小时后？")
    private Integer callbackDelay;

    @ExcelProperty("是否需要结合所有上下文, Y or N")
    private String isNeedCtx;

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
