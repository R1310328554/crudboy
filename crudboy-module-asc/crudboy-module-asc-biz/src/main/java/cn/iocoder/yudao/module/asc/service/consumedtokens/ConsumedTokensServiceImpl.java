package cn.iocoder.yudao.module.asc.service.consumedtokens;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import java.util.*;
import cn.iocoder.yudao.module.asc.controller.admin.consumedtokens.vo.*;
import cn.iocoder.yudao.module.asc.dal.dataobject.consumedtokens.ConsumedTokensDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

import cn.iocoder.yudao.module.asc.convert.consumedtokens.ConsumedTokensConvert;
import cn.iocoder.yudao.module.asc.dal.mysql.consumedtokens.ConsumedTokensMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.asc.enums.ErrorCodeConstants.*;

/**
 * 租户的token消耗情况 Service 实现类
 *
 * @author 超级管理员
 */
@Service
@Validated
public class ConsumedTokensServiceImpl implements ConsumedTokensService {

    @Resource
    private ConsumedTokensMapper mapper;

    @Override
    public Long create(ConsumedTokensCreateReqVO createReqVO) {
        // 插入
        ConsumedTokensDO consumedTokens = ConsumedTokensConvert.INSTANCE.convert(createReqVO);
        mapper.insert(consumedTokens);
        // 返回
        return consumedTokens.getId();
    }

    @Override
    public void update(ConsumedTokensUpdateReqVO updateReqVO) {
        // 校验存在
        validateExists(updateReqVO.getId());
        // 更新
        ConsumedTokensDO updateObj = ConsumedTokensConvert.INSTANCE.convert(updateReqVO);
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
            throw exception(CONSUMED_TOKENS_NOT_EXISTS);
        }
    }

    @Override
    public ConsumedTokensDO get(Long id) {
        return mapper.selectById(id);
    }

    @Override
    public List<ConsumedTokensDO> getList(Collection<Long> ids) {
        return mapper.selectBatchIds(ids);
    }

    @Override
    public PageResult<ConsumedTokensDO> getPage(ConsumedTokensPageReqVO pageReqVO) {
        return mapper.selectPage(pageReqVO);
    }

    @Override
    public List<ConsumedTokensDO> getList(ConsumedTokensExportReqVO exportReqVO) {
        return mapper.selectList(exportReqVO);
    }

}
