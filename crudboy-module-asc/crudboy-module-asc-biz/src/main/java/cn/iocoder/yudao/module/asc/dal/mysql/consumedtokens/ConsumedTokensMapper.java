package cn.iocoder.yudao.module.asc.dal.mysql.consumedtokens;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.asc.dal.dataobject.consumedtokens.ConsumedTokensDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.asc.controller.admin.consumedtokens.vo.*;

/**
 * 租户的token消耗情况 Mapper
 *
 * @author 超级管理员
 */
@Mapper
public interface ConsumedTokensMapper extends BaseMapperX<ConsumedTokensDO> {

    default PageResult<ConsumedTokensDO> selectPage(ConsumedTokensPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<ConsumedTokensDO>()
                .orderByDesc(ConsumedTokensDO::getId));
    }

    default List<ConsumedTokensDO> selectList(ConsumedTokensExportReqVO reqVO) {
        return selectList(new LambdaQueryWrapperX<ConsumedTokensDO>()
                .orderByDesc(ConsumedTokensDO::getId));
    }

}
