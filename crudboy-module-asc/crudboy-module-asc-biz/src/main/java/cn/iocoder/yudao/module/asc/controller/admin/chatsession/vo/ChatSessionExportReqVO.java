package cn.iocoder.yudao.module.asc.controller.admin.chatsession.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import java.math.BigDecimal;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import java.time.LocalDateTime;
import java.time.LocalDate;
import java.time.LocalDateTime;
import org.springframework.format.annotation.DateTimeFormat;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "ASC接口 - 聊天的会话 Excel 导出 Request VO，参数和 ChatSessionPageReqVO 是一致的")
@Data
public class ChatSessionExportReqVO {

    @Schema(description = "终端用户名称，即问题发出者的昵称或姓名等， 冗余", example = "罗哥")
    private String endUserName;

    @Schema(description = "question总数量")
    private Long questionCnt;

    @Schema(description = "answer总数量")
    private Long answerCnt;

    @Schema(description = "总消息条数")
    private Long cnt;

    @Schema(description = "总消息大小（Byte）")
    private Long len;

    @Schema(description = "消耗token数量")
    private Integer tokenCnt;

    @Schema(description = "...")
    private Long other;

    @Schema(description = "类型, 普通、高级、", example = "2")
    private String type;

    @Schema(description = "聊天标题，可以手动设置，否则自动生成", example = "李四")
    private String chatName;

    @Schema(description = "备注", example = "你说的对")
    private String remark;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}
