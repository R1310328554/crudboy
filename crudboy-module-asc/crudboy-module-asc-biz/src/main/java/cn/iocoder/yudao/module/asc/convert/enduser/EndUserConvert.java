package cn.iocoder.yudao.module.asc.convert.enduser;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import cn.iocoder.yudao.module.asc.controller.admin.enduser.vo.*;
import cn.iocoder.yudao.module.asc.dal.dataobject.enduser.EndUserDO;

/**
 * 终端用户 Convert
 *
 * @author 超级管理员
 */
@Mapper
public interface EndUserConvert {

    EndUserConvert INSTANCE = Mappers.getMapper(EndUserConvert.class);

    EndUserDO convert(EndUserCreateReqVO bean);

    EndUserDO convert(EndUserUpdateReqVO bean);

    EndUserRespVO convert(EndUserDO bean);

    List<EndUserRespVO> convertList(List<EndUserDO> list);

    PageResult<EndUserRespVO> convertPage(PageResult<EndUserDO> page);

    List<EndUserExcelVO> convertList02(List<EndUserDO> list);

}
