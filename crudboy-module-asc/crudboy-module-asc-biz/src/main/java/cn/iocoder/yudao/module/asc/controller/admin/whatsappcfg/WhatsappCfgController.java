package cn.iocoder.yudao.module.asc.controller.admin.whatsappcfg;

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

import cn.iocoder.yudao.module.asc.controller.admin.whatsappcfg.vo.*;
import cn.iocoder.yudao.module.asc.dal.dataobject.whatsappcfg.WhatsappCfgDO;
import cn.iocoder.yudao.module.asc.convert.whatsappcfg.WhatsappCfgConvert;
import cn.iocoder.yudao.module.asc.service.whatsappcfg.WhatsappCfgService;

@Tag(name = "管理后台 - WhatsApp 平台配置")
@RestController
@RequestMapping("/asc/whatsapp-cfg")
@Validated
public class WhatsappCfgController {

    @Resource
    private WhatsappCfgService service;

    @PostMapping("/create")
    @Operation(summary = "创建WhatsApp 平台配置")
    @PreAuthorize("@ss.hasPermission('asc:whatsapp-cfg:create')")
    public CommonResult<Long> create(@Valid @RequestBody WhatsappCfgCreateReqVO createReqVO) {
        return success(service.create(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新WhatsApp 平台配置")
    @PreAuthorize("@ss.hasPermission('asc:whatsapp-cfg:update')")
    public CommonResult<Boolean> update(@Valid @RequestBody WhatsappCfgUpdateReqVO updateReqVO) {
        service.update(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除WhatsApp 平台配置")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('asc:whatsapp-cfg:delete')")
    public CommonResult<Boolean> delete(@RequestParam("id") Long id) {
        service.delete(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得WhatsApp 平台配置")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('asc:whatsapp-cfg:query')")
    public CommonResult<WhatsappCfgRespVO> get(@RequestParam("id") Long id) {
        WhatsappCfgDO whatsappCfg = service.get(id);
        return success(WhatsappCfgConvert.INSTANCE.convert(whatsappCfg));
    }

    @GetMapping("/list")
    @Operation(summary = "获得WhatsApp 平台配置列表")
    @Parameter(name = "ids", description = "编号列表", required = true, example = "1024,2048")
    @PreAuthorize("@ss.hasPermission('asc:whatsapp-cfg:query')")
    public CommonResult<List<WhatsappCfgRespVO>> getList(@RequestParam("ids") Collection<Long> ids) {
        List<WhatsappCfgDO> list = service.getList(ids);
        return success(WhatsappCfgConvert.INSTANCE.convertList(list));
    }

    @GetMapping("/page")
    @Operation(summary = "获得WhatsApp 平台配置分页")
    @PreAuthorize("@ss.hasPermission('asc:whatsapp-cfg:query')")
    public CommonResult<PageResult<WhatsappCfgRespVO>> getPage(@Valid WhatsappCfgPageReqVO pageVO) {
        PageResult<WhatsappCfgDO> pageResult = service.getPage(pageVO);
        return success(WhatsappCfgConvert.INSTANCE.convertPage(pageResult));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出WhatsApp 平台配置 Excel")
    @PreAuthorize("@ss.hasPermission('asc:whatsapp-cfg:export')")
    @OperateLog(type = EXPORT)
    public void exportExcel(@Valid WhatsappCfgExportReqVO exportReqVO,
              HttpServletResponse response) throws IOException {
        List<WhatsappCfgDO> list = service.getList(exportReqVO);
        // 导出 Excel
        List<WhatsappCfgExcelVO> datas = WhatsappCfgConvert.INSTANCE.convertList02(list);
        ExcelUtils.write(response, "WhatsApp 平台配置.xls", "数据", WhatsappCfgExcelVO.class, datas);
    }

}
