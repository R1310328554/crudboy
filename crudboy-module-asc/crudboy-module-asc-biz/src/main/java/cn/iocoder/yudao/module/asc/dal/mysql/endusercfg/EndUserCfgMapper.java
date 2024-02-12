package cn.iocoder.yudao.module.asc.dal.mysql.endusercfg;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.asc.dal.dataobject.endusercfg.EndUserCfgDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.asc.controller.admin.endusercfg.vo.*;

/**
 * 终端用户配置 Mapper
 *
 * @author 超级管理员
 */
@Mapper
public interface EndUserCfgMapper extends BaseMapperX<EndUserCfgDO> {

    default PageResult<EndUserCfgDO> selectPage(EndUserCfgPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<EndUserCfgDO>()
                .likeIfPresent(EndUserCfgDO::getName, reqVO.getName())
                .orderByDesc(EndUserCfgDO::getId));
    }

    default List<EndUserCfgDO> selectList(EndUserCfgExportReqVO reqVO) {
        return selectList(new LambdaQueryWrapperX<EndUserCfgDO>()
                .likeIfPresent(EndUserCfgDO::getName, reqVO.getName())
                .orderByDesc(EndUserCfgDO::getId));
    }

}
