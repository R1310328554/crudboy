package cn.iocoder.yudao.module.asc.convert.thirdpartycfg;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import cn.iocoder.yudao.module.asc.controller.admin.thirdpartycfg.vo.*;
import cn.iocoder.yudao.module.asc.dal.dataobject.thirdpartycfg.ThirdPartyCfgDO;

/**
 * 终端平台即第三方平台配置 Convert
 *
 * @author 超级管理员
 */
@Mapper
public interface ThirdPartyCfgConvert {

    ThirdPartyCfgConvert INSTANCE = Mappers.getMapper(ThirdPartyCfgConvert.class);

    ThirdPartyCfgDO convert(ThirdPartyCfgCreateReqVO bean);

    ThirdPartyCfgDO convert(ThirdPartyCfgUpdateReqVO bean);

    ThirdPartyCfgRespVO convert(ThirdPartyCfgDO bean);

    List<ThirdPartyCfgRespVO> convertList(List<ThirdPartyCfgDO> list);

    PageResult<ThirdPartyCfgRespVO> convertPage(PageResult<ThirdPartyCfgDO> page);

    List<ThirdPartyCfgExcelVO> convertList02(List<ThirdPartyCfgDO> list);

}
