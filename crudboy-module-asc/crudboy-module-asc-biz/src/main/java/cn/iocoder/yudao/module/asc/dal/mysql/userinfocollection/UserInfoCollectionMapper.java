package cn.iocoder.yudao.module.asc.dal.mysql.userinfocollection;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.asc.dal.dataobject.userinfocollection.UserInfoCollectionDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.asc.controller.admin.userinfocollection.vo.*;

/**
 * 客户信息收集 Mapper
 *
 * @author 超级管理员
 */
@Mapper
public interface UserInfoCollectionMapper extends BaseMapperX<UserInfoCollectionDO> {

    default PageResult<UserInfoCollectionDO> selectPage(UserInfoCollectionPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<UserInfoCollectionDO>()
                .orderByDesc(UserInfoCollectionDO::getId));
    }

    default List<UserInfoCollectionDO> selectList(UserInfoCollectionExportReqVO reqVO) {
        return selectList(new LambdaQueryWrapperX<UserInfoCollectionDO>()
                .orderByDesc(UserInfoCollectionDO::getId));
    }

}
