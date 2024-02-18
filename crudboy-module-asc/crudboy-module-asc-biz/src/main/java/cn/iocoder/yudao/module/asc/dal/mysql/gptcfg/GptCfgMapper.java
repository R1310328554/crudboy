package cn.iocoder.yudao.module.asc.dal.mysql.gptcfg;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.asc.dal.dataobject.gptcfg.GptCfgDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.asc.controller.admin.gptcfg.vo.*;

/**
 * GPT 专用的基础的配置 Mapper
 *
 * @author 超级管理员
 */
@Mapper
public interface GptCfgMapper extends BaseMapperX<GptCfgDO> {

    default PageResult<GptCfgDO> selectPage(GptCfgPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<GptCfgDO>()
                .orderByDesc(GptCfgDO::getId));
    }

    default List<GptCfgDO> selectList(GptCfgExportReqVO reqVO) {
        return selectList(new LambdaQueryWrapperX<GptCfgDO>()
                .orderByDesc(GptCfgDO::getId));
    }

}
