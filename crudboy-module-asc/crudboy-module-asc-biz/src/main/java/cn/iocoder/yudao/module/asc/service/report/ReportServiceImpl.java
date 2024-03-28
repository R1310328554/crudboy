package cn.iocoder.yudao.module.asc.service.report;

import cn.iocoder.yudao.framework.common.pojo.PageResult;

import cn.iocoder.yudao.module.asc.controller.admin.report.vo.ReportCreateReqVO;
import cn.iocoder.yudao.module.asc.controller.admin.report.vo.ReportExportReqVO;
import cn.iocoder.yudao.module.asc.controller.admin.report.vo.ReportPageReqVO;
import cn.iocoder.yudao.module.asc.controller.admin.report.vo.ReportUpdateReqVO;
import cn.iocoder.yudao.module.asc.convert.botbasecfg.BotBaseCfgConvert;
import cn.iocoder.yudao.module.asc.dal.dataobject.botbasecfg.BotBaseCfgDO;
import cn.iocoder.yudao.module.asc.dal.mysql.botbasecfg.BotBaseCfgMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.asc.enums.ErrorCodeConstants.BOT_BASE_CFG_NOT_EXISTS;

/**
 * 机器人的基础配置 Service 实现类
 *
 * @author 超级管理员
 */
@Service
@Validated
public class ReportServiceImpl implements ReportService {

    @Resource
    private BotBaseCfgMapper mapper;

    @Override
    public Long create(ReportCreateReqVO createReqVO) {
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
    public void update(ReportUpdateReqVO updateReqVO) {
        System.out.println("updateReqVO = " + updateReqVO);
        // 校验存在
        validateExists(updateReqVO.getId());
        // 更新
        BotBaseCfgDO botBaseCfg = BotBaseCfgConvert.INSTANCE.convert12(updateReqVO);
//        mapper.updateById(updateObj);
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
    public List<BotBaseCfgDO> getList(ReportExportReqVO exportReqVO) {
//        return mapper.selectList(exportReqVO);
        return new ArrayList<>();
    }

}
