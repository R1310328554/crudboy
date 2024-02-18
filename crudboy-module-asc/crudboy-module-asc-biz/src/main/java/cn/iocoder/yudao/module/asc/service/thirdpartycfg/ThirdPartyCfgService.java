package cn.iocoder.yudao.module.asc.service.thirdpartycfg;

import java.util.*;
import javax.validation.*;
import cn.iocoder.yudao.module.asc.controller.admin.thirdpartycfg.vo.*;
import cn.iocoder.yudao.module.asc.dal.dataobject.thirdpartycfg.ThirdPartyCfgDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 终端平台即第三方平台配置 Service 接口
 *
 * @author 超级管理员
 */
public interface ThirdPartyCfgService {

    /**
     * 创建终端平台即第三方平台配置
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long create(@Valid ThirdPartyCfgCreateReqVO createReqVO);

    /**
     * 更新终端平台即第三方平台配置
     *
     * @param updateReqVO 更新信息
     */
    void update(@Valid ThirdPartyCfgUpdateReqVO updateReqVO);

    /**
     * 删除终端平台即第三方平台配置
     *
     * @param id 编号
     */
    void delete(Long id);

    /**
     * 获得终端平台即第三方平台配置
     *
     * @param id 编号
     * @return 终端平台即第三方平台配置
     */
    ThirdPartyCfgDO get(Long id);

    /**
     * 获得终端平台即第三方平台配置列表
     *
     * @param ids 编号
     * @return 终端平台即第三方平台配置列表
     */
    List<ThirdPartyCfgDO> getList(Collection<Long> ids);

    /**
     * 获得终端平台即第三方平台配置分页
     *
     * @param pageReqVO 分页查询
     * @return 终端平台即第三方平台配置分页
     */
    PageResult<ThirdPartyCfgDO> getPage(ThirdPartyCfgPageReqVO pageReqVO);

    /**
     * 获得终端平台即第三方平台配置列表, 用于 Excel 导出
     *
     * @param exportReqVO 查询条件
     * @return 终端平台即第三方平台配置列表
     */
    List<ThirdPartyCfgDO> getList(ThirdPartyCfgExportReqVO exportReqVO);

}
