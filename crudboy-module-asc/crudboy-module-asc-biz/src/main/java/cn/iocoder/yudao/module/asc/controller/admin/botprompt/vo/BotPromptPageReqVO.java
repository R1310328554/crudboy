package cn.iocoder.yudao.module.asc.controller.admin.botprompt.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

@Schema(description = "ASC接口 - 预设提示词分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class BotPromptPageReqVO extends PageParam {

}
