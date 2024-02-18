package cn.iocoder.yudao.module.asc.service.gptcfg;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import java.util.*;
import cn.iocoder.yudao.module.asc.controller.admin.gptcfg.vo.*;
import cn.iocoder.yudao.module.asc.dal.dataobject.gptcfg.GptCfgDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

import cn.iocoder.yudao.module.asc.convert.gptcfg.GptCfgConvert;
import cn.iocoder.yudao.module.asc.dal.mysql.gptcfg.GptCfgMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.asc.enums.ErrorCodeConstants.*;

/**
 * GPT 专用的基础的配置 Service 实现类
 *
 * @author 超级管理员
 */
@Service
@Validated
public class GptCfgServiceImpl implements GptCfgService {

    @Resource
    private GptCfgMapper mapper;

    @Override
    public Long create(GptCfgCreateReqVO createReqVO) {
        // 插入
        GptCfgDO gptCfg = GptCfgConvert.INSTANCE.convert(createReqVO);
        mapper.insert(gptCfg);
        // 返回
        return gptCfg.getId();
    }

    @Override
    public void update(GptCfgUpdateReqVO updateReqVO) {
        // 校验存在
        validateExists(updateReqVO.getId());
        // 更新
        GptCfgDO updateObj = GptCfgConvert.INSTANCE.convert(updateReqVO);
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
            throw exception(GPT_CFG_NOT_EXISTS);
        }
    }

    @Override
    public GptCfgDO get(Long id) {
        return mapper.selectById(id);
    }

    @Override
    public List<GptCfgDO> getList(Collection<Long> ids) {
        return mapper.selectBatchIds(ids);
    }

    @Override
    public PageResult<GptCfgDO> getPage(GptCfgPageReqVO pageReqVO) {
        return mapper.selectPage(pageReqVO);
    }

    @Override
    public List<GptCfgDO> getList(GptCfgExportReqVO exportReqVO) {
        return mapper.selectList(exportReqVO);
    }

}
