package cn.iocoder.yudao.module.asc.dal.mysql.enduser;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.asc.dal.dataobject.enduser.EndUserDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.asc.controller.admin.enduser.vo.*;

/**
 * 终端用户 Mapper
 *
 * @author 超级管理员
 */
@Mapper
public interface EndUserMapper extends BaseMapperX<EndUserDO> {

    default PageResult<EndUserDO> selectPage(EndUserPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<EndUserDO>()
                .orderByDesc(EndUserDO::getId));
    }

    default List<EndUserDO> selectList(EndUserExportReqVO reqVO) {
        return selectList(new LambdaQueryWrapperX<EndUserDO>()
                .orderByDesc(EndUserDO::getId));
    }

}
