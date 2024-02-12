package cn.iocoder.yudao.module.asc.controller.admin.chatsession.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalDate;

import java.time.LocalDateTime;
import java.time.LocalDateTime;
import javax.validation.constraints.*;

/**
 * 聊天的会话 Base VO，提供给添加、修改、详细的子 VO 使用
 * 如果子 VO 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成
 */
@Data
public class ChatSessionBaseVO {

    @Schema(description = "终端用户名称，即问题发出者的昵称或姓名等， 冗余", example = "罗哥")
    private String endUserName;

    @Schema(description = "question总数量", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "question总数量不能为空")
    private Long questionCnt;

    @Schema(description = "answer总数量", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "answer总数量不能为空")
    private Long answerCnt;

    @Schema(description = "总消息条数")
    private Long cnt;

    @Schema(description = "总消息大小（Byte）")
    private Long len;

    @Schema(description = "消耗token数量")
    private Integer tokenCnt;

    @Schema(description = "...", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "...不能为空")
    private Long other;

    @Schema(description = "类型, 普通、高级、", example = "2")
    private String type;

    @Schema(description = "聊天标题，可以手动设置，否则自动生成", example = "李四")
    private String chatName;

    @Schema(description = "备注", example = "你说的对")
    private String remark;

}
