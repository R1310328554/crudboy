package cn.iocoder.yudao.module.asc.convert.userinfocollection;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import cn.iocoder.yudao.module.asc.controller.admin.userinfocollection.vo.*;
import cn.iocoder.yudao.module.asc.dal.dataobject.userinfocollection.UserInfoCollectionDO;

/**
 * 客户信息收集 Convert
 *
 * @author 超级管理员
 */
@Mapper
public interface UserInfoCollectionConvert {

    UserInfoCollectionConvert INSTANCE = Mappers.getMapper(UserInfoCollectionConvert.class);

    UserInfoCollectionDO convert(UserInfoCollectionCreateReqVO bean);

    UserInfoCollectionDO convert(UserInfoCollectionUpdateReqVO bean);

    UserInfoCollectionRespVO convert(UserInfoCollectionDO bean);

    List<UserInfoCollectionRespVO> convertList(List<UserInfoCollectionDO> list);

    PageResult<UserInfoCollectionRespVO> convertPage(PageResult<UserInfoCollectionDO> page);

    List<UserInfoCollectionExcelVO> convertList02(List<UserInfoCollectionDO> list);

}
