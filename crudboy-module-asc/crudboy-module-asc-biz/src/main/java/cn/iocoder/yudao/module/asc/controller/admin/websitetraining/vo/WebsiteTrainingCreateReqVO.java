package cn.iocoder.yudao.module.asc.controller.admin.websitetraining.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.*;
import java.math.BigDecimal;

@Schema(description = "ASC接口 - 网站内容爬取训练创建 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class WebsiteTrainingCreateReqVO extends WebsiteTrainingBaseVO {

}
