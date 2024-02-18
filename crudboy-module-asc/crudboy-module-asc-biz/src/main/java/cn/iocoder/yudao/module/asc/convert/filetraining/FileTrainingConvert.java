package cn.iocoder.yudao.module.asc.convert.filetraining;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import cn.iocoder.yudao.module.asc.controller.admin.filetraining.vo.*;
import cn.iocoder.yudao.module.asc.dal.dataobject.filetraining.FileTrainingDO;

/**
 * 文档训练 Convert
 *
 * @author 超级管理员
 */
@Mapper
public interface FileTrainingConvert {

    FileTrainingConvert INSTANCE = Mappers.getMapper(FileTrainingConvert.class);

    FileTrainingDO convert(FileTrainingCreateReqVO bean);

    FileTrainingDO convert(FileTrainingUpdateReqVO bean);

    FileTrainingRespVO convert(FileTrainingDO bean);

    List<FileTrainingRespVO> convertList(List<FileTrainingDO> list);

    PageResult<FileTrainingRespVO> convertPage(PageResult<FileTrainingDO> page);

    List<FileTrainingExcelVO> convertList02(List<FileTrainingDO> list);

}
