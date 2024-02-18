package cn.iocoder.yudao.module.asc.controller.admin.thirdpartycfg;

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

import cn.iocoder.yudao.module.asc.controller.admin.thirdpartycfg.vo.*;
import cn.iocoder.yudao.module.asc.dal.dataobject.thirdpartycfg.ThirdPartyCfgDO;
import cn.iocoder.yudao.module.asc.convert.thirdpartycfg.ThirdPartyCfgConvert;
import cn.iocoder.yudao.module.asc.service.thirdpartycfg.ThirdPartyCfgService;

@Tag(name = "管理后台 - 终端平台即第三方平台配置")
@RestController
@RequestMapping("/asc/third-party-cfg")
@Validated
public class ThirdPartyCfgController {

    @Resource
    private ThirdPartyCfgService service;

    @PostMapping("/create")
    @Operation(summary = "创建终端平台即第三方平台配置")
    @PreAuthorize("@ss.hasPermission('asc:third-party-cfg:create')")
    public CommonResult<Long> create(@Valid @RequestBody ThirdPartyCfgCreateReqVO createReqVO) {
        return success(service.create(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新终端平台即第三方平台配置")
    @PreAuthorize("@ss.hasPermission('asc:third-party-cfg:update')")
    public CommonResult<Boolean> update(@Valid @RequestBody ThirdPartyCfgUpdateReqVO updateReqVO) {
        service.update(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除终端平台即第三方平台配置")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('asc:third-party-cfg:delete')")
    public CommonResult<Boolean> delete(@RequestParam("id") Long id) {
        service.delete(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得终端平台即第三方平台配置")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('asc:third-party-cfg:query')")
    public CommonResult<ThirdPartyCfgRespVO> get(@RequestParam("id") Long id) {
        ThirdPartyCfgDO thirdPartyCfg = service.get(id);
        return success(ThirdPartyCfgConvert.INSTANCE.convert(thirdPartyCfg));
    }

    @GetMapping("/list")
    @Operation(summary = "获得终端平台即第三方平台配置列表")
    @Parameter(name = "ids", description = "编号列表", required = true, example = "1024,2048")
    @PreAuthorize("@ss.hasPermission('asc:third-party-cfg:query')")
    public CommonResult<List<ThirdPartyCfgRespVO>> getList(@RequestParam("ids") Collection<Long> ids) {
        List<ThirdPartyCfgDO> list = service.getList(ids);
        return success(ThirdPartyCfgConvert.INSTANCE.convertList(list));
    }

    @GetMapping("/page")
    @Operation(summary = "获得终端平台即第三方平台配置分页")
    @PreAuthorize("@ss.hasPermission('asc:third-party-cfg:query')")
    public CommonResult<PageResult<ThirdPartyCfgRespVO>> getPage(@Valid ThirdPartyCfgPageReqVO pageVO) {
        PageResult<ThirdPartyCfgDO> pageResult = service.getPage(pageVO);
        return success(ThirdPartyCfgConvert.INSTANCE.convertPage(pageResult));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出终端平台即第三方平台配置 Excel")
    @PreAuthorize("@ss.hasPermission('asc:third-party-cfg:export')")
    @OperateLog(type = EXPORT)
    public void exportExcel(@Valid ThirdPartyCfgExportReqVO exportReqVO,
              HttpServletResponse response) throws IOException {
        List<ThirdPartyCfgDO> list = service.getList(exportReqVO);
        // 导出 Excel
        List<ThirdPartyCfgExcelVO> datas = ThirdPartyCfgConvert.INSTANCE.convertList02(list);
        ExcelUtils.write(response, "终端平台即第三方平台配置.xls", "数据", ThirdPartyCfgExcelVO.class, datas);
    }

}
