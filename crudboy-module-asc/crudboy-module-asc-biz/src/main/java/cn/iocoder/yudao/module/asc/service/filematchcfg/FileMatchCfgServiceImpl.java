package cn.iocoder.yudao.module.asc.service.filematchcfg;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import java.util.*;
import cn.iocoder.yudao.module.asc.controller.admin.filematchcfg.vo.*;
import cn.iocoder.yudao.module.asc.dal.dataobject.filematchcfg.FileMatchCfgDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

import cn.iocoder.yudao.module.asc.convert.filematchcfg.FileMatchCfgConvert;
import cn.iocoder.yudao.module.asc.dal.mysql.filematchcfg.FileMatchCfgMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.asc.enums.ErrorCodeConstants.*;

/**
 * 关键信息和文件链接匹配 Service 实现类
 *
 * @author 超级管理员
 */
@Service
@Validated
public class FileMatchCfgServiceImpl implements FileMatchCfgService {

    @Resource
    private FileMatchCfgMapper mapper;

    @Override
    public Long create(FileMatchCfgCreateReqVO createReqVO) {
        // 插入
        FileMatchCfgDO fileMatchCfg = FileMatchCfgConvert.INSTANCE.convert(createReqVO);
        mapper.insert(fileMatchCfg);
        // 返回
        return fileMatchCfg.getId();
    }

    @Override
    public void update(FileMatchCfgUpdateReqVO updateReqVO) {
        // 校验存在
        validateExists(updateReqVO.getId());
        // 更新
        FileMatchCfgDO updateObj = FileMatchCfgConvert.INSTANCE.convert(updateReqVO);
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
            throw exception(FILE_MATCH_CFG_NOT_EXISTS);
        }
    }

    @Override
    public FileMatchCfgDO get(Long id) {
        return mapper.selectById(id);
    }

    @Override
    public List<FileMatchCfgDO> getList(Collection<Long> ids) {
        return mapper.selectBatchIds(ids);
    }

    @Override
    public PageResult<FileMatchCfgDO> getPage(FileMatchCfgPageReqVO pageReqVO) {
        return mapper.selectPage(pageReqVO);
    }

    @Override
    public List<FileMatchCfgDO> getList(FileMatchCfgExportReqVO exportReqVO) {
        return mapper.selectList(exportReqVO);
    }

}
