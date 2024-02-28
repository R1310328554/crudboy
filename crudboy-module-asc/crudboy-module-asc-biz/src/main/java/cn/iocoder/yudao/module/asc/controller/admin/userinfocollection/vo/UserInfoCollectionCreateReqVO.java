package cn.iocoder.yudao.module.asc.controller.admin.userinfocollection.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.*;
import java.math.BigDecimal;

@Schema(description = "ASC接口 - 客户信息收集创建 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class UserInfoCollectionCreateReqVO extends UserInfoCollectionBaseVO {

}
