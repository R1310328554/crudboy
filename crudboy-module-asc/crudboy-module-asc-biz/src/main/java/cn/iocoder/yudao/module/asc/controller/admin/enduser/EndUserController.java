package cn.iocoder.yudao.module.asc.controller.admin.enduser;

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

import cn.iocoder.yudao.module.asc.controller.admin.enduser.vo.*;
import cn.iocoder.yudao.module.asc.dal.dataobject.enduser.EndUserDO;
import cn.iocoder.yudao.module.asc.convert.enduser.EndUserConvert;
import cn.iocoder.yudao.module.asc.service.enduser.EndUserService;

@Tag(name = "ASC接口 - 终端用户")
@RestController
@RequestMapping("/asc/end-user")
@Validated
public class EndUserController {

    @Resource
    private EndUserService service;

    @PostMapping("/create")
    @Operation(summary = "创建终端用户")
    @PreAuthorize("@ss.hasPermission('asc:end-user:create')")
    public CommonResult<Long> create(@Valid @RequestBody EndUserCreateReqVO createReqVO) {
        return success(service.create(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新终端用户")
    @PreAuthorize("@ss.hasPermission('asc:end-user:update')")
    public CommonResult<Boolean> update(@Valid @RequestBody EndUserUpdateReqVO updateReqVO) {
        service.update(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除终端用户")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('asc:end-user:delete')")
    public CommonResult<Boolean> delete(@RequestParam("id") Long id) {
        service.delete(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得终端用户")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('asc:end-user:query')")
    public CommonResult<EndUserRespVO> get(@RequestParam("id") Long id) {
        EndUserDO endUser = service.get(id);
        return success(EndUserConvert.INSTANCE.convert(endUser));
    }

    @GetMapping("/list")
    @Operation(summary = "获得终端用户列表")
    @Parameter(name = "ids", description = "编号列表", required = true, example = "1024,2048")
    @PreAuthorize("@ss.hasPermission('asc:end-user:query')")
    public CommonResult<List<EndUserRespVO>> getList(@RequestParam("ids") Collection<Long> ids) {
        List<EndUserDO> list = service.getList(ids);
        return success(EndUserConvert.INSTANCE.convertList(list));
    }

    @GetMapping("/page")
    @Operation(summary = "获得终端用户分页")
    @PreAuthorize("@ss.hasPermission('asc:end-user:query')")
    public CommonResult<PageResult<EndUserRespVO>> getPage(@Valid EndUserPageReqVO pageVO) {
        PageResult<EndUserDO> pageResult = service.getPage(pageVO);
        return success(EndUserConvert.INSTANCE.convertPage(pageResult));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出终端用户 Excel")
    @PreAuthorize("@ss.hasPermission('asc:end-user:export')")
    @OperateLog(type = EXPORT)
    public void exportExcel(@Valid EndUserExportReqVO exportReqVO,
              HttpServletResponse response) throws IOException {
        List<EndUserDO> list = service.getList(exportReqVO);
        // 导出 Excel
        List<EndUserExcelVO> datas = EndUserConvert.INSTANCE.convertList02(list);
        ExcelUtils.write(response, "终端用户.xls", "数据", EndUserExcelVO.class, datas);
    }

}
