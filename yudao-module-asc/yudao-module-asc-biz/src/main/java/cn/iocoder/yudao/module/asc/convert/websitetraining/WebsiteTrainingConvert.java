package cn.iocoder.yudao.module.asc.convert.websitetraining;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import cn.iocoder.yudao.module.asc.controller.admin.websitetraining.vo.*;
import cn.iocoder.yudao.module.asc.dal.dataobject.websitetraining.WebsiteTrainingDO;

/**
 * 网站内容爬取训练 Convert
 *
 * @author 超级管理员
 */
@Mapper
public interface WebsiteTrainingConvert {

    WebsiteTrainingConvert INSTANCE = Mappers.getMapper(WebsiteTrainingConvert.class);

    WebsiteTrainingDO convert(WebsiteTrainingCreateReqVO bean);

    WebsiteTrainingDO convert(WebsiteTrainingUpdateReqVO bean);

    WebsiteTrainingRespVO convert(WebsiteTrainingDO bean);

    List<WebsiteTrainingRespVO> convertList(List<WebsiteTrainingDO> list);

    PageResult<WebsiteTrainingRespVO> convertPage(PageResult<WebsiteTrainingDO> page);

    List<WebsiteTrainingExcelVO> convertList02(List<WebsiteTrainingDO> list);

}
