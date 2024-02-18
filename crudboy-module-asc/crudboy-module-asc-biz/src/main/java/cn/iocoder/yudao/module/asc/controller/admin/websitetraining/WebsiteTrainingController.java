package cn.iocoder.yudao.module.asc.controller.admin.websitetraining;

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

import cn.iocoder.yudao.module.asc.controller.admin.websitetraining.vo.*;
import cn.iocoder.yudao.module.asc.dal.dataobject.websitetraining.WebsiteTrainingDO;
import cn.iocoder.yudao.module.asc.convert.websitetraining.WebsiteTrainingConvert;
import cn.iocoder.yudao.module.asc.service.websitetraining.WebsiteTrainingService;

@Tag(name = "管理后台 - 网站内容爬取训练")
@RestController
@RequestMapping("/asc/website-training")
@Validated
public class WebsiteTrainingController {

    @Resource
    private WebsiteTrainingService service;

    @PostMapping("/create")
    @Operation(summary = "创建网站内容爬取训练")
    @PreAuthorize("@ss.hasPermission('asc:website-training:create')")
    public CommonResult<Long> create(@Valid @RequestBody WebsiteTrainingCreateReqVO createReqVO) {
        return success(service.create(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新网站内容爬取训练")
    @PreAuthorize("@ss.hasPermission('asc:website-training:update')")
    public CommonResult<Boolean> update(@Valid @RequestBody WebsiteTrainingUpdateReqVO updateReqVO) {
        service.update(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除网站内容爬取训练")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('asc:website-training:delete')")
    public CommonResult<Boolean> delete(@RequestParam("id") Long id) {
        service.delete(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得网站内容爬取训练")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('asc:website-training:query')")
    public CommonResult<WebsiteTrainingRespVO> get(@RequestParam("id") Long id) {
        WebsiteTrainingDO websiteTraining = service.get(id);
        return success(WebsiteTrainingConvert.INSTANCE.convert(websiteTraining));
    }

    @GetMapping("/list")
    @Operation(summary = "获得网站内容爬取训练列表")
    @Parameter(name = "ids", description = "编号列表", required = true, example = "1024,2048")
    @PreAuthorize("@ss.hasPermission('asc:website-training:query')")
    public CommonResult<List<WebsiteTrainingRespVO>> getList(@RequestParam("ids") Collection<Long> ids) {
        List<WebsiteTrainingDO> list = service.getList(ids);
        return success(WebsiteTrainingConvert.INSTANCE.convertList(list));
    }

    @GetMapping("/page")
    @Operation(summary = "获得网站内容爬取训练分页")
    @PreAuthorize("@ss.hasPermission('asc:website-training:query')")
    public CommonResult<PageResult<WebsiteTrainingRespVO>> getPage(@Valid WebsiteTrainingPageReqVO pageVO) {
        PageResult<WebsiteTrainingDO> pageResult = service.getPage(pageVO);
        return success(WebsiteTrainingConvert.INSTANCE.convertPage(pageResult));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出网站内容爬取训练 Excel")
    @PreAuthorize("@ss.hasPermission('asc:website-training:export')")
    @OperateLog(type = EXPORT)
    public void exportExcel(@Valid WebsiteTrainingExportReqVO exportReqVO,
              HttpServletResponse response) throws IOException {
        List<WebsiteTrainingDO> list = service.getList(exportReqVO);
        // 导出 Excel
        List<WebsiteTrainingExcelVO> datas = WebsiteTrainingConvert.INSTANCE.convertList02(list);
        ExcelUtils.write(response, "网站内容爬取训练.xls", "数据", WebsiteTrainingExcelVO.class, datas);
    }

}
