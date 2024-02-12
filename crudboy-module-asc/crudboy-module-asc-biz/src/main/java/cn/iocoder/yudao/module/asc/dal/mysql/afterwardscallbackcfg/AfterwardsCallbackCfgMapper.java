package cn.iocoder.yudao.module.asc.dal.mysql.afterwardscallbackcfg;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.asc.dal.dataobject.afterwardscallbackcfg.AfterwardsCallbackCfgDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.asc.controller.admin.afterwardscallbackcfg.vo.*;

/**
 * 后续主动沟通的询问方案 Mapper
 *
 * @author 超级管理员
 */
@Mapper
public interface AfterwardsCallbackCfgMapper extends BaseMapperX<AfterwardsCallbackCfgDO> {

    default PageResult<AfterwardsCallbackCfgDO> selectPage(AfterwardsCallbackCfgPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<AfterwardsCallbackCfgDO>()
                .likeIfPresent(AfterwardsCallbackCfgDO::getName, reqVO.getName())
                .orderByDesc(AfterwardsCallbackCfgDO::getId));
    }

    default List<AfterwardsCallbackCfgDO> selectList(AfterwardsCallbackCfgExportReqVO reqVO) {
        return selectList(new LambdaQueryWrapperX<AfterwardsCallbackCfgDO>()
                .likeIfPresent(AfterwardsCallbackCfgDO::getName, reqVO.getName())
                .orderByDesc(AfterwardsCallbackCfgDO::getId));
    }

}
