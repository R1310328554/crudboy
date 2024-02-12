package cn.iocoder.yudao.module.asc.service.chatmsg;

import java.util.*;
import javax.validation.*;
import cn.iocoder.yudao.module.asc.controller.admin.chatmsg.vo.*;
import cn.iocoder.yudao.module.asc.dal.dataobject.chatmsg.ChatMsgDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 单个聊天消息 Service 接口
 *
 * @author 超级管理员
 */
public interface ChatMsgService {

    /**
     * 创建单个聊天消息
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long create(@Valid ChatMsgCreateReqVO createReqVO);

    /**
     * 更新单个聊天消息
     *
     * @param updateReqVO 更新信息
     */
    void update(@Valid ChatMsgUpdateReqVO updateReqVO);

    /**
     * 删除单个聊天消息
     *
     * @param id 编号
     */
    void delete(Long id);

    /**
     * 获得单个聊天消息
     *
     * @param id 编号
     * @return 单个聊天消息
     */
    ChatMsgDO get(Long id);

    /**
     * 获得单个聊天消息列表
     *
     * @param ids 编号
     * @return 单个聊天消息列表
     */
    List<ChatMsgDO> getList(Collection<Long> ids);

    /**
     * 获得单个聊天消息分页
     *
     * @param pageReqVO 分页查询
     * @return 单个聊天消息分页
     */
    PageResult<ChatMsgDO> getPage(ChatMsgPageReqVO pageReqVO);

    /**
     * 获得单个聊天消息列表, 用于 Excel 导出
     *
     * @param exportReqVO 查询条件
     * @return 单个聊天消息列表
     */
    List<ChatMsgDO> getList(ChatMsgExportReqVO exportReqVO);

}
