package cn.iocoder.yudao.module.asc.service.chatsession;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import java.util.*;
import cn.iocoder.yudao.module.asc.controller.admin.chatsession.vo.*;
import cn.iocoder.yudao.module.asc.dal.dataobject.chatsession.ChatSessionDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

import cn.iocoder.yudao.module.asc.convert.chatsession.ChatSessionConvert;
import cn.iocoder.yudao.module.asc.dal.mysql.chatsession.ChatSessionMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.asc.enums.ErrorCodeConstants.*;

/**
 * 聊天的会话 Service 实现类
 *
 * @author 超级管理员
 */
@Service
@Validated
public class ChatSessionServiceImpl implements ChatSessionService {

    @Resource
    private ChatSessionMapper mapper;

    @Override
    public Long create(ChatSessionCreateReqVO createReqVO) {
        // 插入
        ChatSessionDO chatSession = ChatSessionConvert.INSTANCE.convert(createReqVO);
        mapper.insert(chatSession);
        // 返回
        return chatSession.getId();
    }

    @Override
    public void update(ChatSessionUpdateReqVO updateReqVO) {
        // 校验存在
        validateExists(updateReqVO.getId());
        // 更新
        ChatSessionDO updateObj = ChatSessionConvert.INSTANCE.convert(updateReqVO);
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
            throw exception(CHAT_SESSION_NOT_EXISTS);
        }
    }

    @Override
    public ChatSessionDO get(Long id) {
        return mapper.selectById(id);
    }

    @Override
    public List<ChatSessionDO> getList(Collection<Long> ids) {
        return mapper.selectBatchIds(ids);
    }

    @Override
    public PageResult<ChatSessionDO> getPage(ChatSessionPageReqVO pageReqVO) {
        return mapper.selectPage(pageReqVO);
    }

    @Override
    public List<ChatSessionDO> getList(ChatSessionExportReqVO exportReqVO) {
        return mapper.selectList(exportReqVO);
    }

}
