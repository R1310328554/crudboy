package cn.iocoder.yudao.module.asc.service.botbasecfg;

import java.util.*;
import javax.validation.*;
import cn.iocoder.yudao.module.asc.controller.admin.botbasecfg.vo.*;
import cn.iocoder.yudao.module.asc.dal.dataobject.botbasecfg.BotBaseCfgDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 机器人的基础配置 Service 接口
 *
 * @author 超级管理员
 */
public interface BotBaseCfgService {

    /**
     * 创建机器人的基础配置
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long create(@Valid BotBaseCfgCreateReqVO createReqVO);

    /**
     * 更新机器人的基础配置
     *
     * @param updateReqVO 更新信息
     */
    void update(@Valid BotBaseCfgUpdateReqVO updateReqVO);

    /**
     * 删除机器人的基础配置
     *
     * @param id 编号
     */
    void delete(Long id);

    /**
     * 获得机器人的基础配置
     *
     * @param id 编号
     * @return 机器人的基础配置
     */
    BotBaseCfgDO get(Long id);

    /**
     * 获得机器人的基础配置列表
     *
     * @param ids 编号
     * @return 机器人的基础配置列表
     */
    List<BotBaseCfgDO> getList(Collection<Long> ids);

    /**
     * 获得机器人的基础配置分页
     *
     * @param pageReqVO 分页查询
     * @return 机器人的基础配置分页
     */
    PageResult<BotBaseCfgDO> getPage(BotBaseCfgPageReqVO pageReqVO);

    /**
     * 获得机器人的基础配置列表, 用于 Excel 导出
     *
     * @param exportReqVO 查询条件
     * @return 机器人的基础配置列表
     */
    List<BotBaseCfgDO> getList(BotBaseCfgExportReqVO exportReqVO);

}
