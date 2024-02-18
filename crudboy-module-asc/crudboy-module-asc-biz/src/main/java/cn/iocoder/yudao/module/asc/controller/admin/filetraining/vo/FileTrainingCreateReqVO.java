package cn.iocoder.yudao.module.asc.controller.admin.filetraining.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.*;
import java.math.BigDecimal;

@Schema(description = "管理后台 - 文档训练创建 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class FileTrainingCreateReqVO extends FileTrainingBaseVO {

}
