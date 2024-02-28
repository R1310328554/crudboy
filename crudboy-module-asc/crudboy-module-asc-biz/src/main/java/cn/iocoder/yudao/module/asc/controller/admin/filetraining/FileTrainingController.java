package cn.iocoder.yudao.module.asc.controller.admin.filetraining;

import cn.iocoder.yudao.module.asc.controller.admin.websitetraining.vo.WebsiteTrainingRespVO;
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

import cn.iocoder.yudao.module.asc.controller.admin.filetraining.vo.*;
import cn.iocoder.yudao.module.asc.dal.dataobject.filetraining.FileTrainingDO;
import cn.iocoder.yudao.module.asc.convert.filetraining.FileTrainingConvert;
import cn.iocoder.yudao.module.asc.service.filetraining.FileTrainingService;

@Tag(name = "管理后台 - 文档训练")
@RestController
@RequestMapping("/asc/file-training")
@Validated
public class FileTrainingController {

    @Resource
    private FileTrainingService service;

    @PostMapping("/create")
    @Operation(summary = "创建文档训练")
    @PreAuthorize("@ss.hasPermission('asc:file-training:create')")
    public CommonResult<Long> create(@Valid @RequestBody FileTrainingCreateReqVO createReqVO) {
        return success(service.create(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新文档训练")
    @PreAuthorize("@ss.hasPermission('asc:file-training:update')")
    public CommonResult<Boolean> update(@Valid @RequestBody FileTrainingUpdateReqVO updateReqVO) {
        service.update(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除文档训练")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('asc:file-training:delete')")
    public CommonResult<Boolean> delete(@RequestParam("id") Long id) {
        service.delete(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得文档训练")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('asc:file-training:query')")
    public CommonResult<FileTrainingRespVO> get(@RequestParam("id") Long id) {
        FileTrainingDO fileTraining = service.get(id);
        return success(FileTrainingConvert.INSTANCE.convert(fileTraining));
    }

    @GetMapping("/list")
    @Operation(summary = "获得文档训练列表")
    @Parameter(name = "ids", description = "编号列表", required = true, example = "1024,2048")
    @PreAuthorize("@ss.hasPermission('asc:file-training:query')")
    public CommonResult<List<FileTrainingRespVO>> getList(@RequestParam("ids") Collection<Long> ids) {
        List<FileTrainingDO> list = service.getList(ids);
        return success(FileTrainingConvert.INSTANCE.convertList(list));
    }

    @GetMapping("/page")
    @Operation(summary = "获得文档训练分页")
    @PreAuthorize("@ss.hasPermission('asc:file-training:query')")
    public CommonResult<PageResult<FileTrainingRespVO>> getPage(@Valid FileTrainingPageReqVO pageVO) {
        PageResult<FileTrainingDO> pageResult = service.getPage(pageVO);
        return success(FileTrainingConvert.INSTANCE.convertPage(pageResult));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出文档训练 Excel")
    @PreAuthorize("@ss.hasPermission('asc:file-training:export')")
    @OperateLog(type = EXPORT)
    public void exportExcel(@Valid FileTrainingExportReqVO exportReqVO,
              HttpServletResponse response) throws IOException {
        List<FileTrainingDO> list = service.getList(exportReqVO);
        // 导出 Excel
        List<FileTrainingExcelVO> datas = FileTrainingConvert.INSTANCE.convertList02(list);
        ExcelUtils.write(response, "文档训练.xls", "数据", FileTrainingExcelVO.class, datas);
    }

    @GetMapping("/train")
    @Operation(summary = "解析并训练文档")
    @PreAuthorize("@ss.hasPermission('asc:file-training:update')")
    public CommonResult<List<FileTrainingRespVO>> train(@Valid List<Long> ids) {
        List<FileTrainingRespVO> list = service.train(ids);
        return success(list);
//        return success(WebsiteTrainingConvert.INSTANCE.convertList(list));
    }

}
