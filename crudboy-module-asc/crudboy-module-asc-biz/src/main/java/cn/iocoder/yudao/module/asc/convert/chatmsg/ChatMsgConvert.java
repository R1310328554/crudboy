package cn.iocoder.yudao.module.asc.convert.chatmsg;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import cn.iocoder.yudao.module.asc.controller.admin.chatmsg.vo.*;
import cn.iocoder.yudao.module.asc.dal.dataobject.chatmsg.ChatMsgDO;

/**
 * 单个聊天消息 Convert
 *
 * @author 超级管理员
 */
@Mapper
public interface ChatMsgConvert {

    ChatMsgConvert INSTANCE = Mappers.getMapper(ChatMsgConvert.class);

    ChatMsgDO convert(ChatMsgCreateReqVO bean);

    ChatMsgDO convert(ChatMsgUpdateReqVO bean);

    ChatMsgRespVO convert(ChatMsgDO bean);

    List<ChatMsgRespVO> convertList(List<ChatMsgDO> list);

    PageResult<ChatMsgRespVO> convertPage(PageResult<ChatMsgDO> page);

    List<ChatMsgExcelVO> convertList02(List<ChatMsgDO> list);

}
