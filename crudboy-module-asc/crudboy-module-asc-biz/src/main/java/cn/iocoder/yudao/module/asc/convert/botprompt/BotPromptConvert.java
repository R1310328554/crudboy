package cn.iocoder.yudao.module.asc.convert.botprompt;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import cn.iocoder.yudao.module.asc.controller.admin.botprompt.vo.*;
import cn.iocoder.yudao.module.asc.dal.dataobject.botprompt.BotPromptDO;

/**
 * 预设提示词 Convert
 *
 * @author 超级管理员
 */
@Mapper
public interface BotPromptConvert {

    BotPromptConvert INSTANCE = Mappers.getMapper(BotPromptConvert.class);

    BotPromptDO convert(BotPromptCreateReqVO bean);

    BotPromptDO convert(BotPromptUpdateReqVO bean);

    BotPromptRespVO convert(BotPromptDO bean);

    List<BotPromptRespVO> convertList(List<BotPromptDO> list);

    PageResult<BotPromptRespVO> convertPage(PageResult<BotPromptDO> page);

    List<BotPromptExcelVO> convertList02(List<BotPromptDO> list);

}
