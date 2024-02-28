package cn.iocoder.yudao.module.asc.controller.admin.botprompt;

import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Operation;

import javax.validation.constraints.*;
import javax.validation.*;
import javax.servlet.http.*;
import java.util.*;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalDate;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;

import cn.iocoder.yudao.framework.operatelog.core.annotations.OperateLog;
import static cn.iocoder.yudao.framework.operatelog.core.enums.OperateTypeEnum.*;

import cn.iocoder.yudao.module.asc.controller.admin.botprompt.vo.*;
import cn.iocoder.yudao.module.asc.dal.dataobject.botprompt.BotPromptDO;
import cn.iocoder.yudao.module.asc.convert.botprompt.BotPromptConvert;
import cn.iocoder.yudao.module.asc.service.botprompt.BotPromptService;

@Tag(name = "ASC接口 - 预设提示词")
@RestController
@RequestMapping("/asc/bot-prompt")
@Validated
public class BotPromptController {

    @Resource
    private BotPromptService service;

    @PostMapping("/create")
    @Operation(summary = "创建预设提示词")
    @PreAuthorize("@ss.hasPermission('asc:bot-prompt:create')")
    public CommonResult<Long> create(@Valid @RequestBody BotPromptCreateReqVO createReqVO) {
        return success(service.create(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新预设提示词")
    @PreAuthorize("@ss.hasPermission('asc:bot-prompt:update')")
    public CommonResult<Boolean> update(@Valid @RequestBody BotPromptUpdateReqVO updateReqVO) {
        service.update(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除预设提示词")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('asc:bot-prompt:delete')")
    public CommonResult<Boolean> delete(@RequestParam("id") Long id) {
        service.delete(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得预设提示词")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('asc:bot-prompt:query')")
    public CommonResult<BotPromptRespVO> get(@RequestParam("id") Long id) {
        BotPromptDO botPrompt = service.get(id);
        return success(BotPromptConvert.INSTANCE.convert(botPrompt));
    }

    @GetMapping("/list")
    @Operation(summary = "获得预设提示词列表")
    @Parameter(name = "ids", description = "编号列表", required = true, example = "1024,2048")
    @PreAuthorize("@ss.hasPermission('asc:bot-prompt:query')")
    public CommonResult<List<BotPromptRespVO>> getList(@RequestParam("ids") Collection<Long> ids) {
        List<BotPromptDO> list = service.getList(ids);
        return success(BotPromptConvert.INSTANCE.convertList(list));
    }

    @GetMapping("/page")
    @Operation(summary = "获得预设提示词分页")
    @PreAuthorize("@ss.hasPermission('asc:bot-prompt:query')")
    public CommonResult<PageResult<BotPromptRespVO>> getPage(@Valid BotPromptPageReqVO pageVO) {
        PageResult<BotPromptDO> pageResult = service.getPage(pageVO);
        return success(BotPromptConvert.INSTANCE.convertPage(pageResult));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出预设提示词 Excel")
    @PreAuthorize("@ss.hasPermission('asc:bot-prompt:export')")
    @OperateLog(type = EXPORT)
    public void exportExcel(@Valid BotPromptExportReqVO exportReqVO,
              HttpServletResponse response) throws IOException {
        List<BotPromptDO> list = service.getList(exportReqVO);
        // 导出 Excel
        List<BotPromptExcelVO> datas = BotPromptConvert.INSTANCE.convertList02(list);
        ExcelUtils.write(response, "预设提示词.xls", "数据", BotPromptExcelVO.class, datas);
    }

}
