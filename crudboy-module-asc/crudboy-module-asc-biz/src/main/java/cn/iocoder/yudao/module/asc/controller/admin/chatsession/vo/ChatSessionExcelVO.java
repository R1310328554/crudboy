package cn.iocoder.yudao.module.asc.controller.admin.chatsession.vo;

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
 * 聊天的会话 Excel VO
 *
 * @author 超级管理员
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = false) // 设置 chain = false，避免用户导入有问题
public class ChatSessionExcelVO {

    @ExcelProperty("聊天的会话id")
    private Long id;

    @ExcelProperty("终端用户名称，即问题发出者的昵称或姓名等， 冗余")
    private String endUserName;

    @ExcelProperty("question总数量")
    private Long questionCnt;

    @ExcelProperty("answer总数量")
    private Long answerCnt;

    @ExcelProperty("总消息条数")
    private Long cnt;

    @ExcelProperty("总消息大小（Byte）")
    private Long len;

    @ExcelProperty("消耗token数量")
    private Integer tokenCnt;

    @ExcelProperty("...")
    private Long other;

    @ExcelProperty(value = "类型, 普通、高级、", converter = DictConvert.class)
    @DictFormat("yj_tmp") // TODO 代码优化：建议设置到对应的 XXXDictTypeConstants 枚举类中
    private String type;

    @ExcelProperty("聊天标题，可以手动设置，否则自动生成")
    private String chatName;

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
