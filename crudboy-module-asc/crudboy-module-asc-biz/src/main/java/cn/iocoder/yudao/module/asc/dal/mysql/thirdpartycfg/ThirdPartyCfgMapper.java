package cn.iocoder.yudao.module.asc.dal.mysql.thirdpartycfg;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.asc.dal.dataobject.thirdpartycfg.ThirdPartyCfgDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.asc.controller.admin.thirdpartycfg.vo.*;

/**
 * 终端平台即第三方平台配置 Mapper
 *
 * @author 超级管理员
 */
@Mapper
public interface ThirdPartyCfgMapper extends BaseMapperX<ThirdPartyCfgDO> {

    default PageResult<ThirdPartyCfgDO> selectPage(ThirdPartyCfgPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<ThirdPartyCfgDO>()
                .likeIfPresent(ThirdPartyCfgDO::getName, reqVO.getName())
                .orderByDesc(ThirdPartyCfgDO::getId));
    }

    default List<ThirdPartyCfgDO> selectList(ThirdPartyCfgExportReqVO reqVO) {
        return selectList(new LambdaQueryWrapperX<ThirdPartyCfgDO>()
                .likeIfPresent(ThirdPartyCfgDO::getName, reqVO.getName())
                .orderByDesc(ThirdPartyCfgDO::getId));
    }

}
