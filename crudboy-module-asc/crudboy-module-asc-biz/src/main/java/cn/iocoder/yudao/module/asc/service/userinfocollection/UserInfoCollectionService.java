package cn.iocoder.yudao.module.asc.service.userinfocollection;

import java.util.*;
import javax.validation.*;
import cn.iocoder.yudao.module.asc.controller.admin.userinfocollection.vo.*;
import cn.iocoder.yudao.module.asc.dal.dataobject.userinfocollection.UserInfoCollectionDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 客户信息收集 Service 接口
 *
 * @author 超级管理员
 */
public interface UserInfoCollectionService {

    /**
     * 创建客户信息收集
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long create(@Valid UserInfoCollectionCreateReqVO createReqVO);

    /**
     * 更新客户信息收集
     *
     * @param updateReqVO 更新信息
     */
    void update(@Valid UserInfoCollectionUpdateReqVO updateReqVO);

    /**
     * 删除客户信息收集
     *
     * @param id 编号
     */
    void delete(Long id);

    /**
     * 获得客户信息收集
     *
     * @param id 编号
     * @return 客户信息收集
     */
    UserInfoCollectionDO get(Long id);

    /**
     * 获得客户信息收集列表
     *
     * @param ids 编号
     * @return 客户信息收集列表
     */
    List<UserInfoCollectionDO> getList(Collection<Long> ids);

    /**
     * 获得客户信息收集分页
     *
     * @param pageReqVO 分页查询
     * @return 客户信息收集分页
     */
    PageResult<UserInfoCollectionDO> getPage(UserInfoCollectionPageReqVO pageReqVO);

    /**
     * 获得客户信息收集列表, 用于 Excel 导出
     *
     * @param exportReqVO 查询条件
     * @return 客户信息收集列表
     */
    List<UserInfoCollectionDO> getList(UserInfoCollectionExportReqVO exportReqVO);

}
