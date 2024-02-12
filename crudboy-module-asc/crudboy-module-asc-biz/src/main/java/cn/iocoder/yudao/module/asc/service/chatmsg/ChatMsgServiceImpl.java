package cn.iocoder.yudao.module.asc.service.chatmsg;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import java.util.*;
import cn.iocoder.yudao.module.asc.controller.admin.chatmsg.vo.*;
import cn.iocoder.yudao.module.asc.dal.dataobject.chatmsg.ChatMsgDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

import cn.iocoder.yudao.module.asc.convert.chatmsg.ChatMsgConvert;
import cn.iocoder.yudao.module.asc.dal.mysql.chatmsg.ChatMsgMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.asc.enums.ErrorCodeConstants.*;

/**
 * 单个聊天消息 Service 实现类
 *
 * @author 超级管理员
 */
@Service
@Validated
public class ChatMsgServiceImpl implements ChatMsgService {

    @Resource
    private ChatMsgMapper mapper;

    @Override
    public Long create(ChatMsgCreateReqVO createReqVO) {
        // 插入
        ChatMsgDO chatMsg = ChatMsgConvert.INSTANCE.convert(createReqVO);
        mapper.insert(chatMsg);
        // 返回
        return chatMsg.getId();
    }

    @Override
    public void update(ChatMsgUpdateReqVO updateReqVO) {
        // 校验存在
        validateExists(updateReqVO.getId());
        // 更新
        ChatMsgDO updateObj = ChatMsgConvert.INSTANCE.convert(updateReqVO);
        mapper.updateById(updateObj);
    }

    @Override
    public void delete(Long id) {
        // 校验存在
        validateExists(id);
        // 删除
        mapper.deleteById(id);
    }

    private void validateExists(Long id) {
        if (mapper.selectById(id) == null) {
            throw exception(CHAT_MSG_NOT_EXISTS);
        }
    }

    @Override
    public ChatMsgDO get(Long id) {
        return mapper.selectById(id);
    }

    @Override
    public List<ChatMsgDO> getList(Collection<Long> ids) {
        return mapper.selectBatchIds(ids);
    }

    @Override
    public PageResult<ChatMsgDO> getPage(ChatMsgPageReqVO pageReqVO) {
        return mapper.selectPage(pageReqVO);
    }

    @Override
    public List<ChatMsgDO> getList(ChatMsgExportReqVO exportReqVO) {
        return mapper.selectList(exportReqVO);
    }

}
