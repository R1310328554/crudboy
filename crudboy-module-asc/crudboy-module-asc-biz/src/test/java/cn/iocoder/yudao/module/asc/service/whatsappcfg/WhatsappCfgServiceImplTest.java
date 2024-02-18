package cn.iocoder.yudao.module.asc.service.whatsappcfg;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

import javax.annotation.Resource;

import cn.iocoder.yudao.framework.test.core.ut.BaseDbUnitTest;

import cn.iocoder.yudao.module.asc.controller.admin.whatsappcfg.vo.*;
import cn.iocoder.yudao.module.asc.dal.dataobject.whatsappcfg.WhatsappCfgDO;
import cn.iocoder.yudao.module.asc.dal.mysql.whatsappcfg.WhatsappCfgMapper;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

import javax.annotation.Resource;
import org.springframework.context.annotation.Import;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDate;

import static cn.hutool.core.util.RandomUtil.*;
import static cn.iocoder.yudao.module.asc.enums.ErrorCodeConstants.*;
import static cn.iocoder.yudao.framework.test.core.util.AssertUtils.*;
import static cn.iocoder.yudao.framework.test.core.util.RandomUtils.*;
import static cn.iocoder.yudao.framework.common.util.date.LocalDateTimeUtils.*;
import static cn.iocoder.yudao.framework.common.util.object.ObjectUtils.*;
import static cn.iocoder.yudao.framework.common.util.date.DateUtils.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * {@link WhatsappCfgServiceImpl} 的单元测试类
 *
 * @author 超级管理员
 */
@Import(WhatsappCfgServiceImpl.class)
public class WhatsappCfgServiceImplTest extends BaseDbUnitTest {

    @Resource
    private WhatsappCfgServiceImpl whatsappCfgService;

    @Resource
    private WhatsappCfgMapper whatsappCfgMapper;

    @Test
    public void testCreate_success() {
        // 准备参数
        WhatsappCfgCreateReqVO reqVO = randomPojo(WhatsappCfgCreateReqVO.class);

        // 调用
        Long whatsappCfgId = whatsappCfgService.create(reqVO);
        // 断言
        assertNotNull(whatsappCfgId);
        // 校验记录的属性是否正确
        WhatsappCfgDO whatsappCfg = whatsappCfgMapper.selectById(whatsappCfgId);
        assertPojoEquals(reqVO, whatsappCfg);
    }

    @Test
    public void testUpdate_success() {
        // mock 数据
        WhatsappCfgDO db = randomPojo(WhatsappCfgDO.class);
        whatsappCfgMapper.insert(db);// @Sql: 先插入出一条存在的数据
        // 准备参数
        WhatsappCfgUpdateReqVO reqVO = randomPojo(WhatsappCfgUpdateReqVO.class, o -> {
            o.setId(db.getId()); // 设置更新的 ID
        });

        // 调用
        whatsappCfgService.update(reqVO);
        // 校验是否更新正确
        WhatsappCfgDO whatsappCfg = whatsappCfgMapper.selectById(reqVO.getId()); // 获取最新的
        assertPojoEquals(reqVO, whatsappCfg);
    }

    @Test
    public void testUpdate_notExists() {
        // 准备参数
        WhatsappCfgUpdateReqVO reqVO = randomPojo(WhatsappCfgUpdateReqVO.class);

        // 调用, 并断言异常
        assertServiceException(() -> whatsappCfgService.update(reqVO), WHATSAPP_CFG_NOT_EXISTS);
    }

    @Test
    public void testDelete_success() {
        // mock 数据
        WhatsappCfgDO db = randomPojo(WhatsappCfgDO.class);
        whatsappCfgMapper.insert(db);// @Sql: 先插入出一条存在的数据
        // 准备参数
        Long id = db.getId();

        // 调用
        whatsappCfgService.delete(id);
       // 校验数据不存在了
       assertNull(whatsappCfgMapper.selectById(id));
    }

    @Test
    public void testDelete_notExists() {
        // 准备参数
        Long id = randomLongId();

        // 调用, 并断言异常
        assertServiceException(() -> whatsappCfgService.delete(id), WHATSAPP_CFG_NOT_EXISTS);
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetPage() {
       // mock 数据
       WhatsappCfgDO db = randomPojo(WhatsappCfgDO.class, o -> { // 等会查询到
           o.setName(null);
       });
       whatsappCfgMapper.insert(db);
       // 测试 name 不匹配
       whatsappCfgMapper.insert(cloneIgnoreId(db, o -> o.setName(null)));
       // 准备参数
       WhatsappCfgPageReqVO reqVO = new WhatsappCfgPageReqVO();
       reqVO.setName(null);

       // 调用
       PageResult<WhatsappCfgDO> pageResult = whatsappCfgService.getPage(reqVO);
       // 断言
       assertEquals(1, pageResult.getTotal());
       assertEquals(1, pageResult.getList().size());
       assertPojoEquals(db, pageResult.getList().get(0));
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetList() {
       // mock 数据
       WhatsappCfgDO db = randomPojo(WhatsappCfgDO.class, o -> { // 等会查询到
           o.setName(null);
       });
       whatsappCfgMapper.insert(db);
       // 测试 name 不匹配
       whatsappCfgMapper.insert(cloneIgnoreId(db, o -> o.setName(null)));
       // 准备参数
       WhatsappCfgExportReqVO reqVO = new WhatsappCfgExportReqVO();
       reqVO.setName(null);

       // 调用
       List<WhatsappCfgDO> list = whatsappCfgService.getList(reqVO);
       // 断言
       assertEquals(1, list.size());
       assertPojoEquals(db, list.get(0));
    }

}
