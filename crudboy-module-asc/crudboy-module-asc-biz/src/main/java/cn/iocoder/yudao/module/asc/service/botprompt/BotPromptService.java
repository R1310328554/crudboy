package cn.iocoder.yudao.module.asc.service.botprompt;

import java.util.*;
import javax.validation.*;
import cn.iocoder.yudao.module.asc.controller.admin.botprompt.vo.*;
import cn.iocoder.yudao.module.asc.dal.dataobject.botprompt.BotPromptDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 预设提示词 Service 接口
 *
 * @author 超级管理员
 */
public interface BotPromptService {

    /**
     * 创建预设提示词
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long create(@Valid BotPromptCreateReqVO createReqVO);

    /**
     * 更新预设提示词
     *
     * @param updateReqVO 更新信息
     */
    void update(@Valid BotPromptUpdateReqVO updateReqVO);

    /**
     * 删除预设提示词
     *
     * @param id 编号
     */
    void delete(Long id);

    /**
     * 获得预设提示词
     *
     * @param id 编号
     * @return 预设提示词
     */
    BotPromptDO get(Long id);

    /**
     * 获得预设提示词列表
     *
     * @param ids 编号
     * @return 预设提示词列表
     */
    List<BotPromptDO> getList(Collection<Long> ids);

    /**
     * 获得预设提示词分页
     *
     * @param pageReqVO 分页查询
     * @return 预设提示词分页
     */
    PageResult<BotPromptDO> getPage(BotPromptPageReqVO pageReqVO);

    /**
     * 获得预设提示词列表, 用于 Excel 导出
     *
     * @param exportReqVO 查询条件
     * @return 预设提示词列表
     */
    List<BotPromptDO> getList(BotPromptExportReqVO exportReqVO);

}
