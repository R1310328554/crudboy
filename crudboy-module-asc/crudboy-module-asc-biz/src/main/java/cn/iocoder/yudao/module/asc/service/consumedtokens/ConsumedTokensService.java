package cn.iocoder.yudao.module.asc.service.consumedtokens;

import java.util.*;
import javax.validation.*;
import cn.iocoder.yudao.module.asc.controller.admin.consumedtokens.vo.*;
import cn.iocoder.yudao.module.asc.dal.dataobject.consumedtokens.ConsumedTokensDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 租户的token消耗情况 Service 接口
 *
 * @author 超级管理员
 */
public interface ConsumedTokensService {

    /**
     * 创建租户的token消耗情况
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long create(@Valid ConsumedTokensCreateReqVO createReqVO);

    /**
     * 更新租户的token消耗情况
     *
     * @param updateReqVO 更新信息
     */
    void update(@Valid ConsumedTokensUpdateReqVO updateReqVO);

    /**
     * 删除租户的token消耗情况
     *
     * @param id 编号
     */
    void delete(Long id);

    /**
     * 获得租户的token消耗情况
     *
     * @param id 编号
     * @return 租户的token消耗情况
     */
    ConsumedTokensDO get(Long id);

    /**
     * 获得租户的token消耗情况列表
     *
     * @param ids 编号
     * @return 租户的token消耗情况列表
     */
    List<ConsumedTokensDO> getList(Collection<Long> ids);

    /**
     * 获得租户的token消耗情况分页
     *
     * @param pageReqVO 分页查询
     * @return 租户的token消耗情况分页
     */
    PageResult<ConsumedTokensDO> getPage(ConsumedTokensPageReqVO pageReqVO);

    /**
     * 获得租户的token消耗情况列表, 用于 Excel 导出
     *
     * @param exportReqVO 查询条件
     * @return 租户的token消耗情况列表
     */
    List<ConsumedTokensDO> getList(ConsumedTokensExportReqVO exportReqVO);

}
