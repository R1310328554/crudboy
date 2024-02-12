package cn.iocoder.yudao.module.asc.dal.mysql.chatmsg;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.asc.dal.dataobject.chatmsg.ChatMsgDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.asc.controller.admin.chatmsg.vo.*;

/**
 * 单个聊天消息 Mapper
 *
 * @author 超级管理员
 */
@Mapper
public interface ChatMsgMapper extends BaseMapperX<ChatMsgDO> {

    default PageResult<ChatMsgDO> selectPage(ChatMsgPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<ChatMsgDO>()
                .eqIfPresent(ChatMsgDO::getStatus, reqVO.getStatus())
                .orderByDesc(ChatMsgDO::getId));
    }

    default List<ChatMsgDO> selectList(ChatMsgExportReqVO reqVO) {
        return selectList(new LambdaQueryWrapperX<ChatMsgDO>()
                .eqIfPresent(ChatMsgDO::getStatus, reqVO.getStatus())
                .orderByDesc(ChatMsgDO::getId));
    }

}
