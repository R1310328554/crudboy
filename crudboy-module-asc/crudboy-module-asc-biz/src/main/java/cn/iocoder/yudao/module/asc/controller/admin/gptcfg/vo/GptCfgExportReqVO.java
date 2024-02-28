package cn.iocoder.yudao.module.asc.controller.admin.gptcfg.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import java.math.BigDecimal;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import java.time.LocalDateTime;
import java.time.LocalDate;

@Schema(description = "ASC接口 - GPT 专用的基础的配置 Excel 导出 Request VO，参数和 GptCfgPageReqVO 是一致的")
@Data
public class GptCfgExportReqVO {

    @Schema(description = "大模型的key")
    private String openaiKey;

    @Schema(description = "endpoint url, 终端访问路径", example = "https://www.qq.com")
    private String endpointUrl;

    @Schema(description = "当前openai_key 支持的model， 可以是本地大模型")
    private String openaiModel;

}
