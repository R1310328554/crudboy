package cn.iocoder.yudao.module.asc.convert.chatsession;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import cn.iocoder.yudao.module.asc.controller.admin.chatsession.vo.*;
import cn.iocoder.yudao.module.asc.dal.dataobject.chatsession.ChatSessionDO;

/**
 * 聊天的会话 Convert
 *
 * @author 超级管理员
 */
@Mapper
public interface ChatSessionConvert {

    ChatSessionConvert INSTANCE = Mappers.getMapper(ChatSessionConvert.class);

    ChatSessionDO convert(ChatSessionCreateReqVO bean);

    ChatSessionDO convert(ChatSessionUpdateReqVO bean);

    ChatSessionRespVO convert(ChatSessionDO bean);

    List<ChatSessionRespVO> convertList(List<ChatSessionDO> list);

    PageResult<ChatSessionRespVO> convertPage(PageResult<ChatSessionDO> page);

    List<ChatSessionExcelVO> convertList02(List<ChatSessionDO> list);

}
