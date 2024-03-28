package cn.iocoder.yudao.module.asc.service.report;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.asc.controller.admin.report.vo.ReportCreateReqVO;
import cn.iocoder.yudao.module.asc.controller.admin.report.vo.ReportExportReqVO;
import cn.iocoder.yudao.module.asc.controller.admin.report.vo.ReportPageReqVO;
import cn.iocoder.yudao.module.asc.controller.admin.report.vo.ReportUpdateReqVO;
import cn.iocoder.yudao.module.asc.dal.dataobject.botbasecfg.BotBaseCfgDO;

import javax.validation.Valid;
import java.util.Collection;
import java.util.List;

/**
 * 机器人的基础配置 Service 接口
 *
 * @author 超级管理员
 */
public interface ReportService {

    /**
     * 创建机器人的基础配置
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long create(@Valid ReportCreateReqVO createReqVO);

    /**
     * 更新机器人的基础配置
     *
     * @param updateReqVO 更新信息
     */
    void update(@Valid ReportUpdateReqVO updateReqVO);

    /**
     * 删除机器人的基础配置
     *
     * @param id 编号
     */
    void delete(Long id);

    /**
     * 获得机器人的基础配置列表, 用于 Excel 导出
     *
     * @param exportReqVO 查询条件
     * @return 机器人的基础配置列表
     */
    List<BotBaseCfgDO> getList(ReportExportReqVO exportReqVO);

}
