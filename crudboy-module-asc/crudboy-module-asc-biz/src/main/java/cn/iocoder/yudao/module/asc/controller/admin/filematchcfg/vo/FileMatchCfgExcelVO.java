package cn.iocoder.yudao.module.asc.controller.admin.filematchcfg.vo;

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
 * 关键信息和文件链接匹配 Excel VO
 *
 * @author 超级管理员
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = false) // 设置 chain = false，避免用户导入有问题
public class FileMatchCfgExcelVO {

    @ExcelProperty("主键")
    private Long id;

    @ExcelProperty("比如， 方案1、2 这样的名称")
    private String name;

    @ExcelProperty("触发关键信息，只有聊天信息匹配这些意图， 那么下一步发送文件， 多个问题以换行符分割")
    private String questions;

    @ExcelProperty("匹配到关键信息后发送的文件， 多个文件以逗号分割")
    private String sendFiles;

    @ExcelProperty("匹配到关键信息后发送的链接，如自己的小程序的链接或url， 多个文件以逗号分割")
    private String sendLinks;

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
