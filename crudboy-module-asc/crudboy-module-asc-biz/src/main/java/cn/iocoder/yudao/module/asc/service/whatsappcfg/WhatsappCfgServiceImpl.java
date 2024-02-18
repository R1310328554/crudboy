package cn.iocoder.yudao.module.asc.service.whatsappcfg;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import java.util.*;
import cn.iocoder.yudao.module.asc.controller.admin.whatsappcfg.vo.*;
import cn.iocoder.yudao.module.asc.dal.dataobject.whatsappcfg.WhatsappCfgDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

import cn.iocoder.yudao.module.asc.convert.whatsappcfg.WhatsappCfgConvert;
import cn.iocoder.yudao.module.asc.dal.mysql.whatsappcfg.WhatsappCfgMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.asc.enums.ErrorCodeConstants.*;

/**
 * WhatsApp 平台配置 Service 实现类
 *
 * @author 超级管理员
 */
@Service
@Validated
public class WhatsappCfgServiceImpl implements WhatsappCfgService {

    @Resource
    private WhatsappCfgMapper mapper;

    @Override
    public Long create(WhatsappCfgCreateReqVO createReqVO) {
        // 插入
        WhatsappCfgDO whatsappCfg = WhatsappCfgConvert.INSTANCE.convert(createReqVO);
        mapper.insert(whatsappCfg);
        // 返回
        return whatsappCfg.getId();
    }

    @Override
    public void update(WhatsappCfgUpdateReqVO updateReqVO) {
        // 校验存在
        validateExists(updateReqVO.getId());
        // 更新
        WhatsappCfgDO updateObj = WhatsappCfgConvert.INSTANCE.convert(updateReqVO);
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
            throw exception(WHATSAPP_CFG_NOT_EXISTS);
        }
    }

    @Override
    public WhatsappCfgDO get(Long id) {
        return mapper.selectById(id);
    }

    @Override
    public List<WhatsappCfgDO> getList(Collection<Long> ids) {
        return mapper.selectBatchIds(ids);
    }

    @Override
    public PageResult<WhatsappCfgDO> getPage(WhatsappCfgPageReqVO pageReqVO) {
        return mapper.selectPage(pageReqVO);
    }

    @Override
    public List<WhatsappCfgDO> getList(WhatsappCfgExportReqVO exportReqVO) {
        return mapper.selectList(exportReqVO);
    }

}
