//package cn.iocoder.yudao.module.asc.controller.admin.tenant;
//
//import org.springframework.web.bind.annotation.*;
//import javax.annotation.Resource;
//import org.springframework.validation.annotation.Validated;
//import org.springframework.security.access.prepost.PreAuthorize;
//import io.swagger.v3.oas.annotations.tags.Tag;
//import io.swagger.v3.oas.annotations.Parameter;
//import io.swagger.v3.oas.annotations.Operation;
//
//import javax.validation.constraints.*;
//import javax.validation.*;
//import javax.servlet.http.*;
//import java.util.*;
//import java.io.IOException;
//import java.time.LocalDateTime;
//import java.time.LocalDate;
//
//import cn.iocoder.yudao.framework.common.pojo.PageResult;
//import cn.iocoder.yudao.framework.common.pojo.CommonResult;
//import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;
//
//import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
//
//import cn.iocoder.yudao.framework.operatelog.core.annotations.OperateLog;
//import static cn.iocoder.yudao.framework.operatelog.core.enums.OperateTypeEnum.*;
//
//import cn.iocoder.yudao.module.asc.controller.admin.tenant.vo.*;
//import cn.iocoder.yudao.module.asc.dal.dataobject.tenant.TenantDO;
//import cn.iocoder.yudao.module.asc.convert.tenant.TenantConvert;
//import cn.iocoder.yudao.module.asc.service.tenant.TenantService;
//
//@Tag(name = "管理后台 - 租户")
//@RestController
//@RequestMapping("/asc/tenant")
//@Validated
//public class TenantController {
//
//    @Resource
//    private TenantService service;
//
//    @PostMapping("/create")
//    @Operation(summary = "创建租户")
//    @PreAuthorize("@ss.hasPermission('asc:tenant:create')")
//    public CommonResult<Long> create(@Valid @RequestBody TenantCreateReqVO createReqVO) {
//        return success(service.create(createReqVO));
//    }
//
//    @PutMapping("/update")
//    @Operation(summary = "更新租户")
//    @PreAuthorize("@ss.hasPermission('asc:tenant:update')")
//    public CommonResult<Boolean> update(@Valid @RequestBody TenantUpdateReqVO updateReqVO) {
//        service.update(updateReqVO);
//        return success(true);
//    }
//
//    @DeleteMapping("/delete")
//    @Operation(summary = "删除租户")
//    @Parameter(name = "id", description = "编号", required = true)
//    @PreAuthorize("@ss.hasPermission('asc:tenant:delete')")
//    public CommonResult<Boolean> delete(@RequestParam("id") Long id) {
//        service.delete(id);
//        return success(true);
//    }
//
//    @GetMapping("/get")
//    @Operation(summary = "获得租户")
//    @Parameter(name = "id", description = "编号", required = true, example = "1024")
//    @PreAuthorize("@ss.hasPermission('asc:tenant:query')")
//    public CommonResult<TenantRespVO> get(@RequestParam("id") Long id) {
//        TenantDO tenant = service.get(id);
//        return success(TenantConvert.INSTANCE.convert(tenant));
//    }
//
//    @GetMapping("/list")
//    @Operation(summary = "获得租户列表")
//    @Parameter(name = "ids", description = "编号列表", required = true, example = "1024,2048")
//    @PreAuthorize("@ss.hasPermission('asc:tenant:query')")
//    public CommonResult<List<TenantRespVO>> getList(@RequestParam("ids") Collection<Long> ids) {
//        List<TenantDO> list = service.getList(ids);
//        return success(TenantConvert.INSTANCE.convertList(list));
//    }
//
//    @GetMapping("/page")
//    @Operation(summary = "获得租户分页")
//    @PreAuthorize("@ss.hasPermission('asc:tenant:query')")
//    public CommonResult<PageResult<TenantRespVO>> getPage(@Valid TenantPageReqVO pageVO) {
//        PageResult<TenantDO> pageResult = service.getPage(pageVO);
//        return success(TenantConvert.INSTANCE.convertPage(pageResult));
//    }
//
//    @GetMapping("/export-excel")
//    @Operation(summary = "导出租户 Excel")
//    @PreAuthorize("@ss.hasPermission('asc:tenant:export')")
//    @OperateLog(type = EXPORT)
//    public void exportExcel(@Valid TenantExportReqVO exportReqVO,
//              HttpServletResponse response) throws IOException {
//        List<TenantDO> list = service.getList(exportReqVO);
//        // 导出 Excel
//        List<TenantExcelVO> datas = TenantConvert.INSTANCE.convertList02(list);
//        ExcelUtils.write(response, "租户.xls", "数据", TenantExcelVO.class, datas);
//    }
//
//}
