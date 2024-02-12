package cn.iocoder.yudao.module.asc.convert.endusercfg;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import cn.iocoder.yudao.module.asc.controller.admin.endusercfg.vo.*;
import cn.iocoder.yudao.module.asc.dal.dataobject.endusercfg.EndUserCfgDO;

/**
 * 终端用户配置 Convert
 *
 * @author 超级管理员
 */
@Mapper
public interface EndUserCfgConvert {

    EndUserCfgConvert INSTANCE = Mappers.getMapper(EndUserCfgConvert.class);

    EndUserCfgDO convert(EndUserCfgCreateReqVO bean);

    EndUserCfgDO convert(EndUserCfgUpdateReqVO bean);

    EndUserCfgRespVO convert(EndUserCfgDO bean);

    List<EndUserCfgRespVO> convertList(List<EndUserCfgDO> list);

    PageResult<EndUserCfgRespVO> convertPage(PageResult<EndUserCfgDO> page);

    List<EndUserCfgExcelVO> convertList02(List<EndUserCfgDO> list);

}
