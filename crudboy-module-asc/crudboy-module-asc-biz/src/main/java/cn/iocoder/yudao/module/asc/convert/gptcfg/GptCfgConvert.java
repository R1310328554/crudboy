package cn.iocoder.yudao.module.asc.convert.gptcfg;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import cn.iocoder.yudao.module.asc.controller.admin.gptcfg.vo.*;
import cn.iocoder.yudao.module.asc.dal.dataobject.gptcfg.GptCfgDO;

/**
 * GPT 专用的基础的配置 Convert
 *
 * @author 超级管理员
 */
@Mapper
public interface GptCfgConvert {

    GptCfgConvert INSTANCE = Mappers.getMapper(GptCfgConvert.class);

    GptCfgDO convert(GptCfgCreateReqVO bean);

    GptCfgDO convert(GptCfgUpdateReqVO bean);

    GptCfgRespVO convert(GptCfgDO bean);

    List<GptCfgRespVO> convertList(List<GptCfgDO> list);

    PageResult<GptCfgRespVO> convertPage(PageResult<GptCfgDO> page);

    List<GptCfgExcelVO> convertList02(List<GptCfgDO> list);

}
