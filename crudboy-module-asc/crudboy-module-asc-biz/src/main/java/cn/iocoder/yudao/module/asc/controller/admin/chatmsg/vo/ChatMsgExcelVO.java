package cn.iocoder.yudao.module.asc.controller.admin.chatmsg.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalDate;
import java.time.LocalDateTime;

import com.alibaba.excel.annotation.ExcelProperty;
import cn.iocoder.yudao.framework.excel.core.annotations.DictFormat;
import cn.iocoder.yudao.framework.excel.core.convert.DictConvert;
import lombok.experimental.Accessors;


/**
 * 单个聊天消息 Excel VO
 *
 * @author 超级管理员
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = false) // 设置 chain = false，避免用户导入有问题
public class ChatMsgExcelVO {

    @ExcelProperty("主键")
    private Long id;

    @ExcelProperty(value = "方向，即问还是答，分别是：1,2", converter = DictConvert.class)
    @DictFormat("yj_tmp") // TODO 代码优化：建议设置到对应的 XXXDictTypeConstants 枚举类中
    private Boolean direction;

    @ExcelProperty(value = "消息类型, 1文本、2图片、3语音、4视频", converter = DictConvert.class)
    @DictFormat("yj_tmp") // TODO 代码优化：建议设置到对应的 XXXDictTypeConstants 枚举类中
    private Integer type;

    @ExcelProperty(value = "消息状态： 1送达、2已读、3发送失败、4重复、 5涉及敏感词被拦截、6涉及敏感词被替换、7其他异常， 等等", converter = DictConvert.class)
    @DictFormat("biz_status") // TODO 代码优化：建议设置到对应的 XXXDictTypeConstants 枚举类中
    private Integer status;

    @ExcelProperty("消耗token数量")
    private Integer tokenCnt;

    @ExcelProperty("消息大小（Byte）")
    private Integer len;

    @ExcelProperty("序号，即当前会话中的第几条消息（包括提问的回复的）")
    private Integer sequence;

    @ExcelProperty("消息内容，即问题或回答，")
    private String message;

    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}
