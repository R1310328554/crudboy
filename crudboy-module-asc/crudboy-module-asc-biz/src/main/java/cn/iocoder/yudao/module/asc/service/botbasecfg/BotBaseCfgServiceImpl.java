package cn.iocoder.yudao.module.asc.service.botbasecfg;

import com.fasterxml.uuid.UUIDGenerator;
import com.fasterxml.uuid.impl.UUIDUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import java.util.*;
import cn.iocoder.yudao.module.asc.controller.admin.botbasecfg.vo.*;
import cn.iocoder.yudao.module.asc.dal.dataobject.botbasecfg.BotBaseCfgDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

import cn.iocoder.yudao.module.asc.convert.botbasecfg.BotBaseCfgConvert;
import cn.iocoder.yudao.module.asc.dal.mysql.botbasecfg.BotBaseCfgMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.asc.enums.ErrorCodeConstants.*;

/**
 * 机器人的基础配置 Service 实现类
 *
 * @author 超级管理员
 */
@Service
@Validated
public class BotBaseCfgServiceImpl implements BotBaseCfgService {

    @Resource
    private BotBaseCfgMapper mapper;

    @Override
    public Long create(BotBaseCfgCreateReqVO createReqVO) {
        // 插入
        BotBaseCfgDO botBaseCfg = BotBaseCfgConvert.INSTANCE.convert(createReqVO);

        String code1 = botBaseCfg.getCode();
        if (code1 == null) {
            String post = botBaseCfg.getPost();
            if (StringUtils.isEmpty(post)) {
                post = "Bot_";
            }
            String code = post + UUID.randomUUID().toString(); // java.lang.NumberFormatException: UUID has to be represented by the standard 36-char representation
            botBaseCfg.setCode(code);
        }
        mapper.insert(botBaseCfg);
        // 返回
        return botBaseCfg.getId();
    }

    @Override
    public void update(BotBaseCfgUpdateReqVO updateReqVO) {
        // 校验存在
        validateExists(updateReqVO.getId());
        // 更新
        BotBaseCfgDO updateObj = BotBaseCfgConvert.INSTANCE.convert(updateReqVO);
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
            throw exception(BOT_BASE_CFG_NOT_EXISTS);
        }
    }

    @Override
    public BotBaseCfgDO get(Long id) {
        return mapper.selectById(id);
    }

    @Override
    public List<BotBaseCfgDO> getList(Collection<Long> ids) {
        return mapper.selectBatchIds(ids);
    }

    @Override
    public PageResult<BotBaseCfgDO> getPage(BotBaseCfgPageReqVO pageReqVO) {
        return mapper.selectPage(pageReqVO);
    }

    @Override
    public List<BotBaseCfgDO> getList(BotBaseCfgExportReqVO exportReqVO) {
        return mapper.selectList(exportReqVO);
    }

}
