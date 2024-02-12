package cn.iocoder.yudao.module.asc.service.afterwardscallbackcfg;

import java.util.*;
import javax.validation.*;
import cn.iocoder.yudao.module.asc.controller.admin.afterwardscallbackcfg.vo.*;
import cn.iocoder.yudao.module.asc.dal.dataobject.afterwardscallbackcfg.AfterwardsCallbackCfgDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 后续主动沟通的询问方案 Service 接口
 *
 * @author 超级管理员
 */
public interface AfterwardsCallbackCfgService {

    /**
     * 创建后续主动沟通的询问方案
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long create(@Valid AfterwardsCallbackCfgCreateReqVO createReqVO);

    /**
     * 更新后续主动沟通的询问方案
     *
     * @param updateReqVO 更新信息
     */
    void update(@Valid AfterwardsCallbackCfgUpdateReqVO updateReqVO);

    /**
     * 删除后续主动沟通的询问方案
     *
     * @param id 编号
     */
    void delete(Long id);

    /**
     * 获得后续主动沟通的询问方案
     *
     * @param id 编号
     * @return 后续主动沟通的询问方案
     */
    AfterwardsCallbackCfgDO get(Long id);

    /**
     * 获得后续主动沟通的询问方案列表
     *
     * @param ids 编号
     * @return 后续主动沟通的询问方案列表
     */
    List<AfterwardsCallbackCfgDO> getList(Collection<Long> ids);

    /**
     * 获得后续主动沟通的询问方案分页
     *
     * @param pageReqVO 分页查询
     * @return 后续主动沟通的询问方案分页
     */
    PageResult<AfterwardsCallbackCfgDO> getPage(AfterwardsCallbackCfgPageReqVO pageReqVO);

    /**
     * 获得后续主动沟通的询问方案列表, 用于 Excel 导出
     *
     * @param exportReqVO 查询条件
     * @return 后续主动沟通的询问方案列表
     */
    List<AfterwardsCallbackCfgDO> getList(AfterwardsCallbackCfgExportReqVO exportReqVO);

}
