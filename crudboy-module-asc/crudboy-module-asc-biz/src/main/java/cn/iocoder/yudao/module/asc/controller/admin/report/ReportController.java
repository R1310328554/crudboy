package cn.iocoder.yudao.module.asc.controller.admin.report;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.framework.operatelog.core.annotations.OperateLog;
import cn.iocoder.yudao.module.asc.controller.admin.report.vo.*;
import cn.iocoder.yudao.module.asc.service.report.ReportService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;

import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;
import static cn.iocoder.yudao.framework.operatelog.core.enums.OperateTypeEnum.EXPORT;

@Tag(name = "ASC接口 - 机器人的基础配置")
@RestController
@RequestMapping("/asc/report")
@Validated
public class ReportController {

    @Resource
    private ReportService service;

    @PostMapping("/dash")
    @Operation(summary = "dash")
    @PreAuthorize("@ss.hasPermission('asc:bot-base-cfg:query')")
    public CommonResult<Long> dash(@Valid @RequestBody ReportCreateReqVO createReqVO) {
        BigDecimal temperature = createReqVO.getTemperature();
        if (temperature.compareTo(BigDecimal.ONE) > 0) {
            System.err.println("temperature = " + temperature);
        } else if (temperature.compareTo(BigDecimal.ZERO) < 0) {
            System.err.println("temperature = " + temperature);
        }
        return success(service.create(createReqVO));
    }

    @PutMapping("/token")
    @Operation(summary = "token消耗情况统计")
    @PreAuthorize("@ss.hasPermission('asc:bot-base-cfg:query')")
    public CommonResult<Boolean> token(@Valid @RequestBody ReportUpdateReqVO updateReqVO) {
        BigDecimal temperature = updateReqVO.getTemperature();
        if (temperature.compareTo(BigDecimal.ONE) > 0) {
            System.err.println("temperature = " + temperature);
        } else if (temperature.compareTo(BigDecimal.ZERO) < 0) {
            System.err.println("temperature = " + temperature);
        }
        service.update(updateReqVO);
        return success(true);
    }

    @PutMapping("/chat")
    @Operation(summary = "聊天统计")
    @PreAuthorize("@ss.hasPermission('asc:bot-base-cfg:query')")
    public CommonResult<Boolean> chat(@Valid @RequestBody ReportUpdateReqVO updateReqVO) {
        BigDecimal temperature = updateReqVO.getTemperature();
        if (temperature.compareTo(BigDecimal.ONE) > 0) {
            System.err.println("temperature = " + temperature);
        } else if (temperature.compareTo(BigDecimal.ZERO) < 0) {
            System.err.println("temperature = " + temperature);
        }
        service.update(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/overall")
    @Operation(summary = "租户整体情况")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('asc:bot-base-cfg:query')")
    public CommonResult<Boolean> overall(@RequestParam("id") Long id) {
        service.delete(id);
        return success(true);
    }

    @DeleteMapping("/fee")
    @Operation(summary = "费用统计")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('asc:bot-base-cfg:query')")
    public CommonResult<Boolean> fee(@RequestParam("id") Long id) {
        service.delete(id);
        return success(true);
    }


}
