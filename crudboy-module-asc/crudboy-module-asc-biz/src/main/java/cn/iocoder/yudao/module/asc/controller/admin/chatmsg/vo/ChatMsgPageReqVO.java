package cn.iocoder.yudao.module.asc.controller.admin.chatmsg.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

@Schema(description = "管理后台 - 单个聊天消息分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ChatMsgPageReqVO extends PageParam {

    @Schema(description = "消息状态： 1送达、2已读、3发送失败、4重复、 5涉及敏感词被拦截、6涉及敏感词被替换、7其他异常， 等等", example = "2")
    private Integer status;

}
