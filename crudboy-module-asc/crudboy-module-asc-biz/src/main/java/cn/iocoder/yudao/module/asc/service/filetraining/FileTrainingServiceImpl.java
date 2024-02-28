package cn.iocoder.yudao.module.asc.service.filetraining;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import java.util.*;
import cn.iocoder.yudao.module.asc.controller.admin.filetraining.vo.*;
import cn.iocoder.yudao.module.asc.dal.dataobject.filetraining.FileTrainingDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

import cn.iocoder.yudao.module.asc.convert.filetraining.FileTrainingConvert;
import cn.iocoder.yudao.module.asc.dal.mysql.filetraining.FileTrainingMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.asc.enums.ErrorCodeConstants.*;

/**
 * 文档训练 Service 实现类
 *
 * @author 超级管理员
 */
@Service
@Validated
public class FileTrainingServiceImpl implements FileTrainingService {

    @Resource
    private FileTrainingMapper mapper;

    @Override
    public Long create(FileTrainingCreateReqVO createReqVO) {
        // 插入
        FileTrainingDO fileTraining = FileTrainingConvert.INSTANCE.convert(createReqVO);
        mapper.insert(fileTraining);
        // 返回
        return fileTraining.getId();
    }

    @Override
    public void update(FileTrainingUpdateReqVO updateReqVO) {
        // 校验存在
        validateExists(updateReqVO.getId());
        // 更新
        FileTrainingDO updateObj = FileTrainingConvert.INSTANCE.convert(updateReqVO);
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
            throw exception(FILE_TRAINING_NOT_EXISTS);
        }
    }

    @Override
    public FileTrainingDO get(Long id) {
        return mapper.selectById(id);
    }

    @Override
    public List<FileTrainingDO> getList(Collection<Long> ids) {
        return mapper.selectBatchIds(ids);
    }

    @Override
    public PageResult<FileTrainingDO> getPage(FileTrainingPageReqVO pageReqVO) {
        return mapper.selectPage(pageReqVO);
    }

    @Override
    public List<FileTrainingDO> getList(FileTrainingExportReqVO exportReqVO) {
        return mapper.selectList(exportReqVO);
    }

    @Override
    public List<FileTrainingRespVO> train(List<Long> ids) {
        return null;
    }

}
