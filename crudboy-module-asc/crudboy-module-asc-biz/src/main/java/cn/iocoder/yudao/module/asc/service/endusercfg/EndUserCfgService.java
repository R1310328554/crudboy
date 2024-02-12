package cn.iocoder.yudao.module.asc.service.endusercfg;

import java.util.*;
import javax.validation.*;
import cn.iocoder.yudao.module.asc.controller.admin.endusercfg.vo.*;
import cn.iocoder.yudao.module.asc.dal.dataobject.endusercfg.EndUserCfgDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 终端用户配置 Service 接口
 *
 * @author 超级管理员
 */
public interface EndUserCfgService {

    /**
     * 创建终端用户配置
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long create(@Valid EndUserCfgCreateReqVO createReqVO);

    /**
     * 更新终端用户配置
     *
     * @param updateReqVO 更新信息
     */
    void update(@Valid EndUserCfgUpdateReqVO updateReqVO);

    /**
     * 删除终端用户配置
     *
     * @param id 编号
     */
    void delete(Long id);

    /**
     * 获得终端用户配置
     *
     * @param id 编号
     * @return 终端用户配置
     */
    EndUserCfgDO get(Long id);

    /**
     * 获得终端用户配置列表
     *
     * @param ids 编号
     * @return 终端用户配置列表
     */
    List<EndUserCfgDO> getList(Collection<Long> ids);

    /**
     * 获得终端用户配置分页
     *
     * @param pageReqVO 分页查询
     * @return 终端用户配置分页
     */
    PageResult<EndUserCfgDO> getPage(EndUserCfgPageReqVO pageReqVO);

    /**
     * 获得终端用户配置列表, 用于 Excel 导出
     *
     * @param exportReqVO 查询条件
     * @return 终端用户配置列表
     */
    List<EndUserCfgDO> getList(EndUserCfgExportReqVO exportReqVO);

}
