package cn.iocoder.yudao.module.asc.dal.mysql.whatsappcfg;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.asc.dal.dataobject.whatsappcfg.WhatsappCfgDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.asc.controller.admin.whatsappcfg.vo.*;

/**
 * WhatsApp 平台配置 Mapper
 *
 * @author 超级管理员
 */
@Mapper
public interface WhatsappCfgMapper extends BaseMapperX<WhatsappCfgDO> {

    default PageResult<WhatsappCfgDO> selectPage(WhatsappCfgPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<WhatsappCfgDO>()
                .likeIfPresent(WhatsappCfgDO::getName, reqVO.getName())
                .orderByDesc(WhatsappCfgDO::getId));
    }

    default List<WhatsappCfgDO> selectList(WhatsappCfgExportReqVO reqVO) {
        return selectList(new LambdaQueryWrapperX<WhatsappCfgDO>()
                .likeIfPresent(WhatsappCfgDO::getName, reqVO.getName())
                .orderByDesc(WhatsappCfgDO::getId));
    }

}
