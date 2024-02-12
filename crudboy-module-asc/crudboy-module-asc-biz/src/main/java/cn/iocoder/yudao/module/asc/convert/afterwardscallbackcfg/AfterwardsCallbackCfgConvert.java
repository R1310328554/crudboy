package cn.iocoder.yudao.module.asc.convert.afterwardscallbackcfg;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import cn.iocoder.yudao.module.asc.controller.admin.afterwardscallbackcfg.vo.*;
import cn.iocoder.yudao.module.asc.dal.dataobject.afterwardscallbackcfg.AfterwardsCallbackCfgDO;

/**
 * 后续主动沟通的询问方案 Convert
 *
 * @author 超级管理员
 */
@Mapper
public interface AfterwardsCallbackCfgConvert {

    AfterwardsCallbackCfgConvert INSTANCE = Mappers.getMapper(AfterwardsCallbackCfgConvert.class);

    AfterwardsCallbackCfgDO convert(AfterwardsCallbackCfgCreateReqVO bean);

    AfterwardsCallbackCfgDO convert(AfterwardsCallbackCfgUpdateReqVO bean);

    AfterwardsCallbackCfgRespVO convert(AfterwardsCallbackCfgDO bean);

    List<AfterwardsCallbackCfgRespVO> convertList(List<AfterwardsCallbackCfgDO> list);

    PageResult<AfterwardsCallbackCfgRespVO> convertPage(PageResult<AfterwardsCallbackCfgDO> page);

    List<AfterwardsCallbackCfgExcelVO> convertList02(List<AfterwardsCallbackCfgDO> list);

}
