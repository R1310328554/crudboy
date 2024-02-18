package cn.iocoder.yudao.module.asc.service.thirdpartycfg;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import java.util.*;
import cn.iocoder.yudao.module.asc.controller.admin.thirdpartycfg.vo.*;
import cn.iocoder.yudao.module.asc.dal.dataobject.thirdpartycfg.ThirdPartyCfgDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

import cn.iocoder.yudao.module.asc.convert.thirdpartycfg.ThirdPartyCfgConvert;
import cn.iocoder.yudao.module.asc.dal.mysql.thirdpartycfg.ThirdPartyCfgMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.asc.enums.ErrorCodeConstants.*;

/**
 * 终端平台即第三方平台配置 Service 实现类
 *
 * @author 超级管理员
 */
@Service
@Validated
public class ThirdPartyCfgServiceImpl implements ThirdPartyCfgService {

    @Resource
    private ThirdPartyCfgMapper mapper;

    @Override
    public Long create(ThirdPartyCfgCreateReqVO createReqVO) {
        // 插入
        ThirdPartyCfgDO thirdPartyCfg = ThirdPartyCfgConvert.INSTANCE.convert(createReqVO);
        mapper.insert(thirdPartyCfg);
        // 返回
        return thirdPartyCfg.getId();
    }

    @Override
    public void update(ThirdPartyCfgUpdateReqVO updateReqVO) {
        // 校验存在
        validateExists(updateReqVO.getId());
        // 更新
        ThirdPartyCfgDO updateObj = ThirdPartyCfgConvert.INSTANCE.convert(updateReqVO);
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
            throw exception(THIRD_PARTY_CFG_NOT_EXISTS);
        }
    }

    @Override
    public ThirdPartyCfgDO get(Long id) {
        return mapper.selectById(id);
    }

    @Override
    public List<ThirdPartyCfgDO> getList(Collection<Long> ids) {
        return mapper.selectBatchIds(ids);
    }

    @Override
    public PageResult<ThirdPartyCfgDO> getPage(ThirdPartyCfgPageReqVO pageReqVO) {
        return mapper.selectPage(pageReqVO);
    }

    @Override
    public List<ThirdPartyCfgDO> getList(ThirdPartyCfgExportReqVO exportReqVO) {
        return mapper.selectList(exportReqVO);
    }

}
