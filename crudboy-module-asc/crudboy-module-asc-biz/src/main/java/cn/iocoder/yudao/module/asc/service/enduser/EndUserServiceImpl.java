package cn.iocoder.yudao.module.asc.service.enduser;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import java.util.*;
import cn.iocoder.yudao.module.asc.controller.admin.enduser.vo.*;
import cn.iocoder.yudao.module.asc.dal.dataobject.enduser.EndUserDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

import cn.iocoder.yudao.module.asc.convert.enduser.EndUserConvert;
import cn.iocoder.yudao.module.asc.dal.mysql.enduser.EndUserMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.asc.enums.ErrorCodeConstants.*;

/**
 * 终端用户 Service 实现类
 *
 * @author 超级管理员
 */
@Service
@Validated
public class EndUserServiceImpl implements EndUserService {

    @Resource
    private EndUserMapper mapper;

    @Override
    public Long create(EndUserCreateReqVO createReqVO) {
        // 插入
        EndUserDO endUser = EndUserConvert.INSTANCE.convert(createReqVO);
        mapper.insert(endUser);
        // 返回
        return endUser.getId();
    }

    @Override
    public void update(EndUserUpdateReqVO updateReqVO) {
        // 校验存在
        validateExists(updateReqVO.getId());
        // 更新
        EndUserDO updateObj = EndUserConvert.INSTANCE.convert(updateReqVO);
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
            throw exception(END_USER_NOT_EXISTS);
        }
    }

    @Override
    public EndUserDO get(Long id) {
        return mapper.selectById(id);
    }

    @Override
    public List<EndUserDO> getList(Collection<Long> ids) {
        return mapper.selectBatchIds(ids);
    }

    @Override
    public PageResult<EndUserDO> getPage(EndUserPageReqVO pageReqVO) {
        return mapper.selectPage(pageReqVO);
    }

    @Override
    public List<EndUserDO> getList(EndUserExportReqVO exportReqVO) {
        return mapper.selectList(exportReqVO);
    }

}
