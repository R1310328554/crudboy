package cn.iocoder.yudao.module.asc.controller.admin.chatmsg.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.*;
import java.math.BigDecimal;

@Schema(description = "管理后台 - 单个聊天消息创建 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ChatMsgCreateReqVO extends ChatMsgBaseVO {

}
