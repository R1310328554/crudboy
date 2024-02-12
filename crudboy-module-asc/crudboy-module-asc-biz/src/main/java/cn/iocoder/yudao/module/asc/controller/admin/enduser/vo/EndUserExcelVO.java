package cn.iocoder.yudao.module.asc.controller.admin.enduser.vo;

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
 * 终端用户 Excel VO
 *
 * @author 超级管理员
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = false) // 设置 chain = false，避免用户导入有问题
public class EndUserExcelVO {

    @ExcelProperty("主键，即用户id")
    private Long id;

    @ExcelProperty("用户名")
    private String username;

    @ExcelProperty("用户昵称")
    private String nickname;

    @ExcelProperty("用户头像")
    private String avatar;

    @ExcelProperty("在第三方平台类型上的名称")
    private String thirdPartyName;

    @ExcelProperty(value = "第三方平台类型，即用户使用的微信还是WhatsApp..", converter = DictConvert.class)
    @DictFormat("yj_tmp") // TODO 代码优化：建议设置到对应的 XXXDictTypeConstants 枚举类中
    private String thirdPartyType;

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
