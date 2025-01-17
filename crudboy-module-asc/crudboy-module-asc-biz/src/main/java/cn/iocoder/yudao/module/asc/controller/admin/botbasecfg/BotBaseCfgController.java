package cn.iocoder.yudao.module.asc.controller.admin.botbasecfg;

import cn.iocoder.yudao.module.asc.service.filetraining.FileTrainingService;
import cn.iocoder.yudao.module.system.dal.dataobject.tenant.TenantDO;
import cn.iocoder.yudao.module.system.service.tenant.TenantService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.util.MultiValueMap;
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
import java.math.BigDecimal;
import java.net.InetSocketAddress;
import java.util.*;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalDate;
import java.util.stream.Collectors;

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

@Tag(name = "ASC接口 - 机器人的基础配置")
@RestController
@RequestMapping("/asc/bot-base-cfg")
@Validated
@Slf4j
public class BotBaseCfgController {

    @Resource
    private BotBaseCfgService service;

    @Resource
    private FileTrainingService fileTrainingService;
    @Resource
    private TenantService tenantService;

    @PostMapping("/create")
    @Operation(summary = "创建机器人的基础配置")
    @PreAuthorize("@ss.hasPermission('asc:bot-base-cfg:create')")
    public CommonResult<Long> create(@Valid @RequestBody BotBaseCfgCreateReqVO createReqVO) {
        BigDecimal temperature = createReqVO.getTemperature();
        if (temperature.compareTo(BigDecimal.ONE) > 0) {
            System.err.println("temperature = " + temperature);
        } else if (temperature.compareTo(BigDecimal.ZERO) < 0) {
            System.err.println("temperature = " + temperature);
        }
        return success(service.create(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新机器人的基础配置")
    @PreAuthorize("@ss.hasPermission('asc:bot-base-cfg:update')")
    public CommonResult<Boolean> update(@Valid @RequestBody BotBaseCfgUpdateReqVO updateReqVO) {
        BigDecimal temperature = updateReqVO.getTemperature();
        if (temperature.compareTo(BigDecimal.ONE) > 0) {
            System.err.println("temperature = " + temperature);
        } else if (temperature.compareTo(BigDecimal.ZERO) < 0) {
            System.err.println("temperature = " + temperature);
        }
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


    @PutMapping("/start")
    @Operation(summary = "启动机器人")
    @PreAuthorize("@ss.hasPermission('asc:bot-base-cfg:update')")
    public CommonResult<Boolean> start(@Valid @RequestBody BotBaseCfgUpdateReqVO updateReqVO) {
        service.update(updateReqVO);
        return success(true);
    }

    @PutMapping("/stop")
    @Operation(summary = "停止机器人")
    @PreAuthorize("@ss.hasPermission('asc:bot-base-cfg:update')")
    public CommonResult<Boolean> stop(@Valid @RequestBody BotBaseCfgUpdateReqVO updateReqVO) {
        service.update(updateReqVO);
        return success(true);
    }

    @PutMapping("/chat")
    @Operation(summary = "聊天")
    @PreAuthorize("@ss.hasPermission('asc:bot-base-cfg:update')")
    public CommonResult<Boolean> chat(@Valid @RequestBody BotBaseCfgUpdateReqVO updateReqVO) {
        service.update(updateReqVO);
        return success(true);
    }

    @GetMapping("/createKnowledgeBase")
    @Operation(summary = "创建知识库")
    @PreAuthorize("@ss.hasPermission('asc:file-training:update')")
    public CommonResult<String> createKnowledgeBase(String knowledge_base_name) {
        String ret = fileTrainingService.createKnowledgeBase(knowledge_base_name);
        return success(ret);
//        return success(WebsiteTrainingConvert.INSTANCE.convertList(list));
    }

    @PutMapping("/initTenantKnowledgeBase")
    @Operation(summary = "给租户创建对应的 知识库")
    @PreAuthorize("@ss.hasPermission('asc:bot-base-cfg:update')")
    // curl -X GET -H  "Accept:*/*" -H  "tenant-id:1" -H  "Authorization:Bearer test1" -H  "Content-Type:application/x-www-form-urlencoded" "http://localhost:48080/admin-api/asc/chat-msg/page?pageNo=1&pageSize=10&direction=false&chatId=4
    //    public CommonResult<Boolean> initTenantKnowledgeBase(@Header("tenant-id") Long tenantId) {
    public CommonResult<Boolean> initTenantKnowledgeBase(@RequestHeader("tenant-id") Long tenantId) {
        TenantDO t = tenantService.getTenant(tenantId);
        String fileCategorys = t.getFileCategorys();
        String[] split = fileCategorys.split("[,，;； ]");
        for (int i = 0; i < split.length; i++) {
            String knowledge_base_name = split[i];
            log.info("knowledge_base_name = " + knowledge_base_name);
            String ret = fileTrainingService.createKnowledgeBase(knowledge_base_name);
            log.info("knowledge_base_name" + knowledge_base_name + " created,    ret = " + ret);
        }
        return success(true);
    }

    @GetMapping("/multiValue")
    public ResponseEntity<String> multiValue(
            @RequestHeader MultiValueMap<String, String> headers) {
        headers.forEach((key, value) -> {
            log.info(String.format(
                    "Header '%s' = %s", key, value.stream().collect(Collectors.joining("|"))));
        });

        return new ResponseEntity<String>(
                String.format("Listed %d headers", headers.size()), HttpStatus.OK);
    }

//    @GetMapping("/getBaseUrl")
//    public ResponseEntity<String> getBaseUrl(@RequestHeader HttpHeaders headers) {
//        InetSocketAddress host = headers.getHost();
//        String url = "http://" + host.getHostName() + ":" + host.getPort();
//        return new ResponseEntity<String>(String.format("Base URL = %s", url), HttpStatus.OK);
//    }


}
