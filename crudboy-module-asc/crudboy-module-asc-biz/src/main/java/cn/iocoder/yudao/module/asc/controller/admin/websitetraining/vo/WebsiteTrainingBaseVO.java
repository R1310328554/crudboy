package cn.iocoder.yudao.module.asc.controller.admin.websitetraining.vo;

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
 * 网站内容爬取训练 Base VO，提供给添加、修改、详细的子 VO 使用
 * 如果子 VO 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成
 */
@Data
public class WebsiteTrainingBaseVO {

    @Schema(description = "网站url", requiredMode = Schema.RequiredMode.REQUIRED, example = "https://www.qq.com")
    @NotNull(message = "网站url不能为空")
    private String url;

    @Schema(description = "训练状态， 如训练失败， 训练完成", example = "2")
    private String trainingStatus;

    @Schema(description = "训练的当前网站的Tokens总数量")
    private Long tokenCnt;

    @Schema(description = "耗时，总共时间跨度(秒)")
    private Integer rateLimitDuration;

    @Schema(description = "所属的知识库； 即训练的文档类型,  如 公司介绍,产品介绍,业务流程,销售技巧")
    private String fileCategory;

    @Schema(description = "标签", example = "销售知识库")
    private String tag;
    
    @Schema(description = "备注", example = "你说的对")
    private String remark;

}
