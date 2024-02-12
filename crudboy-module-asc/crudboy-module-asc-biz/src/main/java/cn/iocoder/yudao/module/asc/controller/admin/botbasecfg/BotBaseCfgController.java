package cn.iocoder.yudao.module.asc.controller.admin.botbasecfg;

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

import cn.iocoder.yudao.module.asc.controller.admin.botbasecfg.vo.*;
import cn.iocoder.yudao.module.asc.dal.dataobject.botbasecfg.BotBaseCfgDO;
import cn.iocoder.yudao.module.asc.convert.botbasecfg.BotBaseCfgConvert;
import cn.iocoder.yudao.module.asc.service.botbasecfg.BotBaseCfgService;

@Tag(name = "管理后台 - 机器人的基础配置")
@RestController
@RequestMapping("/asc/bot-base-cfg")
@Validated
public class BotBaseCfgController {

    @Resource
    private BotBaseCfgService service;

    @PostMapping("/create")
    @Operation(summary = "创建机器人的基础配置")
    @PreAuthorize("@ss.hasPermission('asc:bot-base-cfg:create')")
    public CommonResult<Long> create(@Valid @RequestBody BotBaseCfgCreateReqVO createReqVO) {
        return success(service.create(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新机器人的基础配置")
    @PreAuthorize("@ss.hasPermission('asc:bot-base-cfg:update')")
    public CommonResult<Boolean> update(@Valid @RequestBody BotBaseCfgUpdateReqVO updateReqVO) {
        service.update(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除机器人的基础配置")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('asc:bot-base-cfg:delete')")
    public CommonResult<Boolean> delete(@RequestParam("id") Long id) {
        service.delete(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得机器人的基础配置")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('asc:bot-base-cfg:query')")
    public CommonResult<BotBaseCfgRespVO> get(@RequestParam("id") Long id) {
        BotBaseCfgDO botBaseCfg = service.get(id);
        return success(BotBaseCfgConvert.INSTANCE.convert(botBaseCfg));
    }

    @GetMapping("/list")
    @Operation(summary = "获得机器人的基础配置列表")
    @Parameter(name = "ids", description = "编号列表", required = true, example = "1024,2048")
    @PreAuthorize("@ss.hasPermission('asc:bot-base-cfg:query')")
    public CommonResult<List<BotBaseCfgRespVO>> getList(@RequestParam("ids") Collection<Long> ids) {
        List<BotBaseCfgDO> list = service.getList(ids);
        return success(BotBaseCfgConvert.INSTANCE.convertList(list));
    }

    @GetMapping("/page")
    @Operation(summary = "获得机器人的基础配置分页")
    @PreAuthorize("@ss.hasPermission('asc:bot-base-cfg:query')")
    public CommonResult<PageResult<BotBaseCfgRespVO>> getPage(@Valid BotBaseCfgPageReqVO pageVO) {
        PageResult<BotBaseCfgDO> pageResult = service.getPage(pageVO);
        return success(BotBaseCfgConvert.INSTANCE.convertPage(pageResult));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出机器人的基础配置 Excel")
    @PreAuthorize("@ss.hasPermission('asc:bot-base-cfg:export')")
    @OperateLog(type = EXPORT)
    public void exportExcel(@Valid BotBaseCfgExportReqVO exportReqVO,
              HttpServletResponse response) throws IOException {
        List<BotBaseCfgDO> list = service.getList(exportReqVO);
        // 导出 Excel
        List<BotBaseCfgExcelVO> datas = BotBaseCfgConvert.INSTANCE.convertList02(list);
        ExcelUtils.write(response, "机器人的基础配置.xls", "数据", BotBaseCfgExcelVO.class, datas);
    }

}
