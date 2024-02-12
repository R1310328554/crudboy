package cn.iocoder.yudao.module.asc.controller.admin.endusercfg;

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

import cn.iocoder.yudao.module.asc.controller.admin.endusercfg.vo.*;
import cn.iocoder.yudao.module.asc.dal.dataobject.endusercfg.EndUserCfgDO;
import cn.iocoder.yudao.module.asc.convert.endusercfg.EndUserCfgConvert;
import cn.iocoder.yudao.module.asc.service.endusercfg.EndUserCfgService;

@Tag(name = "管理后台 - 终端用户配置")
@RestController
@RequestMapping("/asc/end-user-cfg")
@Validated
public class EndUserCfgController {

    @Resource
    private EndUserCfgService service;

    @PostMapping("/create")
    @Operation(summary = "创建终端用户配置")
    @PreAuthorize("@ss.hasPermission('asc:end-user-cfg:create')")
    public CommonResult<Long> create(@Valid @RequestBody EndUserCfgCreateReqVO createReqVO) {
        return success(service.create(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新终端用户配置")
    @PreAuthorize("@ss.hasPermission('asc:end-user-cfg:update')")
    public CommonResult<Boolean> update(@Valid @RequestBody EndUserCfgUpdateReqVO updateReqVO) {
        service.update(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除终端用户配置")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('asc:end-user-cfg:delete')")
    public CommonResult<Boolean> delete(@RequestParam("id") Long id) {
        service.delete(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得终端用户配置")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('asc:end-user-cfg:query')")
    public CommonResult<EndUserCfgRespVO> get(@RequestParam("id") Long id) {
        EndUserCfgDO endUserCfg = service.get(id);
        return success(EndUserCfgConvert.INSTANCE.convert(endUserCfg));
    }

    @GetMapping("/list")
    @Operation(summary = "获得终端用户配置列表")
    @Parameter(name = "ids", description = "编号列表", required = true, example = "1024,2048")
    @PreAuthorize("@ss.hasPermission('asc:end-user-cfg:query')")
    public CommonResult<List<EndUserCfgRespVO>> getList(@RequestParam("ids") Collection<Long> ids) {
        List<EndUserCfgDO> list = service.getList(ids);
        return success(EndUserCfgConvert.INSTANCE.convertList(list));
    }

    @GetMapping("/page")
    @Operation(summary = "获得终端用户配置分页")
    @PreAuthorize("@ss.hasPermission('asc:end-user-cfg:query')")
    public CommonResult<PageResult<EndUserCfgRespVO>> getPage(@Valid EndUserCfgPageReqVO pageVO) {
        PageResult<EndUserCfgDO> pageResult = service.getPage(pageVO);
        return success(EndUserCfgConvert.INSTANCE.convertPage(pageResult));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出终端用户配置 Excel")
    @PreAuthorize("@ss.hasPermission('asc:end-user-cfg:export')")
    @OperateLog(type = EXPORT)
    public void exportExcel(@Valid EndUserCfgExportReqVO exportReqVO,
              HttpServletResponse response) throws IOException {
        List<EndUserCfgDO> list = service.getList(exportReqVO);
        // 导出 Excel
        List<EndUserCfgExcelVO> datas = EndUserCfgConvert.INSTANCE.convertList02(list);
        ExcelUtils.write(response, "终端用户配置.xls", "数据", EndUserCfgExcelVO.class, datas);
    }

}
