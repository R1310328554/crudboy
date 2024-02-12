package cn.iocoder.yudao.module.asc.dal.mysql.botbasecfg;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.asc.dal.dataobject.botbasecfg.BotBaseCfgDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.asc.controller.admin.botbasecfg.vo.*;

/**
 * 机器人的基础配置 Mapper
 *
 * @author 超级管理员
 */
@Mapper
public interface BotBaseCfgMapper extends BaseMapperX<BotBaseCfgDO> {

    default PageResult<BotBaseCfgDO> selectPage(BotBaseCfgPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<BotBaseCfgDO>()
                .eqIfPresent(BotBaseCfgDO::getCode, reqVO.getCode())
                .likeIfPresent(BotBaseCfgDO::getName, reqVO.getName())
                .eqIfPresent(BotBaseCfgDO::getStatus, reqVO.getStatus())
                .orderByDesc(BotBaseCfgDO::getId));
    }

    default List<BotBaseCfgDO> selectList(BotBaseCfgExportReqVO reqVO) {
        return selectList(new LambdaQueryWrapperX<BotBaseCfgDO>()
                .eqIfPresent(BotBaseCfgDO::getCode, reqVO.getCode())
                .likeIfPresent(BotBaseCfgDO::getName, reqVO.getName())
                .eqIfPresent(BotBaseCfgDO::getStatus, reqVO.getStatus())
                .orderByDesc(BotBaseCfgDO::getId));
    }

}
