package cn.iocoder.yudao.module.asc.service.endusercfg;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import java.util.*;
import cn.iocoder.yudao.module.asc.controller.admin.endusercfg.vo.*;
import cn.iocoder.yudao.module.asc.dal.dataobject.endusercfg.EndUserCfgDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

import cn.iocoder.yudao.module.asc.convert.endusercfg.EndUserCfgConvert;
import cn.iocoder.yudao.module.asc.dal.mysql.endusercfg.EndUserCfgMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.asc.enums.ErrorCodeConstants.*;

/**
 * 终端用户配置 Service 实现类
 *
 * @author 超级管理员
 */
@Service
@Validated
public class EndUserCfgServiceImpl implements EndUserCfgService {

    @Resource
    private EndUserCfgMapper mapper;

    @Override
    public Long create(EndUserCfgCreateReqVO createReqVO) {
        // 插入
        EndUserCfgDO endUserCfg = EndUserCfgConvert.INSTANCE.convert(createReqVO);
        mapper.insert(endUserCfg);
        // 返回
        return endUserCfg.getId();
    }

    @Override
    public void update(EndUserCfgUpdateReqVO updateReqVO) {
        // 校验存在
        validateExists(updateReqVO.getId());
        // 更新
        EndUserCfgDO updateObj = EndUserCfgConvert.INSTANCE.convert(updateReqVO);
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
            throw exception(END_USER_CFG_NOT_EXISTS);
        }
    }

    @Override
    public EndUserCfgDO get(Long id) {
        return mapper.selectById(id);
    }

    @Override
    public List<EndUserCfgDO> getList(Collection<Long> ids) {
        return mapper.selectBatchIds(ids);
    }

    @Override
    public PageResult<EndUserCfgDO> getPage(EndUserCfgPageReqVO pageReqVO) {
        return mapper.selectPage(pageReqVO);
    }

    @Override
    public List<EndUserCfgDO> getList(EndUserCfgExportReqVO exportReqVO) {
        return mapper.selectList(exportReqVO);
    }

}
