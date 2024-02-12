package cn.iocoder.yudao.module.asc.dal.mysql.filematchcfg;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.asc.dal.dataobject.filematchcfg.FileMatchCfgDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.asc.controller.admin.filematchcfg.vo.*;

/**
 * 关键信息和文件链接匹配 Mapper
 *
 * @author 超级管理员
 */
@Mapper
public interface FileMatchCfgMapper extends BaseMapperX<FileMatchCfgDO> {

    default PageResult<FileMatchCfgDO> selectPage(FileMatchCfgPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<FileMatchCfgDO>()
                .likeIfPresent(FileMatchCfgDO::getName, reqVO.getName())
                .orderByDesc(FileMatchCfgDO::getId));
    }

    default List<FileMatchCfgDO> selectList(FileMatchCfgExportReqVO reqVO) {
        return selectList(new LambdaQueryWrapperX<FileMatchCfgDO>()
                .likeIfPresent(FileMatchCfgDO::getName, reqVO.getName())
                .orderByDesc(FileMatchCfgDO::getId));
    }

}
