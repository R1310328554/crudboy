package cn.iocoder.yudao.module.asc.controller.admin.consumedtokens;

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

import cn.iocoder.yudao.module.asc.controller.admin.consumedtokens.vo.*;
import cn.iocoder.yudao.module.asc.dal.dataobject.consumedtokens.ConsumedTokensDO;
import cn.iocoder.yudao.module.asc.convert.consumedtokens.ConsumedTokensConvert;
import cn.iocoder.yudao.module.asc.service.consumedtokens.ConsumedTokensService;

@Tag(name = "管理后台 - 租户的token消耗情况")
@RestController
@RequestMapping("/asc/consumed-tokens")
@Validated
public class ConsumedTokensController {

    @Resource
    private ConsumedTokensService service;

    @PostMapping("/create")
    @Operation(summary = "创建租户的token消耗情况")
    @PreAuthorize("@ss.hasPermission('asc:consumed-tokens:create')")
    public CommonResult<Long> create(@Valid @RequestBody ConsumedTokensCreateReqVO createReqVO) {
        return success(service.create(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新租户的token消耗情况")
    @PreAuthorize("@ss.hasPermission('asc:consumed-tokens:update')")
    public CommonResult<Boolean> update(@Valid @RequestBody ConsumedTokensUpdateReqVO updateReqVO) {
        service.update(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除租户的token消耗情况")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('asc:consumed-tokens:delete')")
    public CommonResult<Boolean> delete(@RequestParam("id") Long id) {
        service.delete(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得租户的token消耗情况")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('asc:consumed-tokens:query')")
    public CommonResult<ConsumedTokensRespVO> get(@RequestParam("id") Long id) {
        ConsumedTokensDO consumedTokens = service.get(id);
        return success(ConsumedTokensConvert.INSTANCE.convert(consumedTokens));
    }

    @GetMapping("/list")
    @Operation(summary = "获得租户的token消耗情况列表")
    @Parameter(name = "ids", description = "编号列表", required = true, example = "1024,2048")
    @PreAuthorize("@ss.hasPermission('asc:consumed-tokens:query')")
    public CommonResult<List<ConsumedTokensRespVO>> getList(@RequestParam("ids") Collection<Long> ids) {
        List<ConsumedTokensDO> list = service.getList(ids);
        return success(ConsumedTokensConvert.INSTANCE.convertList(list));
    }

    @GetMapping("/page")
    @Operation(summary = "获得租户的token消耗情况分页")
    @PreAuthorize("@ss.hasPermission('asc:consumed-tokens:query')")
    public CommonResult<PageResult<ConsumedTokensRespVO>> getPage(@Valid ConsumedTokensPageReqVO pageVO) {
        PageResult<ConsumedTokensDO> pageResult = service.getPage(pageVO);
        return success(ConsumedTokensConvert.INSTANCE.convertPage(pageResult));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出租户的token消耗情况 Excel")
    @PreAuthorize("@ss.hasPermission('asc:consumed-tokens:export')")
    @OperateLog(type = EXPORT)
    public void exportExcel(@Valid ConsumedTokensExportReqVO exportReqVO,
              HttpServletResponse response) throws IOException {
        List<ConsumedTokensDO> list = service.getList(exportReqVO);
        // 导出 Excel
        List<ConsumedTokensExcelVO> datas = ConsumedTokensConvert.INSTANCE.convertList02(list);
        ExcelUtils.write(response, "租户的token消耗情况.xls", "数据", ConsumedTokensExcelVO.class, datas);
    }

}
