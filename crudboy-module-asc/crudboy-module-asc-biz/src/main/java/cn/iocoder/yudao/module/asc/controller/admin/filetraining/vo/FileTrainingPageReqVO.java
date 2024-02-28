package cn.iocoder.yudao.module.asc.controller.admin.filetraining.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

@Schema(description = "ASC接口 - 文档训练分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class FileTrainingPageReqVO extends PageParam {

}
