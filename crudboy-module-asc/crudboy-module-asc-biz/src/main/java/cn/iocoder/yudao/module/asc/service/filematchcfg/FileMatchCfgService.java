package cn.iocoder.yudao.module.asc.service.filematchcfg;

import java.util.*;
import javax.validation.*;
import cn.iocoder.yudao.module.asc.controller.admin.filematchcfg.vo.*;
import cn.iocoder.yudao.module.asc.dal.dataobject.filematchcfg.FileMatchCfgDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 关键信息和文件链接匹配 Service 接口
 *
 * @author 超级管理员
 */
public interface FileMatchCfgService {

    /**
     * 创建关键信息和文件链接匹配
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long create(@Valid FileMatchCfgCreateReqVO createReqVO);

    /**
     * 更新关键信息和文件链接匹配
     *
     * @param updateReqVO 更新信息
     */
    void update(@Valid FileMatchCfgUpdateReqVO updateReqVO);

    /**
     * 删除关键信息和文件链接匹配
     *
     * @param id 编号
     */
    void delete(Long id);

    /**
     * 获得关键信息和文件链接匹配
     *
     * @param id 编号
     * @return 关键信息和文件链接匹配
     */
    FileMatchCfgDO get(Long id);

    /**
     * 获得关键信息和文件链接匹配列表
     *
     * @param ids 编号
     * @return 关键信息和文件链接匹配列表
     */
    List<FileMatchCfgDO> getList(Collection<Long> ids);

    /**
     * 获得关键信息和文件链接匹配分页
     *
     * @param pageReqVO 分页查询
     * @return 关键信息和文件链接匹配分页
     */
    PageResult<FileMatchCfgDO> getPage(FileMatchCfgPageReqVO pageReqVO);

    /**
     * 获得关键信息和文件链接匹配列表, 用于 Excel 导出
     *
     * @param exportReqVO 查询条件
     * @return 关键信息和文件链接匹配列表
     */
    List<FileMatchCfgDO> getList(FileMatchCfgExportReqVO exportReqVO);

}
