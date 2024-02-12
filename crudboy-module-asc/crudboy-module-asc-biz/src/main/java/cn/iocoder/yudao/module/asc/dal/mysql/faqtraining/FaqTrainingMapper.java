package cn.iocoder.yudao.module.asc.dal.mysql.faqtraining;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.asc.dal.dataobject.faqtraining.FaqTrainingDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.asc.controller.admin.faqtraining.vo.*;

/**
 * 百问百答训练 Mapper
 *
 * @author 超级管理员
 */
@Mapper
public interface FaqTrainingMapper extends BaseMapperX<FaqTrainingDO> {

    default PageResult<FaqTrainingDO> selectPage(FaqTrainingPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<FaqTrainingDO>()
                .orderByDesc(FaqTrainingDO::getId));
    }

    default List<FaqTrainingDO> selectList(FaqTrainingExportReqVO reqVO) {
        return selectList(new LambdaQueryWrapperX<FaqTrainingDO>()
                .orderByDesc(FaqTrainingDO::getId));
    }

}
