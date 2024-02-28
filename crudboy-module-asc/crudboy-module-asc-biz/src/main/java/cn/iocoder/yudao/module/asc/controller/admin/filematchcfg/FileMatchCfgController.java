package cn.iocoder.yudao.module.asc.controller.admin.filematchcfg;

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

import cn.iocoder.yudao.module.asc.controller.admin.filematchcfg.vo.*;
import cn.iocoder.yudao.module.asc.dal.dataobject.filematchcfg.FileMatchCfgDO;
import cn.iocoder.yudao.module.asc.convert.filematchcfg.FileMatchCfgConvert;
import cn.iocoder.yudao.module.asc.service.filematchcfg.FileMatchCfgService;

@Tag(name = "ASC接口 - 关键信息和文件链接匹配")
@RestController
@RequestMapping("/asc/file-match-cfg")
@Validated
public class FileMatchCfgController {

    @Resource
    private FileMatchCfgService service;

    @PostMapping("/create")
    @Operation(summary = "创建关键信息和文件链接匹配")
    @PreAuthorize("@ss.hasPermission('asc:file-match-cfg:create')")
    public CommonResult<Long> create(@Valid @RequestBody FileMatchCfgCreateReqVO createReqVO) {
        return success(service.create(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新关键信息和文件链接匹配")
    @PreAuthorize("@ss.hasPermission('asc:file-match-cfg:update')")
    public CommonResult<Boolean> update(@Valid @RequestBody FileMatchCfgUpdateReqVO updateReqVO) {
        service.update(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除关键信息和文件链接匹配")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('asc:file-match-cfg:delete')")
    public CommonResult<Boolean> delete(@RequestParam("id") Long id) {
        service.delete(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得关键信息和文件链接匹配")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('asc:file-match-cfg:query')")
    public CommonResult<FileMatchCfgRespVO> get(@RequestParam("id") Long id) {
        FileMatchCfgDO fileMatchCfg = service.get(id);
        return success(FileMatchCfgConvert.INSTANCE.convert(fileMatchCfg));
    }

    @GetMapping("/list")
    @Operation(summary = "获得关键信息和文件链接匹配列表")
    @Parameter(name = "ids", description = "编号列表", required = true, example = "1024,2048")
    @PreAuthorize("@ss.hasPermission('asc:file-match-cfg:query')")
    public CommonResult<List<FileMatchCfgRespVO>> getList(@RequestParam("ids") Collection<Long> ids) {
        List<FileMatchCfgDO> list = service.getList(ids);
        return success(FileMatchCfgConvert.INSTANCE.convertList(list));
    }

    @GetMapping("/page")
    @Operation(summary = "获得关键信息和文件链接匹配分页")
    @PreAuthorize("@ss.hasPermission('asc:file-match-cfg:query')")
    public CommonResult<PageResult<FileMatchCfgRespVO>> getPage(@Valid FileMatchCfgPageReqVO pageVO) {
        PageResult<FileMatchCfgDO> pageResult = service.getPage(pageVO);
        return success(FileMatchCfgConvert.INSTANCE.convertPage(pageResult));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出关键信息和文件链接匹配 Excel")
    @PreAuthorize("@ss.hasPermission('asc:file-match-cfg:export')")
    @OperateLog(type = EXPORT)
    public void exportExcel(@Valid FileMatchCfgExportReqVO exportReqVO,
              HttpServletResponse response) throws IOException {
        List<FileMatchCfgDO> list = service.getList(exportReqVO);
        // 导出 Excel
        List<FileMatchCfgExcelVO> datas = FileMatchCfgConvert.INSTANCE.convertList02(list);
        ExcelUtils.write(response, "关键信息和文件链接匹配.xls", "数据", FileMatchCfgExcelVO.class, datas);
    }

}
