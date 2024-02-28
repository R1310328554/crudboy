package cn.iocoder.yudao.module.asc.service.filetraining;

import java.util.*;
import javax.validation.*;
import cn.iocoder.yudao.module.asc.controller.admin.filetraining.vo.*;
import cn.iocoder.yudao.module.asc.dal.dataobject.filetraining.FileTrainingDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 文档训练 Service 接口
 *
 * @author 超级管理员
 */
public interface FileTrainingService {

    /**
     * 创建文档训练
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long create(@Valid FileTrainingCreateReqVO createReqVO);

    /**
     * 更新文档训练
     *
     * @param updateReqVO 更新信息
     */
    void update(@Valid FileTrainingUpdateReqVO updateReqVO);

    /**
     * 删除文档训练
     *
     * @param id 编号
     */
    void delete(Long id);

    /**
     * 获得文档训练
     *
     * @param id 编号
     * @return 文档训练
     */
    FileTrainingDO get(Long id);

    /**
     * 获得文档训练列表
     *
     * @param ids 编号
     * @return 文档训练列表
     */
    List<FileTrainingDO> getList(Collection<Long> ids);

    /**
     * 获得文档训练分页
     *
     * @param pageReqVO 分页查询
     * @return 文档训练分页
     */
    PageResult<FileTrainingDO> getPage(FileTrainingPageReqVO pageReqVO);

    /**
     * 获得文档训练列表, 用于 Excel 导出
     *
     * @param exportReqVO 查询条件
     * @return 文档训练列表
     */
    List<FileTrainingDO> getList(FileTrainingExportReqVO exportReqVO);

    List<FileTrainingRespVO> train(List<Long> ids);
}
