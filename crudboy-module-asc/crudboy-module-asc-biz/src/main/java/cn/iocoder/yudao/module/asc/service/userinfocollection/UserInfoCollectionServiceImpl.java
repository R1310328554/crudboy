package cn.iocoder.yudao.module.asc.service.userinfocollection;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import java.util.*;
import cn.iocoder.yudao.module.asc.controller.admin.userinfocollection.vo.*;
import cn.iocoder.yudao.module.asc.dal.dataobject.userinfocollection.UserInfoCollectionDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

import cn.iocoder.yudao.module.asc.convert.userinfocollection.UserInfoCollectionConvert;
import cn.iocoder.yudao.module.asc.dal.mysql.userinfocollection.UserInfoCollectionMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.asc.enums.ErrorCodeConstants.*;

/**
 * 客户信息收集 Service 实现类
 *
 * @author 超级管理员
 */
@Service
@Validated
public class UserInfoCollectionServiceImpl implements UserInfoCollectionService {

    @Resource
    private UserInfoCollectionMapper mapper;

    @Override
    public Long create(UserInfoCollectionCreateReqVO createReqVO) {
        // 插入
        UserInfoCollectionDO userInfoCollection = UserInfoCollectionConvert.INSTANCE.convert(createReqVO);
        mapper.insert(userInfoCollection);
        // 返回
        return userInfoCollection.getId();
    }

    @Override
    public void update(UserInfoCollectionUpdateReqVO updateReqVO) {
        // 校验存在
        validateExists(updateReqVO.getId());
        // 更新
        UserInfoCollectionDO updateObj = UserInfoCollectionConvert.INSTANCE.convert(updateReqVO);
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
            throw exception(USER_INFO_COLLECTION_NOT_EXISTS);
        }
    }

    @Override
    public UserInfoCollectionDO get(Long id) {
        return mapper.selectById(id);
    }

    @Override
    public List<UserInfoCollectionDO> getList(Collection<Long> ids) {
        return mapper.selectBatchIds(ids);
    }

    @Override
    public PageResult<UserInfoCollectionDO> getPage(UserInfoCollectionPageReqVO pageReqVO) {
        return mapper.selectPage(pageReqVO);
    }

    @Override
    public List<UserInfoCollectionDO> getList(UserInfoCollectionExportReqVO exportReqVO) {
        return mapper.selectList(exportReqVO);
    }

}
