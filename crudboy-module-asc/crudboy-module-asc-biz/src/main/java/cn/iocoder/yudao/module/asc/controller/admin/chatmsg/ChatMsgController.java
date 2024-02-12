package cn.iocoder.yudao.module.asc.controller.admin.chatmsg;

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

import cn.iocoder.yudao.module.asc.controller.admin.chatmsg.vo.*;
import cn.iocoder.yudao.module.asc.dal.dataobject.chatmsg.ChatMsgDO;
import cn.iocoder.yudao.module.asc.convert.chatmsg.ChatMsgConvert;
import cn.iocoder.yudao.module.asc.service.chatmsg.ChatMsgService;

@Tag(name = "管理后台 - 单个聊天消息")
@RestController
@RequestMapping("/asc/chat-msg")
@Validated
public class ChatMsgController {

    @Resource
    private ChatMsgService service;

    @PostMapping("/create")
    @Operation(summary = "创建单个聊天消息")
    @PreAuthorize("@ss.hasPermission('asc:chat-msg:create')")
    public CommonResult<Long> create(@Valid @RequestBody ChatMsgCreateReqVO createReqVO) {
        return success(service.create(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新单个聊天消息")
    @PreAuthorize("@ss.hasPermission('asc:chat-msg:update')")
    public CommonResult<Boolean> update(@Valid @RequestBody ChatMsgUpdateReqVO updateReqVO) {
        service.update(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除单个聊天消息")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('asc:chat-msg:delete')")
    public CommonResult<Boolean> delete(@RequestParam("id") Long id) {
        service.delete(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得单个聊天消息")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('asc:chat-msg:query')")
    public CommonResult<ChatMsgRespVO> get(@RequestParam("id") Long id) {
        ChatMsgDO chatMsg = service.get(id);
        return success(ChatMsgConvert.INSTANCE.convert(chatMsg));
    }

    @GetMapping("/list")
    @Operation(summary = "获得单个聊天消息列表")
    @Parameter(name = "ids", description = "编号列表", required = true, example = "1024,2048")
    @PreAuthorize("@ss.hasPermission('asc:chat-msg:query')")
    public CommonResult<List<ChatMsgRespVO>> getList(@RequestParam("ids") Collection<Long> ids) {
        List<ChatMsgDO> list = service.getList(ids);
        return success(ChatMsgConvert.INSTANCE.convertList(list));
    }

    @GetMapping("/page")
    @Operation(summary = "获得单个聊天消息分页")
    @PreAuthorize("@ss.hasPermission('asc:chat-msg:query')")
    public CommonResult<PageResult<ChatMsgRespVO>> getPage(@Valid ChatMsgPageReqVO pageVO) {
        PageResult<ChatMsgDO> pageResult = service.getPage(pageVO);
        return success(ChatMsgConvert.INSTANCE.convertPage(pageResult));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出单个聊天消息 Excel")
    @PreAuthorize("@ss.hasPermission('asc:chat-msg:export')")
    @OperateLog(type = EXPORT)
    public void exportExcel(@Valid ChatMsgExportReqVO exportReqVO,
              HttpServletResponse response) throws IOException {
        List<ChatMsgDO> list = service.getList(exportReqVO);
        // 导出 Excel
        List<ChatMsgExcelVO> datas = ChatMsgConvert.INSTANCE.convertList02(list);
        ExcelUtils.write(response, "单个聊天消息.xls", "数据", ChatMsgExcelVO.class, datas);
    }

}
