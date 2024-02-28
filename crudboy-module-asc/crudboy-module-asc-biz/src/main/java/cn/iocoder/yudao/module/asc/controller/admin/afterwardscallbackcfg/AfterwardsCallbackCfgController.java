package cn.iocoder.yudao.module.asc.controller.admin.afterwardscallbackcfg;

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

import cn.iocoder.yudao.module.asc.controller.admin.afterwardscallbackcfg.vo.*;
import cn.iocoder.yudao.module.asc.dal.dataobject.afterwardscallbackcfg.AfterwardsCallbackCfgDO;
import cn.iocoder.yudao.module.asc.convert.afterwardscallbackcfg.AfterwardsCallbackCfgConvert;
import cn.iocoder.yudao.module.asc.service.afterwardscallbackcfg.AfterwardsCallbackCfgService;

@Tag(name = "ASC接口 - 后续主动沟通的询问方案")
@RestController
@RequestMapping("/asc/afterwards-callback-cfg")
@Validated
public class AfterwardsCallbackCfgController {

    @Resource
    private AfterwardsCallbackCfgService service;

    @PostMapping("/create")
    @Operation(summary = "创建后续主动沟通的询问方案")
    @PreAuthorize("@ss.hasPermission('asc:afterwards-callback-cfg:create')")
    public CommonResult<Long> create(@Valid @RequestBody AfterwardsCallbackCfgCreateReqVO createReqVO) {
        return success(service.create(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新后续主动沟通的询问方案")
    @PreAuthorize("@ss.hasPermission('asc:afterwards-callback-cfg:update')")
    public CommonResult<Boolean> update(@Valid @RequestBody AfterwardsCallbackCfgUpdateReqVO updateReqVO) {
        service.update(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除后续主动沟通的询问方案")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('asc:afterwards-callback-cfg:delete')")
    public CommonResult<Boolean> delete(@RequestParam("id") Long id) {
        service.delete(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得后续主动沟通的询问方案")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('asc:afterwards-callback-cfg:query')")
    public CommonResult<AfterwardsCallbackCfgRespVO> get(@RequestParam("id") Long id) {
        AfterwardsCallbackCfgDO afterwardsCallbackCfg = service.get(id);
        return success(AfterwardsCallbackCfgConvert.INSTANCE.convert(afterwardsCallbackCfg));
    }

    @GetMapping("/list")
    @Operation(summary = "获得后续主动沟通的询问方案列表")
    @Parameter(name = "ids", description = "编号列表", required = true, example = "1024,2048")
    @PreAuthorize("@ss.hasPermission('asc:afterwards-callback-cfg:query')")
    public CommonResult<List<AfterwardsCallbackCfgRespVO>> getList(@RequestParam("ids") Collection<Long> ids) {
        List<AfterwardsCallbackCfgDO> list = service.getList(ids);
        return success(AfterwardsCallbackCfgConvert.INSTANCE.convertList(list));
    }

    @GetMapping("/page")
    @Operation(summary = "获得后续主动沟通的询问方案分页")
    @PreAuthorize("@ss.hasPermission('asc:afterwards-callback-cfg:query')")
    public CommonResult<PageResult<AfterwardsCallbackCfgRespVO>> getPage(@Valid AfterwardsCallbackCfgPageReqVO pageVO) {
        PageResult<AfterwardsCallbackCfgDO> pageResult = service.getPage(pageVO);
        return success(AfterwardsCallbackCfgConvert.INSTANCE.convertPage(pageResult));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出后续主动沟通的询问方案 Excel")
    @PreAuthorize("@ss.hasPermission('asc:afterwards-callback-cfg:export')")
    @OperateLog(type = EXPORT)
    public void exportExcel(@Valid AfterwardsCallbackCfgExportReqVO exportReqVO,
              HttpServletResponse response) throws IOException {
        List<AfterwardsCallbackCfgDO> list = service.getList(exportReqVO);
        // 导出 Excel
        List<AfterwardsCallbackCfgExcelVO> datas = AfterwardsCallbackCfgConvert.INSTANCE.convertList02(list);
        ExcelUtils.write(response, "后续主动沟通的询问方案.xls", "数据", AfterwardsCallbackCfgExcelVO.class, datas);
    }

}
