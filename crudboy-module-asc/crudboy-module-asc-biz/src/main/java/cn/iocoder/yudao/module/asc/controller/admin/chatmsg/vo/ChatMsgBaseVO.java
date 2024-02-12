package cn.iocoder.yudao.module.asc.controller.admin.chatmsg.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalDate;

import java.time.LocalDateTime;
import javax.validation.constraints.*;

/**
 * 单个聊天消息 Base VO，提供给添加、修改、详细的子 VO 使用
 * 如果子 VO 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成
 */
@Data
public class ChatMsgBaseVO {

    @Schema(description = "方向，即问还是答，分别是：1,2", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "方向，即问还是答，分别是：1,2不能为空")
    private Boolean direction;

    @Schema(description = "消息类型, 1文本、2图片、3语音、4视频", example = "1")
    private Integer type;

    @Schema(description = "消息状态： 1送达、2已读、3发送失败、4重复、 5涉及敏感词被拦截、6涉及敏感词被替换、7其他异常， 等等", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    @NotNull(message = "消息状态： 1送达、2已读、3发送失败、4重复、 5涉及敏感词被拦截、6涉及敏感词被替换、7其他异常， 等等不能为空")
    private Integer status;

    @Schema(description = "消耗token数量")
    private Integer tokenCnt;

    @Schema(description = "消息大小（Byte）")
    private Integer len;

    @Schema(description = "序号，即当前会话中的第几条消息（包括提问的回复的）")
    private Integer sequence;

    @Schema(description = "消息内容，即问题或回答，")
    private String message;

}
