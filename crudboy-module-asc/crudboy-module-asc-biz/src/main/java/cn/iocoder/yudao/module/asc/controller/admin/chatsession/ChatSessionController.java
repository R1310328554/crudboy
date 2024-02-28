package cn.iocoder.yudao.module.asc.controller.admin.chatsession;

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

import cn.iocoder.yudao.module.asc.controller.admin.chatsession.vo.*;
import cn.iocoder.yudao.module.asc.dal.dataobject.chatsession.ChatSessionDO;
import cn.iocoder.yudao.module.asc.convert.chatsession.ChatSessionConvert;
import cn.iocoder.yudao.module.asc.service.chatsession.ChatSessionService;

@Tag(name = "ASC接口 - 聊天的会话")
@RestController
@RequestMapping("/asc/chat-session")
@Validated
public class ChatSessionController {

    @Resource
    private ChatSessionService service;

    @PostMapping("/create")
    @Operation(summary = "创建聊天的会话")
    @PreAuthorize("@ss.hasPermission('asc:chat-session:create')")
    public CommonResult<Long> create(@Valid @RequestBody ChatSessionCreateReqVO createReqVO) {
        return success(service.create(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新聊天的会话")
    @PreAuthorize("@ss.hasPermission('asc:chat-session:update')")
    public CommonResult<Boolean> update(@Valid @RequestBody ChatSessionUpdateReqVO updateReqVO) {
        service.update(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除聊天的会话")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('asc:chat-session:delete')")
    public CommonResult<Boolean> delete(@RequestParam("id") Long id) {
        service.delete(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得聊天的会话")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('asc:chat-session:query')")
    public CommonResult<ChatSessionRespVO> get(@RequestParam("id") Long id) {
        ChatSessionDO chatSession = service.get(id);
        return success(ChatSessionConvert.INSTANCE.convert(chatSession));
    }

    @GetMapping("/list")
    @Operation(summary = "获得聊天的会话列表")
    @Parameter(name = "ids", description = "编号列表", required = true, example = "1024,2048")
    @PreAuthorize("@ss.hasPermission('asc:chat-session:query')")
    public CommonResult<List<ChatSessionRespVO>> getList(@RequestParam("ids") Collection<Long> ids) {
        List<ChatSessionDO> list = service.getList(ids);
        return success(ChatSessionConvert.INSTANCE.convertList(list));
    }

    @GetMapping("/page")
    @Operation(summary = "获得聊天的会话分页")
    @PreAuthorize("@ss.hasPermission('asc:chat-session:query')")
    public CommonResult<PageResult<ChatSessionRespVO>> getPage(@Valid ChatSessionPageReqVO pageVO) {
        PageResult<ChatSessionDO> pageResult = service.getPage(pageVO);
        return success(ChatSessionConvert.INSTANCE.convertPage(pageResult));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出聊天的会话 Excel")
    @PreAuthorize("@ss.hasPermission('asc:chat-session:export')")
    @OperateLog(type = EXPORT)
    public void exportExcel(@Valid ChatSessionExportReqVO exportReqVO,
              HttpServletResponse response) throws IOException {
        List<ChatSessionDO> list = service.getList(exportReqVO);
        // 导出 Excel
        List<ChatSessionExcelVO> datas = ChatSessionConvert.INSTANCE.convertList02(list);
        ExcelUtils.write(response, "聊天的会话.xls", "数据", ChatSessionExcelVO.class, datas);
    }

}
