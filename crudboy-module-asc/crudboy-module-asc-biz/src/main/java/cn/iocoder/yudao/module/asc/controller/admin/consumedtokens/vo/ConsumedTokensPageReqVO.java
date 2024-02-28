package cn.iocoder.yudao.module.asc.controller.admin.consumedtokens.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

@Schema(description = "ASC接口 - 租户的token消耗情况分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ConsumedTokensPageReqVO extends PageParam {

}
