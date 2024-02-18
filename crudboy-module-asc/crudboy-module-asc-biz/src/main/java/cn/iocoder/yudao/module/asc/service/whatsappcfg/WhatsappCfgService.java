package cn.iocoder.yudao.module.asc.service.whatsappcfg;

import java.util.*;
import javax.validation.*;
import cn.iocoder.yudao.module.asc.controller.admin.whatsappcfg.vo.*;
import cn.iocoder.yudao.module.asc.dal.dataobject.whatsappcfg.WhatsappCfgDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * WhatsApp 平台配置 Service 接口
 *
 * @author 超级管理员
 */
public interface WhatsappCfgService {

    /**
     * 创建WhatsApp 平台配置
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long create(@Valid WhatsappCfgCreateReqVO createReqVO);

    /**
     * 更新WhatsApp 平台配置
     *
     * @param updateReqVO 更新信息
     */
    void update(@Valid WhatsappCfgUpdateReqVO updateReqVO);

    /**
     * 删除WhatsApp 平台配置
     *
     * @param id 编号
     */
    void delete(Long id);

    /**
     * 获得WhatsApp 平台配置
     *
     * @param id 编号
     * @return WhatsApp 平台配置
     */
    WhatsappCfgDO get(Long id);

    /**
     * 获得WhatsApp 平台配置列表
     *
     * @param ids 编号
     * @return WhatsApp 平台配置列表
     */
    List<WhatsappCfgDO> getList(Collection<Long> ids);

    /**
     * 获得WhatsApp 平台配置分页
     *
     * @param pageReqVO 分页查询
     * @return WhatsApp 平台配置分页
     */
    PageResult<WhatsappCfgDO> getPage(WhatsappCfgPageReqVO pageReqVO);

    /**
     * 获得WhatsApp 平台配置列表, 用于 Excel 导出
     *
     * @param exportReqVO 查询条件
     * @return WhatsApp 平台配置列表
     */
    List<WhatsappCfgDO> getList(WhatsappCfgExportReqVO exportReqVO);

}
