package cn.iocoder.yudao.module.asc.service.websitetraining;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import java.util.*;
import cn.iocoder.yudao.module.asc.controller.admin.websitetraining.vo.*;
import cn.iocoder.yudao.module.asc.dal.dataobject.websitetraining.WebsiteTrainingDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

import cn.iocoder.yudao.module.asc.convert.websitetraining.WebsiteTrainingConvert;
import cn.iocoder.yudao.module.asc.dal.mysql.websitetraining.WebsiteTrainingMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.asc.enums.ErrorCodeConstants.*;

/**
 * 网站内容爬取训练 Service 实现类
 *
 * @author 超级管理员
 */
@Service
@Validated
public class WebsiteTrainingServiceImpl implements WebsiteTrainingService {

    @Resource
    private WebsiteTrainingMapper mapper;

    @Override
    public Long create(WebsiteTrainingCreateReqVO createReqVO) {
        // 插入
        WebsiteTrainingDO websiteTraining = WebsiteTrainingConvert.INSTANCE.convert(createReqVO);
        mapper.insert(websiteTraining);
        // 返回
        return websiteTraining.getId();
    }

    @Override
    public void update(WebsiteTrainingUpdateReqVO updateReqVO) {
        // 校验存在
        validateExists(updateReqVO.getId());
        // 更新
        WebsiteTrainingDO updateObj = WebsiteTrainingConvert.INSTANCE.convert(updateReqVO);
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
            throw exception(WEBSITE_TRAINING_NOT_EXISTS);
        }
    }

    @Override
    public WebsiteTrainingDO get(Long id) {
        return mapper.selectById(id);
    }

    @Override
    public List<WebsiteTrainingDO> getList(Collection<Long> ids) {
        return mapper.selectBatchIds(ids);
    }

    @Override
    public PageResult<WebsiteTrainingDO> getPage(WebsiteTrainingPageReqVO pageReqVO) {
        return mapper.selectPage(pageReqVO);
    }

    @Override
    public List<WebsiteTrainingDO> getList(WebsiteTrainingExportReqVO exportReqVO) {
        return mapper.selectList(exportReqVO);
    }

}
