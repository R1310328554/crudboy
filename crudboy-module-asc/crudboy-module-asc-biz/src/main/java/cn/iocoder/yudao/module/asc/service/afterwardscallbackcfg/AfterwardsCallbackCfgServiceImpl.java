package cn.iocoder.yudao.module.asc.service.afterwardscallbackcfg;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import java.util.*;
import cn.iocoder.yudao.module.asc.controller.admin.afterwardscallbackcfg.vo.*;
import cn.iocoder.yudao.module.asc.dal.dataobject.afterwardscallbackcfg.AfterwardsCallbackCfgDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

import cn.iocoder.yudao.module.asc.convert.afterwardscallbackcfg.AfterwardsCallbackCfgConvert;
import cn.iocoder.yudao.module.asc.dal.mysql.afterwardscallbackcfg.AfterwardsCallbackCfgMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.asc.enums.ErrorCodeConstants.*;

/**
 * 后续主动沟通的询问方案 Service 实现类
 *
 * @author 超级管理员
 */
@Service
@Validated
public class AfterwardsCallbackCfgServiceImpl implements AfterwardsCallbackCfgService {

    @Resource
    private AfterwardsCallbackCfgMapper mapper;

    @Override
    public Long create(AfterwardsCallbackCfgCreateReqVO createReqVO) {
        // 插入
        AfterwardsCallbackCfgDO afterwardsCallbackCfg = AfterwardsCallbackCfgConvert.INSTANCE.convert(createReqVO);
        mapper.insert(afterwardsCallbackCfg);
        // 返回
        return afterwardsCallbackCfg.getId();
    }

    @Override
    public void update(AfterwardsCallbackCfgUpdateReqVO updateReqVO) {
        // 校验存在
        validateExists(updateReqVO.getId());
        // 更新
        AfterwardsCallbackCfgDO updateObj = AfterwardsCallbackCfgConvert.INSTANCE.convert(updateReqVO);
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
            throw exception(AFTERWARDS_CALLBACK_CFG_NOT_EXISTS);
        }
    }

    @Override
    public AfterwardsCallbackCfgDO get(Long id) {
        return mapper.selectById(id);
    }

    @Override
    public List<AfterwardsCallbackCfgDO> getList(Collection<Long> ids) {
        return mapper.selectBatchIds(ids);
    }

    @Override
    public PageResult<AfterwardsCallbackCfgDO> getPage(AfterwardsCallbackCfgPageReqVO pageReqVO) {
        return mapper.selectPage(pageReqVO);
    }

    @Override
    public List<AfterwardsCallbackCfgDO> getList(AfterwardsCallbackCfgExportReqVO exportReqVO) {
        return mapper.selectList(exportReqVO);
    }

}
