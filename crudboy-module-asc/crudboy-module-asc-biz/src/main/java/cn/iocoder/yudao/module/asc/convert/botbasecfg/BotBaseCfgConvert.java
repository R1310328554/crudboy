package cn.iocoder.yudao.module.asc.convert.botbasecfg;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;

import cn.iocoder.yudao.module.asc.controller.admin.report.vo.ReportCreateReqVO;
import cn.iocoder.yudao.module.asc.controller.admin.report.vo.ReportUpdateReqVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import cn.iocoder.yudao.module.asc.controller.admin.botbasecfg.vo.*;
import cn.iocoder.yudao.module.asc.dal.dataobject.botbasecfg.BotBaseCfgDO;

/**
 * 机器人的基础配置 Convert
 *
 * @author 超级管理员
 */
@Mapper
public interface BotBaseCfgConvert {

    BotBaseCfgConvert INSTANCE = Mappers.getMapper(BotBaseCfgConvert.class);

    BotBaseCfgDO convert(BotBaseCfgCreateReqVO bean);

    BotBaseCfgDO convert(BotBaseCfgUpdateReqVO bean);

    BotBaseCfgRespVO convert(BotBaseCfgDO bean);

    List<BotBaseCfgRespVO> convertList(List<BotBaseCfgDO> list);

    PageResult<BotBaseCfgRespVO> convertPage(PageResult<BotBaseCfgDO> page);

    List<BotBaseCfgExcelVO> convertList02(List<BotBaseCfgDO> list);

    BotBaseCfgDO convert(ReportCreateReqVO createReqVO);

    BotBaseCfgDO convert12(ReportUpdateReqVO updateReqVO);
}
