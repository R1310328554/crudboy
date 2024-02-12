package cn.iocoder.yudao.module.asc.service.enduser;

import java.util.*;
import javax.validation.*;
import cn.iocoder.yudao.module.asc.controller.admin.enduser.vo.*;
import cn.iocoder.yudao.module.asc.dal.dataobject.enduser.EndUserDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 终端用户 Service 接口
 *
 * @author 超级管理员
 */
public interface EndUserService {

    /**
     * 创建终端用户
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long create(@Valid EndUserCreateReqVO createReqVO);

    /**
     * 更新终端用户
     *
     * @param updateReqVO 更新信息
     */
    void update(@Valid EndUserUpdateReqVO updateReqVO);

    /**
     * 删除终端用户
     *
     * @param id 编号
     */
    void delete(Long id);

    /**
     * 获得终端用户
     *
     * @param id 编号
     * @return 终端用户
     */
    EndUserDO get(Long id);

    /**
     * 获得终端用户列表
     *
     * @param ids 编号
     * @return 终端用户列表
     */
    List<EndUserDO> getList(Collection<Long> ids);

    /**
     * 获得终端用户分页
     *
     * @param pageReqVO 分页查询
     * @return 终端用户分页
     */
    PageResult<EndUserDO> getPage(EndUserPageReqVO pageReqVO);

    /**
     * 获得终端用户列表, 用于 Excel 导出
     *
     * @param exportReqVO 查询条件
     * @return 终端用户列表
     */
    List<EndUserDO> getList(EndUserExportReqVO exportReqVO);

}
