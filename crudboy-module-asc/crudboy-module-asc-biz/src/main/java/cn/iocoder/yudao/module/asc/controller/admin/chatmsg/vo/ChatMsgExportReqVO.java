package cn.iocoder.yudao.module.asc.controller.admin.chatmsg.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import java.math.BigDecimal;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import java.time.LocalDateTime;
import java.time.LocalDate;
import java.time.LocalDateTime;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "ASC接口 - 单个聊天消息 Excel 导出 Request VO，参数和 ChatMsgPageReqVO 是一致的")
@Data
public class ChatMsgExportReqVO {

    @Schema(description = "聊天的会话id", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    @NotNull(message = "聊天的会话id不能为空")
    private Long chatId;

    @Schema(description = "方向，即问还是答，分别是：1,2")
    private Boolean direction;

    @Schema(description = "消息类型, 1文本、2图片、3语音、4视频", example = "1")
    private Integer type;

    @Schema(description = "消息状态： 1送达、2已读、3发送失败、4重复、 5涉及敏感词被拦截、6涉及敏感词被替换、7其他异常， 等等", example = "2")
    private Integer status;

    @Schema(description = "消耗token数量")
    private Integer tokenCnt;

    @Schema(description = "消息大小（Byte）")
    private Integer len;

    @Schema(description = "序号，即当前会话中的第几条消息（包括提问的回复的）")
    private Integer sequence;

    @Schema(description = "到xx序号结束 ")
    private Integer sequence2;

    @Schema(description = "消息内容，即问题或回答，")
    private String message;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}
