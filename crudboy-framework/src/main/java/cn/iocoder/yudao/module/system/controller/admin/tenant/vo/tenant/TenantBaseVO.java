package cn.iocoder.yudao.module.system.controller.admin.tenant.vo.tenant;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.validation.constraints.*;
import java.time.LocalDateTime;

/**
 * 租户 Base VO，提供给添加、修改、详细的子 VO 使用
 * 如果子 VO 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成
 */
@Data
public class TenantBaseVO {

    @Schema(description = "租户名", requiredMode = Schema.RequiredMode.REQUIRED, example = "芋道")
    @NotNull(message = "租户名不能为空")
    private String name;

    @Schema(description = "联系人", requiredMode = Schema.RequiredMode.REQUIRED, example = "芋艿")
    @NotNull(message = "联系人不能为空")
    private String contactName;

    @Schema(description = "联系手机", example = "15601691300")
    private String contactMobile;

    @Schema(description = "租户状态", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotNull(message = "租户状态")
    private Integer status;

    @Schema(description = "绑定域名", example = "https://www.iocoder.cn")
    private String domain;

    @Schema(description = "租户套餐编号", requiredMode = Schema.RequiredMode.REQUIRED, example = "1024")
    @NotNull(message = "租户套餐编号不能为空")
    private Long packageId;

    @Schema(description = "过期时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "过期时间不能为空")
    private LocalDateTime expireTime;

    @Schema(description = "账号数量", requiredMode = Schema.RequiredMode.REQUIRED, example = "1024")
    @NotNull(message = "账号数量不能为空")
    private Integer accountCount;


    @Schema(description = "问答token总数上限")
    private Long maxQuestionTokenCnt;

    @Schema(description = "可以发出的问题的最大数量")
    private Long maxQuestionCnt;

    @Schema(description = "文档token总数上限")
    private Long maxFileTokenCnt;

    @Schema(description = "可以上传的最大的文件的个数")
    private Long maxFileCnt;

    @Schema(description = "训练的文档类型, 多个以英文逗号分割； 如 公司介绍,产品介绍,业务流程,销售技巧 ， 其实就是知识库的名称， 可选的知识库")
    private String fileCategorys;

    @Schema(description = "支持的、可以进行训练的文档格式, 多个以英文逗号分割，.pdf  .doc  .txt")
    private String fileTypes;

    @Schema(description = "训练网站可以使用的token总数上限")
    private Long maxWebsiteTokenCnt;

    @Schema(description = "训练的url格式, 多个以英文逗号分割，.com .gov .cn  .top")
    private String websiteTypes;

    @Schema(description = "可以训练问题的Tokens总数量")
    private Long maxFaqTokenCnt;

    @Schema(description = "可以设置FAQ的问题的最大数量")
    private Long maxFaqCnt;

    @Schema(description = "C端用户数上限")
    private Long maxEndUser;

    @Schema(description = "其他")
    private Long other;

    @Schema(description = "备注", example = "随便")
    private String remark;
}
