package cn.iocoder.yudao.module.asc.dal.mysql.chatsession;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.asc.dal.dataobject.chatsession.ChatSessionDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.asc.controller.admin.chatsession.vo.*;

/**
 * 聊天的会话 Mapper
 *
 * @author 超级管理员
 */
@Mapper
public interface ChatSessionMapper extends BaseMapperX<ChatSessionDO> {

    default PageResult<ChatSessionDO> selectPage(ChatSessionPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<ChatSessionDO>()
                .orderByDesc(ChatSessionDO::getId));
    }

    default List<ChatSessionDO> selectList(ChatSessionExportReqVO reqVO) {
        return selectList(new LambdaQueryWrapperX<ChatSessionDO>()
                .orderByDesc(ChatSessionDO::getId));
    }

}
