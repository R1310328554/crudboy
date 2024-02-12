package cn.iocoder.yudao.module.asc.convert.filematchcfg;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import cn.iocoder.yudao.module.asc.controller.admin.filematchcfg.vo.*;
import cn.iocoder.yudao.module.asc.dal.dataobject.filematchcfg.FileMatchCfgDO;

/**
 * 关键信息和文件链接匹配 Convert
 *
 * @author 超级管理员
 */
@Mapper
public interface FileMatchCfgConvert {

    FileMatchCfgConvert INSTANCE = Mappers.getMapper(FileMatchCfgConvert.class);

    FileMatchCfgDO convert(FileMatchCfgCreateReqVO bean);

    FileMatchCfgDO convert(FileMatchCfgUpdateReqVO bean);

    FileMatchCfgRespVO convert(FileMatchCfgDO bean);

    List<FileMatchCfgRespVO> convertList(List<FileMatchCfgDO> list);

    PageResult<FileMatchCfgRespVO> convertPage(PageResult<FileMatchCfgDO> page);

    List<FileMatchCfgExcelVO> convertList02(List<FileMatchCfgDO> list);

}
