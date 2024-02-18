package cn.iocoder.yudao.module.asc.service.gptcfg;

import java.util.*;
import javax.validation.*;
import cn.iocoder.yudao.module.asc.controller.admin.gptcfg.vo.*;
import cn.iocoder.yudao.module.asc.dal.dataobject.gptcfg.GptCfgDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * GPT 专用的基础的配置 Service 接口
 *
 * @author 超级管理员
 */
public interface GptCfgService {

    /**
     * 创建GPT 专用的基础的配置
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long create(@Valid GptCfgCreateReqVO createReqVO);

    /**
     * 更新GPT 专用的基础的配置
     *
     * @param updateReqVO 更新信息
     */
    void update(@Valid GptCfgUpdateReqVO updateReqVO);

    /**
     * 删除GPT 专用的基础的配置
     *
     * @param id 编号
     */
    void delete(Long id);

    /**
     * 获得GPT 专用的基础的配置
     *
     * @param id 编号
     * @return GPT 专用的基础的配置
     */
    GptCfgDO get(Long id);

    /**
     * 获得GPT 专用的基础的配置列表
     *
     * @param ids 编号
     * @return GPT 专用的基础的配置列表
     */
    List<GptCfgDO> getList(Collection<Long> ids);

    /**
     * 获得GPT 专用的基础的配置分页
     *
     * @param pageReqVO 分页查询
     * @return GPT 专用的基础的配置分页
     */
    PageResult<GptCfgDO> getPage(GptCfgPageReqVO pageReqVO);

    /**
     * 获得GPT 专用的基础的配置列表, 用于 Excel 导出
     *
     * @param exportReqVO 查询条件
     * @return GPT 专用的基础的配置列表
     */
    List<GptCfgDO> getList(GptCfgExportReqVO exportReqVO);

}
