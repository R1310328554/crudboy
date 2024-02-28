package cn.iocoder.yudao.module.asc.controller.admin.faqtraining;

import cn.iocoder.yudao.module.asc.controller.admin.filetraining.vo.FileTrainingRespVO;
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

import cn.iocoder.yudao.module.asc.controller.admin.faqtraining.vo.*;
import cn.iocoder.yudao.module.asc.dal.dataobject.faqtraining.FaqTrainingDO;
import cn.iocoder.yudao.module.asc.convert.faqtraining.FaqTrainingConvert;
import cn.iocoder.yudao.module.asc.service.faqtraining.FaqTrainingService;

@Tag(name = "ASC接口 - 百问百答训练")
@RestController
@RequestMapping("/asc/faq-training")
@Validated
public class FaqTrainingController {

    @Resource
    private FaqTrainingService service;

    @PostMapping("/create")
    @Operation(summary = "创建百问百答训练")
    @PreAuthorize("@ss.hasPermission('asc:faq-training:create')")
    public CommonResult<Long> create(@Valid @RequestBody FaqTrainingCreateReqVO createReqVO) {
        return success(service.create(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新百问百答训练")
    @PreAuthorize("@ss.hasPermission('asc:faq-training:update')")
    public CommonResult<Boolean> update(@Valid @RequestBody FaqTrainingUpdateReqVO updateReqVO) {
        service.update(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除百问百答训练")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('asc:faq-training:delete')")
    public CommonResult<Boolean> delete(@RequestParam("id") Long id) {
        service.delete(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得百问百答训练")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('asc:faq-training:query')")
    public CommonResult<FaqTrainingRespVO> get(@RequestParam("id") Long id) {
        FaqTrainingDO faqTraining = service.get(id);
        return success(FaqTrainingConvert.INSTANCE.convert(faqTraining));
    }

    @GetMapping("/list")
    @Operation(summary = "获得百问百答训练列表")
    @Parameter(name = "ids", description = "编号列表", required = true, example = "1024,2048")
    @PreAuthorize("@ss.hasPermission('asc:faq-training:query')")
    public CommonResult<List<FaqTrainingRespVO>> getList(@RequestParam("ids") Collection<Long> ids) {
        List<FaqTrainingDO> list = service.getList(ids);
        return success(FaqTrainingConvert.INSTANCE.convertList(list));
    }

    @GetMapping("/page")
    @Operation(summary = "获得百问百答训练分页")
    @PreAuthorize("@ss.hasPermission('asc:faq-training:query')")
    public CommonResult<PageResult<FaqTrainingRespVO>> getPage(@Valid FaqTrainingPageReqVO pageVO) {
        PageResult<FaqTrainingDO> pageResult = service.getPage(pageVO);
        return success(FaqTrainingConvert.INSTANCE.convertPage(pageResult));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出百问百答训练 Excel")
    @PreAuthorize("@ss.hasPermission('asc:faq-training:export')")
    @OperateLog(type = EXPORT)
    public void exportExcel(@Valid FaqTrainingExportReqVO exportReqVO,
              HttpServletResponse response) throws IOException {
        List<FaqTrainingDO> list = service.getList(exportReqVO);
        // 导出 Excel
        List<FaqTrainingExcelVO> datas = FaqTrainingConvert.INSTANCE.convertList02(list);
        ExcelUtils.write(response, "百问百答训练.xls", "数据", FaqTrainingExcelVO.class, datas);
    }


    @GetMapping("/train")
    @Operation(summary = "解析并训练文档")
    @PreAuthorize("@ss.hasPermission('asc:faq-training:update')")
    public CommonResult<List<FaqTrainingRespVO>> train(@Valid List<Long> ids) {
        List<FaqTrainingRespVO> list = service.train(ids);
        return success(list);
//        return success(WebsiteTrainingConvert.INSTANCE.convertList(list));
    }

}
