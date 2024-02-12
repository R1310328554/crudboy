package cn.iocoder.yudao.module.asc.dal.mysql.botprompt;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.asc.dal.dataobject.botprompt.BotPromptDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.asc.controller.admin.botprompt.vo.*;

/**
 * 预设提示词 Mapper
 *
 * @author 超级管理员
 */
@Mapper
public interface BotPromptMapper extends BaseMapperX<BotPromptDO> {

    default PageResult<BotPromptDO> selectPage(BotPromptPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<BotPromptDO>()
                .orderByDesc(BotPromptDO::getId));
    }

    default List<BotPromptDO> selectList(BotPromptExportReqVO reqVO) {
        return selectList(new LambdaQueryWrapperX<BotPromptDO>()
                .orderByDesc(BotPromptDO::getId));
    }

}
