package cn.iocoder.yudao.module.asc.controller.admin.filetraining.vo;

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
 * 文档训练 Base VO，提供给添加、修改、详细的子 VO 使用
 * 如果子 VO 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成
 */
@Data
public class FileTrainingBaseVO {

    @Schema(description = "文件id、存储路径或url", example = "123")
    private String fileId;

    @Schema(description = "文件名，冗余", example = "李四")
    private String fileName;

    @Schema(description = "所属的知识库； 即训练的文档类型,  如 公司介绍,产品介绍,业务流程,销售技巧")
    private String fileCategory;

    @Schema(description = "标签", example = "销售知识库")
    private String tag;

    @Schema(description = "备注", example = "你说的对")
    private String remark;

}
