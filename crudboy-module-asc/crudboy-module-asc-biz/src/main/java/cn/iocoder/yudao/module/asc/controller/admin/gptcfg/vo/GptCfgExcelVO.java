package cn.iocoder.yudao.module.asc.controller.admin.gptcfg.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalDate;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.experimental.Accessors;

/**
 * GPT 专用的基础的配置 Excel VO
 *
 * @author 超级管理员
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = false) // 设置 chain = false，避免用户导入有问题
public class GptCfgExcelVO {

    @ExcelProperty("主键")
    private Long id;

    @ExcelProperty("大模型的key")
    private String openaiKey;

    @ExcelProperty("endpoint url, 终端访问路径")
    private String endpointUrl;

    @ExcelProperty("当前openai_key 支持的model， 可以是本地大模型")
    private String openaiModel;

}
