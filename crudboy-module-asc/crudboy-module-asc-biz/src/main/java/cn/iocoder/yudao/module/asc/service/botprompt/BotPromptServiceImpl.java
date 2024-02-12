package cn.iocoder.yudao.module.asc.service.botprompt;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import java.util.*;
import cn.iocoder.yudao.module.asc.controller.admin.botprompt.vo.*;
import cn.iocoder.yudao.module.asc.dal.dataobject.botprompt.BotPromptDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

import cn.iocoder.yudao.module.asc.convert.botprompt.BotPromptConvert;
import cn.iocoder.yudao.module.asc.dal.mysql.botprompt.BotPromptMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.asc.enums.ErrorCodeConstants.*;

/**
 * 预设提示词 Service 实现类
 *
 * @author 超级管理员
 */
@Service
@Validated
public class BotPromptServiceImpl implements BotPromptService {

    @Resource
    private BotPromptMapper mapper;

    @Override
    public Long create(BotPromptCreateReqVO createReqVO) {
        // 插入
        BotPromptDO botPrompt = BotPromptConvert.INSTANCE.convert(createReqVO);
        mapper.insert(botPrompt);
        // 返回
        return botPrompt.getId();
    }

    @Override
    public void update(BotPromptUpdateReqVO updateReqVO) {
        // 校验存在
        validateExists(updateReqVO.getId());
        // 更新
        BotPromptDO updateObj = BotPromptConvert.INSTANCE.convert(updateReqVO);
        mapper.updateById(updateObj);
    }

    @Override
    public void delete(Long id) {
        // 校验存在
        validateExists(id);
        // 删除
        mapper.deleteById(id);
    }

    private void validateExists(Long id) {
        if (mapper.selectById(id) == null) {
            throw exception(BOT_PROMPT_NOT_EXISTS);
        }
    }

    @Override
    public BotPromptDO get(Long id) {
        return mapper.selectById(id);
    }

    @Override
    public List<BotPromptDO> getList(Collection<Long> ids) {
        return mapper.selectBatchIds(ids);
    }

    @Override
    public PageResult<BotPromptDO> getPage(BotPromptPageReqVO pageReqVO) {
        return mapper.selectPage(pageReqVO);
    }

    @Override
    public List<BotPromptDO> getList(BotPromptExportReqVO exportReqVO) {
        return mapper.selectList(exportReqVO);
    }

}
