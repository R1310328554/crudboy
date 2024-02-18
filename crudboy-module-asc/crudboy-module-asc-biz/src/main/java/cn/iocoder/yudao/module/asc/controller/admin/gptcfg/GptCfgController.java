package cn.iocoder.yudao.module.asc.controller.admin.gptcfg;

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

import cn.iocoder.yudao.module.asc.controller.admin.gptcfg.vo.*;
import cn.iocoder.yudao.module.asc.dal.dataobject.gptcfg.GptCfgDO;
import cn.iocoder.yudao.module.asc.convert.gptcfg.GptCfgConvert;
import cn.iocoder.yudao.module.asc.service.gptcfg.GptCfgService;

@Tag(name = "管理后台 - GPT 专用的基础的配置")
@RestController
@RequestMapping("/asc/gpt-cfg")
@Validated
public class GptCfgController {

    @Resource
    private GptCfgService service;

    @PostMapping("/create")
    @Operation(summary = "创建GPT 专用的基础的配置")
    @PreAuthorize("@ss.hasPermission('asc:gpt-cfg:create')")
    public CommonResult<Long> create(@Valid @RequestBody GptCfgCreateReqVO createReqVO) {
        return success(service.create(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新GPT 专用的基础的配置")
    @PreAuthorize("@ss.hasPermission('asc:gpt-cfg:update')")
    public CommonResult<Boolean> update(@Valid @RequestBody GptCfgUpdateReqVO updateReqVO) {
        service.update(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除GPT 专用的基础的配置")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('asc:gpt-cfg:delete')")
    public CommonResult<Boolean> delete(@RequestParam("id") Long id) {
        service.delete(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得GPT 专用的基础的配置")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('asc:gpt-cfg:query')")
    public CommonResult<GptCfgRespVO> get(@RequestParam("id") Long id) {
        GptCfgDO gptCfg = service.get(id);
        return success(GptCfgConvert.INSTANCE.convert(gptCfg));
    }

    @GetMapping("/list")
    @Operation(summary = "获得GPT 专用的基础的配置列表")
    @Parameter(name = "ids", description = "编号列表", required = true, example = "1024,2048")
    @PreAuthorize("@ss.hasPermission('asc:gpt-cfg:query')")
    public CommonResult<List<GptCfgRespVO>> getList(@RequestParam("ids") Collection<Long> ids) {
        List<GptCfgDO> list = service.getList(ids);
        return success(GptCfgConvert.INSTANCE.convertList(list));
    }

    @GetMapping("/page")
    @Operation(summary = "获得GPT 专用的基础的配置分页")
    @PreAuthorize("@ss.hasPermission('asc:gpt-cfg:query')")
    public CommonResult<PageResult<GptCfgRespVO>> getPage(@Valid GptCfgPageReqVO pageVO) {
        PageResult<GptCfgDO> pageResult = service.getPage(pageVO);
        return success(GptCfgConvert.INSTANCE.convertPage(pageResult));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出GPT 专用的基础的配置 Excel")
    @PreAuthorize("@ss.hasPermission('asc:gpt-cfg:export')")
    @OperateLog(type = EXPORT)
    public void exportExcel(@Valid GptCfgExportReqVO exportReqVO,
              HttpServletResponse response) throws IOException {
        List<GptCfgDO> list = service.getList(exportReqVO);
        // 导出 Excel
        List<GptCfgExcelVO> datas = GptCfgConvert.INSTANCE.convertList02(list);
        ExcelUtils.write(response, "GPT 专用的基础的配置.xls", "数据", GptCfgExcelVO.class, datas);
    }

}
