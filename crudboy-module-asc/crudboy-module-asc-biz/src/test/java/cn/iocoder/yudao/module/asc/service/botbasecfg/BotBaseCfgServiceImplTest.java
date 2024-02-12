package cn.iocoder.yudao.module.asc.service.botbasecfg;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

import javax.annotation.Resource;

import cn.iocoder.yudao.framework.test.core.ut.BaseDbUnitTest;

import cn.iocoder.yudao.module.asc.controller.admin.botbasecfg.vo.*;
import cn.iocoder.yudao.module.asc.dal.dataobject.botbasecfg.BotBaseCfgDO;
import cn.iocoder.yudao.module.asc.dal.mysql.botbasecfg.BotBaseCfgMapper;
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
 * {@link BotBaseCfgServiceImpl} 的单元测试类
 *
 * @author 超级管理员
 */
@Import(BotBaseCfgServiceImpl.class)
public class BotBaseCfgServiceImplTest extends BaseDbUnitTest {

    @Resource
    private BotBaseCfgServiceImpl botBaseCfgService;

    @Resource
    private BotBaseCfgMapper botBaseCfgMapper;

    @Test
    public void testCreate_success() {
        // 准备参数
        BotBaseCfgCreateReqVO reqVO = randomPojo(BotBaseCfgCreateReqVO.class);

        // 调用
        Long botBaseCfgId = botBaseCfgService.create(reqVO);
        // 断言
        assertNotNull(botBaseCfgId);
        // 校验记录的属性是否正确
        BotBaseCfgDO botBaseCfg = botBaseCfgMapper.selectById(botBaseCfgId);
        assertPojoEquals(reqVO, botBaseCfg);
    }

    @Test
    public void testUpdate_success() {
        // mock 数据
        BotBaseCfgDO db = randomPojo(BotBaseCfgDO.class);
        botBaseCfgMapper.insert(db);// @Sql: 先插入出一条存在的数据
        // 准备参数
        BotBaseCfgUpdateReqVO reqVO = randomPojo(BotBaseCfgUpdateReqVO.class, o -> {
            o.setId(db.getId()); // 设置更新的 ID
        });

        // 调用
        botBaseCfgService.update(reqVO);
        // 校验是否更新正确
        BotBaseCfgDO botBaseCfg = botBaseCfgMapper.selectById(reqVO.getId()); // 获取最新的
        assertPojoEquals(reqVO, botBaseCfg);
    }

    @Test
    public void testUpdate_notExists() {
        // 准备参数
        BotBaseCfgUpdateReqVO reqVO = randomPojo(BotBaseCfgUpdateReqVO.class);

        // 调用, 并断言异常
        assertServiceException(() -> botBaseCfgService.update(reqVO), BOT_BASE_CFG_NOT_EXISTS);
    }

    @Test
    public void testDelete_success() {
        // mock 数据
        BotBaseCfgDO db = randomPojo(BotBaseCfgDO.class);
        botBaseCfgMapper.insert(db);// @Sql: 先插入出一条存在的数据
        // 准备参数
        Long id = db.getId();

        // 调用
        botBaseCfgService.delete(id);
       // 校验数据不存在了
       assertNull(botBaseCfgMapper.selectById(id));
    }

    @Test
    public void testDelete_notExists() {
        // 准备参数
        Long id = randomLongId();

        // 调用, 并断言异常
        assertServiceException(() -> botBaseCfgService.delete(id), BOT_BASE_CFG_NOT_EXISTS);
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetPage() {
       // mock 数据
       BotBaseCfgDO db = randomPojo(BotBaseCfgDO.class, o -> { // 等会查询到
           o.setCode(null);
           o.setName(null);
           o.setStatus(null);
       });
       botBaseCfgMapper.insert(db);
       // 测试 code 不匹配
       botBaseCfgMapper.insert(cloneIgnoreId(db, o -> o.setCode(null)));
       // 测试 name 不匹配
       botBaseCfgMapper.insert(cloneIgnoreId(db, o -> o.setName(null)));
       // 测试 status 不匹配
       botBaseCfgMapper.insert(cloneIgnoreId(db, o -> o.setStatus(null)));
       // 准备参数
       BotBaseCfgPageReqVO reqVO = new BotBaseCfgPageReqVO();
       reqVO.setCode(null);
       reqVO.setName(null);
       reqVO.setStatus(null);

       // 调用
       PageResult<BotBaseCfgDO> pageResult = botBaseCfgService.getPage(reqVO);
       // 断言
       assertEquals(1, pageResult.getTotal());
       assertEquals(1, pageResult.getList().size());
       assertPojoEquals(db, pageResult.getList().get(0));
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetList() {
       // mock 数据
       BotBaseCfgDO db = randomPojo(BotBaseCfgDO.class, o -> { // 等会查询到
           o.setCode(null);
           o.setName(null);
           o.setStatus(null);
       });
       botBaseCfgMapper.insert(db);
       // 测试 code 不匹配
       botBaseCfgMapper.insert(cloneIgnoreId(db, o -> o.setCode(null)));
       // 测试 name 不匹配
       botBaseCfgMapper.insert(cloneIgnoreId(db, o -> o.setName(null)));
       // 测试 status 不匹配
       botBaseCfgMapper.insert(cloneIgnoreId(db, o -> o.setStatus(null)));
       // 准备参数
       BotBaseCfgExportReqVO reqVO = new BotBaseCfgExportReqVO();
       reqVO.setCode(null);
       reqVO.setName(null);
       reqVO.setStatus(null);

       // 调用
       List<BotBaseCfgDO> list = botBaseCfgService.getList(reqVO);
       // 断言
       assertEquals(1, list.size());
       assertPojoEquals(db, list.get(0));
    }

}
