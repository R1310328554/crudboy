package cn.iocoder.yudao.module.asc.convert.whatsappcfg;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import cn.iocoder.yudao.module.asc.controller.admin.whatsappcfg.vo.*;
import cn.iocoder.yudao.module.asc.dal.dataobject.whatsappcfg.WhatsappCfgDO;

/**
 * WhatsApp 平台配置 Convert
 *
 * @author 超级管理员
 */
@Mapper
public interface WhatsappCfgConvert {

    WhatsappCfgConvert INSTANCE = Mappers.getMapper(WhatsappCfgConvert.class);

    WhatsappCfgDO convert(WhatsappCfgCreateReqVO bean);

    WhatsappCfgDO convert(WhatsappCfgUpdateReqVO bean);

    WhatsappCfgRespVO convert(WhatsappCfgDO bean);

    List<WhatsappCfgRespVO> convertList(List<WhatsappCfgDO> list);

    PageResult<WhatsappCfgRespVO> convertPage(PageResult<WhatsappCfgDO> page);

    List<WhatsappCfgExcelVO> convertList02(List<WhatsappCfgDO> list);

}
