package cn.iocoder.yudao.module.asc.convert.faqtraining;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import cn.iocoder.yudao.module.asc.controller.admin.faqtraining.vo.*;
import cn.iocoder.yudao.module.asc.dal.dataobject.faqtraining.FaqTrainingDO;

/**
 * 百问百答训练 Convert
 *
 * @author 超级管理员
 */
@Mapper
public interface FaqTrainingConvert {

    FaqTrainingConvert INSTANCE = Mappers.getMapper(FaqTrainingConvert.class);

    FaqTrainingDO convert(FaqTrainingCreateReqVO bean);

    FaqTrainingDO convert(FaqTrainingUpdateReqVO bean);

    FaqTrainingRespVO convert(FaqTrainingDO bean);

    List<FaqTrainingRespVO> convertList(List<FaqTrainingDO> list);

    PageResult<FaqTrainingRespVO> convertPage(PageResult<FaqTrainingDO> page);

    List<FaqTrainingExcelVO> convertList02(List<FaqTrainingDO> list);

}
