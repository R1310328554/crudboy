package cn.iocoder.yudao.module.asc.dal.mysql.filetraining;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.asc.dal.dataobject.filetraining.FileTrainingDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.asc.controller.admin.filetraining.vo.*;

/**
 * 文档训练 Mapper
 *
 * @author 超级管理员
 */
@Mapper
public interface FileTrainingMapper extends BaseMapperX<FileTrainingDO> {

    default PageResult<FileTrainingDO> selectPage(FileTrainingPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<FileTrainingDO>()
                .orderByDesc(FileTrainingDO::getId));
    }

    default List<FileTrainingDO> selectList(FileTrainingExportReqVO reqVO) {
        return selectList(new LambdaQueryWrapperX<FileTrainingDO>()
                .orderByDesc(FileTrainingDO::getId));
    }

}
