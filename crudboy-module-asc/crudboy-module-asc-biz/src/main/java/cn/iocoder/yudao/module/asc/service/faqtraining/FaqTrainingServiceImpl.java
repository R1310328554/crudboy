package cn.iocoder.yudao.module.asc.service.faqtraining;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import java.util.*;
import cn.iocoder.yudao.module.asc.controller.admin.faqtraining.vo.*;
import cn.iocoder.yudao.module.asc.dal.dataobject.faqtraining.FaqTrainingDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

import cn.iocoder.yudao.module.asc.convert.faqtraining.FaqTrainingConvert;
import cn.iocoder.yudao.module.asc.dal.mysql.faqtraining.FaqTrainingMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.asc.enums.ErrorCodeConstants.*;

/**
 * 百问百答训练 Service 实现类
 *
 * @author 超级管理员
 */
@Service
@Validated
public class FaqTrainingServiceImpl implements FaqTrainingService {

    @Resource
    private FaqTrainingMapper mapper;

    @Override
    public Long create(FaqTrainingCreateReqVO createReqVO) {
        // 插入
        FaqTrainingDO faqTraining = FaqTrainingConvert.INSTANCE.convert(createReqVO);
        mapper.insert(faqTraining);
        // 返回
        return faqTraining.getId();
    }

    @Override
    public void update(FaqTrainingUpdateReqVO updateReqVO) {
        // 校验存在
        validateExists(updateReqVO.getId());
        // 更新
        FaqTrainingDO updateObj = FaqTrainingConvert.INSTANCE.convert(updateReqVO);
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
            throw exception(FAQ_TRAINING_NOT_EXISTS);
        }
    }

    @Override
    public FaqTrainingDO get(Long id) {
        return mapper.selectById(id);
    }

    @Override
    public List<FaqTrainingDO> getList(Collection<Long> ids) {
        return mapper.selectBatchIds(ids);
    }

    @Override
    public PageResult<FaqTrainingDO> getPage(FaqTrainingPageReqVO pageReqVO) {
        return mapper.selectPage(pageReqVO);
    }

    @Override
    public List<FaqTrainingDO> getList(FaqTrainingExportReqVO exportReqVO) {
        return mapper.selectList(exportReqVO);
    }

    @Override
    public List<FaqTrainingRespVO> train(List<Long> ids) {
        return null;
    }

}
