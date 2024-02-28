package cn.iocoder.yudao.module.asc.controller.admin.userinfocollection;

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

import cn.iocoder.yudao.module.asc.controller.admin.userinfocollection.vo.*;
import cn.iocoder.yudao.module.asc.dal.dataobject.userinfocollection.UserInfoCollectionDO;
import cn.iocoder.yudao.module.asc.convert.userinfocollection.UserInfoCollectionConvert;
import cn.iocoder.yudao.module.asc.service.userinfocollection.UserInfoCollectionService;

@Tag(name = "ASC接口 - 客户信息收集")
@RestController
@RequestMapping("/asc/user-info-collection")
@Validated
public class UserInfoCollectionController {

    @Resource
    private UserInfoCollectionService service;

    @PostMapping("/create")
    @Operation(summary = "创建客户信息收集")
    @PreAuthorize("@ss.hasPermission('asc:user-info-collection:create')")
    public CommonResult<Long> create(@Valid @RequestBody UserInfoCollectionCreateReqVO createReqVO) {
        return success(service.create(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新客户信息收集")
    @PreAuthorize("@ss.hasPermission('asc:user-info-collection:update')")
    public CommonResult<Boolean> update(@Valid @RequestBody UserInfoCollectionUpdateReqVO updateReqVO) {
        service.update(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除客户信息收集")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('asc:user-info-collection:delete')")
    public CommonResult<Boolean> delete(@RequestParam("id") Long id) {
        service.delete(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得客户信息收集")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('asc:user-info-collection:query')")
    public CommonResult<UserInfoCollectionRespVO> get(@RequestParam("id") Long id) {
        UserInfoCollectionDO userInfoCollection = service.get(id);
        return success(UserInfoCollectionConvert.INSTANCE.convert(userInfoCollection));
    }

    @GetMapping("/list")
    @Operation(summary = "获得客户信息收集列表")
    @Parameter(name = "ids", description = "编号列表", required = true, example = "1024,2048")
    @PreAuthorize("@ss.hasPermission('asc:user-info-collection:query')")
    public CommonResult<List<UserInfoCollectionRespVO>> getList(@RequestParam("ids") Collection<Long> ids) {
        List<UserInfoCollectionDO> list = service.getList(ids);
        return success(UserInfoCollectionConvert.INSTANCE.convertList(list));
    }

    @GetMapping("/page")
    @Operation(summary = "获得客户信息收集分页")
    @PreAuthorize("@ss.hasPermission('asc:user-info-collection:query')")
    public CommonResult<PageResult<UserInfoCollectionRespVO>> getPage(@Valid UserInfoCollectionPageReqVO pageVO) {
        PageResult<UserInfoCollectionDO> pageResult = service.getPage(pageVO);
        return success(UserInfoCollectionConvert.INSTANCE.convertPage(pageResult));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出客户信息收集 Excel")
    @PreAuthorize("@ss.hasPermission('asc:user-info-collection:export')")
    @OperateLog(type = EXPORT)
    public void exportExcel(@Valid UserInfoCollectionExportReqVO exportReqVO,
              HttpServletResponse response) throws IOException {
        List<UserInfoCollectionDO> list = service.getList(exportReqVO);
        // 导出 Excel
        List<UserInfoCollectionExcelVO> datas = UserInfoCollectionConvert.INSTANCE.convertList02(list);
        ExcelUtils.write(response, "客户信息收集.xls", "数据", UserInfoCollectionExcelVO.class, datas);
    }

}
