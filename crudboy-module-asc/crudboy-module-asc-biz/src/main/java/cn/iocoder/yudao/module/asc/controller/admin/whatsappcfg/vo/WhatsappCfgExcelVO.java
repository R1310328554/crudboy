package cn.iocoder.yudao.module.asc.controller.admin.whatsappcfg.vo;

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
 * WhatsApp 平台配置 Excel VO
 *
 * @author 超级管理员
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = false) // 设置 chain = false，避免用户导入有问题
public class WhatsappCfgExcelVO {

    @ExcelProperty("主键")
    private Long id;

    @ExcelProperty("创建者")
    private String name;

    @ExcelProperty("创建者")
    private String image;

    @ExcelProperty(value = "Wechat/Wechatcompany/WhatsApp", converter = DictConvert.class)
    @DictFormat("yj_tmp") // TODO 代码优化：建议设置到对应的 XXXDictTypeConstants 枚举类中
    private String thirdPartyType;

    @ExcelProperty("key")
    private String key;

    @ExcelProperty(value = "secret", converter = DictConvert.class)
    @DictFormat("yj_tmp") // TODO 代码优化：建议设置到对应的 XXXDictTypeConstants 枚举类中
    private String secret;

    @ExcelProperty("secret")
    private String companyName;

    @ExcelProperty("secret")
    private String companyUrl;

    @ExcelProperty("创建者")
    private String creator;

    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

    @ExcelProperty("更新者")
    private String updater;

    @ExcelProperty("更新时间")
    private LocalDateTime updateTime;

}
