package cn.iocoder.yudao.module.asc.convert.consumedtokens;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import cn.iocoder.yudao.module.asc.controller.admin.consumedtokens.vo.*;
import cn.iocoder.yudao.module.asc.dal.dataobject.consumedtokens.ConsumedTokensDO;

/**
 * 租户的token消耗情况 Convert
 *
 * @author 超级管理员
 */
@Mapper
public interface ConsumedTokensConvert {

    ConsumedTokensConvert INSTANCE = Mappers.getMapper(ConsumedTokensConvert.class);

    ConsumedTokensDO convert(ConsumedTokensCreateReqVO bean);

    ConsumedTokensDO convert(ConsumedTokensUpdateReqVO bean);

    ConsumedTokensRespVO convert(ConsumedTokensDO bean);

    List<ConsumedTokensRespVO> convertList(List<ConsumedTokensDO> list);

    PageResult<ConsumedTokensRespVO> convertPage(PageResult<ConsumedTokensDO> page);

    List<ConsumedTokensExcelVO> convertList02(List<ConsumedTokensDO> list);

}
