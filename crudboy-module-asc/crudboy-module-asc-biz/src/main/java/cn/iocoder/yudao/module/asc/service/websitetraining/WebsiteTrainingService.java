package cn.iocoder.yudao.module.asc.service.websitetraining;

import java.util.*;
import javax.validation.*;
import cn.iocoder.yudao.module.asc.controller.admin.websitetraining.vo.*;
import cn.iocoder.yudao.module.asc.dal.dataobject.websitetraining.WebsiteTrainingDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 网站内容爬取训练 Service 接口
 *
 * @author 超级管理员
 */
public interface WebsiteTrainingService {

    /**
     * 创建网站内容爬取训练
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long create(@Valid WebsiteTrainingCreateReqVO createReqVO);

    /**
     * 更新网站内容爬取训练
     *
     * @param updateReqVO 更新信息
     */
    void update(@Valid WebsiteTrainingUpdateReqVO updateReqVO);

    /**
     * 删除网站内容爬取训练
     *
     * @param id 编号
     */
    void delete(Long id);

    /**
     * 获得网站内容爬取训练
     *
     * @param id 编号
     * @return 网站内容爬取训练
     */
    WebsiteTrainingDO get(Long id);

    /**
     * 获得网站内容爬取训练列表
     *
     * @param ids 编号
     * @return 网站内容爬取训练列表
     */
    List<WebsiteTrainingDO> getList(Collection<Long> ids);

    /**
     * 获得网站内容爬取训练分页
     *
     * @param pageReqVO 分页查询
     * @return 网站内容爬取训练分页
     */
    PageResult<WebsiteTrainingDO> getPage(WebsiteTrainingPageReqVO pageReqVO);

    /**
     * 获得网站内容爬取训练列表, 用于 Excel 导出
     *
     * @param exportReqVO 查询条件
     * @return 网站内容爬取训练列表
     */
    List<WebsiteTrainingDO> getList(WebsiteTrainingExportReqVO exportReqVO);

    List<WebsiteTrainingRespVO> train(List<Long> ids);
}
