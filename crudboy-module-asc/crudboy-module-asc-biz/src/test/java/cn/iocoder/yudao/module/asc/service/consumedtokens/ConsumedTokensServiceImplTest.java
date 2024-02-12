package cn.iocoder.yudao.module.asc.service.consumedtokens;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

import javax.annotation.Resource;

import cn.iocoder.yudao.framework.test.core.ut.BaseDbUnitTest;

import cn.iocoder.yudao.module.asc.controller.admin.consumedtokens.vo.*;
import cn.iocoder.yudao.module.asc.dal.dataobject.consumedtokens.ConsumedTokensDO;
import cn.iocoder.yudao.module.asc.dal.mysql.consumedtokens.ConsumedTokensMapper;
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
 * {@link ConsumedTokensServiceImpl} 的单元测试类
 *
 * @author 超级管理员
 */
@Import(ConsumedTokensServiceImpl.class)
public class ConsumedTokensServiceImplTest extends BaseDbUnitTest {

    @Resource
    private ConsumedTokensServiceImpl consumedTokensService;

    @Resource
    private ConsumedTokensMapper consumedTokensMapper;

    @Test
    public void testCreate_success() {
        // 准备参数
        ConsumedTokensCreateReqVO reqVO = randomPojo(ConsumedTokensCreateReqVO.class);

        // 调用
        Long consumedTokensId = consumedTokensService.create(reqVO);
        // 断言
        assertNotNull(consumedTokensId);
        // 校验记录的属性是否正确
        ConsumedTokensDO consumedTokens = consumedTokensMapper.selectById(consumedTokensId);
        assertPojoEquals(reqVO, consumedTokens);
    }

    @Test
    public void testUpdate_success() {
        // mock 数据
        ConsumedTokensDO db = randomPojo(ConsumedTokensDO.class);
        consumedTokensMapper.insert(db);// @Sql: 先插入出一条存在的数据
        // 准备参数
        ConsumedTokensUpdateReqVO reqVO = randomPojo(ConsumedTokensUpdateReqVO.class, o -> {
            o.setId(db.getId()); // 设置更新的 ID
        });

        // 调用
        consumedTokensService.update(reqVO);
        // 校验是否更新正确
        ConsumedTokensDO consumedTokens = consumedTokensMapper.selectById(reqVO.getId()); // 获取最新的
        assertPojoEquals(reqVO, consumedTokens);
    }

    @Test
    public void testUpdate_notExists() {
        // 准备参数
        ConsumedTokensUpdateReqVO reqVO = randomPojo(ConsumedTokensUpdateReqVO.class);

        // 调用, 并断言异常
        assertServiceException(() -> consumedTokensService.update(reqVO), CONSUMED_TOKENS_NOT_EXISTS);
    }

    @Test
    public void testDelete_success() {
        // mock 数据
        ConsumedTokensDO db = randomPojo(ConsumedTokensDO.class);
        consumedTokensMapper.insert(db);// @Sql: 先插入出一条存在的数据
        // 准备参数
        Long id = db.getId();

        // 调用
        consumedTokensService.delete(id);
       // 校验数据不存在了
       assertNull(consumedTokensMapper.selectById(id));
    }

    @Test
    public void testDelete_notExists() {
        // 准备参数
        Long id = randomLongId();

        // 调用, 并断言异常
        assertServiceException(() -> consumedTokensService.delete(id), CONSUMED_TOKENS_NOT_EXISTS);
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetPage() {
       // mock 数据
       ConsumedTokensDO db = randomPojo(ConsumedTokensDO.class, o -> { // 等会查询到
       });
       consumedTokensMapper.insert(db);
       // 准备参数
       ConsumedTokensPageReqVO reqVO = new ConsumedTokensPageReqVO();

       // 调用
       PageResult<ConsumedTokensDO> pageResult = consumedTokensService.getPage(reqVO);
       // 断言
       assertEquals(1, pageResult.getTotal());
       assertEquals(1, pageResult.getList().size());
       assertPojoEquals(db, pageResult.getList().get(0));
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetList() {
       // mock 数据
       ConsumedTokensDO db = randomPojo(ConsumedTokensDO.class, o -> { // 等会查询到
       });
       consumedTokensMapper.insert(db);
       // 准备参数
       ConsumedTokensExportReqVO reqVO = new ConsumedTokensExportReqVO();

       // 调用
       List<ConsumedTokensDO> list = consumedTokensService.getList(reqVO);
       // 断言
       assertEquals(1, list.size());
       assertPojoEquals(db, list.get(0));
    }

}
