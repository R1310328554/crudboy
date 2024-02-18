package cn.iocoder.yudao.module.asc.controller.admin.thirdpartycfg.vo;

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
 * 终端平台即第三方平台配置 Excel VO
 *
 * @author 超级管理员
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = false) // 设置 chain = false，避免用户导入有问题
public class ThirdPartyCfgExcelVO {

    @ExcelProperty("主键")
    private Long id;

    @ExcelProperty("平台上的账号")
    private String name;

    @ExcelProperty("平台上的头像")
    private String image;

    @ExcelProperty(value = "Wechat/Wechatcompany/WhatsApp", converter = DictConvert.class)
    @DictFormat("yj_tmp") // TODO 代码优化：建议设置到对应的 XXXDictTypeConstants 枚举类中
    private String thirdPartyType;

    @ExcelProperty("key")
    private String key;

    @ExcelProperty(value = "secret", converter = DictConvert.class)
    @DictFormat("yj_tmp") // TODO 代码优化：建议设置到对应的 XXXDictTypeConstants 枚举类中
    private String secret;

    @ExcelProperty("企业名称")
    private String companyName;

    @ExcelProperty("企业url")
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
