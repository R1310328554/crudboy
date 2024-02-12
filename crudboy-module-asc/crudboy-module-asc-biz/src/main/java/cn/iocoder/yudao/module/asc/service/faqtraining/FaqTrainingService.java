package cn.iocoder.yudao.module.asc.service.faqtraining;

import java.util.*;
import javax.validation.*;
import cn.iocoder.yudao.module.asc.controller.admin.faqtraining.vo.*;
import cn.iocoder.yudao.module.asc.dal.dataobject.faqtraining.FaqTrainingDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 百问百答训练 Service 接口
 *
 * @author 超级管理员
 */
public interface FaqTrainingService {

    /**
     * 创建百问百答训练
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long create(@Valid FaqTrainingCreateReqVO createReqVO);

    /**
     * 更新百问百答训练
     *
     * @param updateReqVO 更新信息
     */
    void update(@Valid FaqTrainingUpdateReqVO updateReqVO);

    /**
     * 删除百问百答训练
     *
     * @param id 编号
     */
    void delete(Long id);

    /**
     * 获得百问百答训练
     *
     * @param id 编号
     * @return 百问百答训练
     */
    FaqTrainingDO get(Long id);

    /**
     * 获得百问百答训练列表
     *
     * @param ids 编号
     * @return 百问百答训练列表
     */
    List<FaqTrainingDO> getList(Collection<Long> ids);

    /**
     * 获得百问百答训练分页
     *
     * @param pageReqVO 分页查询
     * @return 百问百答训练分页
     */
    PageResult<FaqTrainingDO> getPage(FaqTrainingPageReqVO pageReqVO);

    /**
     * 获得百问百答训练列表, 用于 Excel 导出
     *
     * @param exportReqVO 查询条件
     * @return 百问百答训练列表
     */
    List<FaqTrainingDO> getList(FaqTrainingExportReqVO exportReqVO);

}
