package cn.iocoder.yudao.module.asc.service.chatsession;

import java.util.*;
import javax.validation.*;
import cn.iocoder.yudao.module.asc.controller.admin.chatsession.vo.*;
import cn.iocoder.yudao.module.asc.dal.dataobject.chatsession.ChatSessionDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 聊天的会话 Service 接口
 *
 * @author 超级管理员
 */
public interface ChatSessionService {

    /**
     * 创建聊天的会话
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long create(@Valid ChatSessionCreateReqVO createReqVO);

    /**
     * 更新聊天的会话
     *
     * @param updateReqVO 更新信息
     */
    void update(@Valid ChatSessionUpdateReqVO updateReqVO);

    /**
     * 删除聊天的会话
     *
     * @param id 编号
     */
    void delete(Long id);

    /**
     * 获得聊天的会话
     *
     * @param id 编号
     * @return 聊天的会话
     */
    ChatSessionDO get(Long id);

    /**
     * 获得聊天的会话列表
     *
     * @param ids 编号
     * @return 聊天的会话列表
     */
    List<ChatSessionDO> getList(Collection<Long> ids);

    /**
     * 获得聊天的会话分页
     *
     * @param pageReqVO 分页查询
     * @return 聊天的会话分页
     */
    PageResult<ChatSessionDO> getPage(ChatSessionPageReqVO pageReqVO);

    /**
     * 获得聊天的会话列表, 用于 Excel 导出
     *
     * @param exportReqVO 查询条件
     * @return 聊天的会话列表
     */
    List<ChatSessionDO> getList(ChatSessionExportReqVO exportReqVO);

}
