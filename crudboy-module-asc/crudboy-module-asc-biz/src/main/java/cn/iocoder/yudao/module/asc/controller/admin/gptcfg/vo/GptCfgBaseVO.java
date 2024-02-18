package cn.iocoder.yudao.module.asc.controller.admin.gptcfg.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalDate;

import javax.validation.constraints.*;

/**
 * GPT 专用的基础的配置 Base VO，提供给添加、修改、详细的子 VO 使用
 * 如果子 VO 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成
 */
@Data
public class GptCfgBaseVO {

    @Schema(description = "大模型的key")
    private String openaiKey;

    @Schema(description = "endpoint url, 终端访问路径", example = "https://www.qq.com")
    private String endpointUrl;

    @Schema(description = "当前openai_key 支持的model， 可以是本地大模型")
    private String openaiModel;

}
