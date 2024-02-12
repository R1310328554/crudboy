package cn.iocoder.yudao.module.asc.dal.mysql.websitetraining;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.asc.dal.dataobject.websitetraining.WebsiteTrainingDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.asc.controller.admin.websitetraining.vo.*;

/**
 * 网站内容爬取训练 Mapper
 *
 * @author 超级管理员
 */
@Mapper
public interface WebsiteTrainingMapper extends BaseMapperX<WebsiteTrainingDO> {

    default PageResult<WebsiteTrainingDO> selectPage(WebsiteTrainingPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<WebsiteTrainingDO>()
                .orderByDesc(WebsiteTrainingDO::getId));
    }

    default List<WebsiteTrainingDO> selectList(WebsiteTrainingExportReqVO reqVO) {
        return selectList(new LambdaQueryWrapperX<WebsiteTrainingDO>()
                .orderByDesc(WebsiteTrainingDO::getId));
    }

}
