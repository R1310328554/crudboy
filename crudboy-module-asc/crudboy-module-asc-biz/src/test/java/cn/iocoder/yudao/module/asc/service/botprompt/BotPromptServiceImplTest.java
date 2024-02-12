package cn.iocoder.yudao.module.asc.service.botprompt;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

import javax.annotation.Resource;

import cn.iocoder.yudao.framework.test.core.ut.BaseDbUnitTest;

import cn.iocoder.yudao.module.asc.controller.admin.botprompt.vo.*;
import cn.iocoder.yudao.module.asc.dal.dataobject.botprompt.BotPromptDO;
import cn.iocoder.yudao.module.asc.dal.mysql.botprompt.BotPromptMapper;
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
 * {@link BotPromptServiceImpl} 的单元测试类
 *
 * @author 超级管理员
 */
@Import(BotPromptServiceImpl.class)
public class BotPromptServiceImplTest extends BaseDbUnitTest {

    @Resource
    private BotPromptServiceImpl botPromptService;

    @Resource
    private BotPromptMapper botPromptMapper;

    @Test
    public void testCreate_success() {
        // 准备参数
        BotPromptCreateReqVO reqVO = randomPojo(BotPromptCreateReqVO.class);

        // 调用
        Long botPromptId = botPromptService.create(reqVO);
        // 断言
        assertNotNull(botPromptId);
        // 校验记录的属性是否正确
        BotPromptDO botPrompt = botPromptMapper.selectById(botPromptId);
        assertPojoEquals(reqVO, botPrompt);
    }

    @Test
    public void testUpdate_success() {
        // mock 数据
        BotPromptDO db = randomPojo(BotPromptDO.class);
        botPromptMapper.insert(db);// @Sql: 先插入出一条存在的数据
        // 准备参数
        BotPromptUpdateReqVO reqVO = randomPojo(BotPromptUpdateReqVO.class, o -> {
            o.setId(db.getId()); // 设置更新的 ID
        });

        // 调用
        botPromptService.update(reqVO);
        // 校验是否更新正确
        BotPromptDO botPrompt = botPromptMapper.selectById(reqVO.getId()); // 获取最新的
        assertPojoEquals(reqVO, botPrompt);
    }

    @Test
    public void testUpdate_notExists() {
        // 准备参数
        BotPromptUpdateReqVO reqVO = randomPojo(BotPromptUpdateReqVO.class);

        // 调用, 并断言异常
        assertServiceException(() -> botPromptService.update(reqVO), BOT_PROMPT_NOT_EXISTS);
    }

    @Test
    public void testDelete_success() {
        // mock 数据
        BotPromptDO db = randomPojo(BotPromptDO.class);
        botPromptMapper.insert(db);// @Sql: 先插入出一条存在的数据
        // 准备参数
        Long id = db.getId();

        // 调用
        botPromptService.delete(id);
       // 校验数据不存在了
       assertNull(botPromptMapper.selectById(id));
    }

    @Test
    public void testDelete_notExists() {
        // 准备参数
        Long id = randomLongId();

        // 调用, 并断言异常
        assertServiceException(() -> botPromptService.delete(id), BOT_PROMPT_NOT_EXISTS);
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetPage() {
       // mock 数据
       BotPromptDO db = randomPojo(BotPromptDO.class, o -> { // 等会查询到
       });
       botPromptMapper.insert(db);
       // 准备参数
       BotPromptPageReqVO reqVO = new BotPromptPageReqVO();

       // 调用
       PageResult<BotPromptDO> pageResult = botPromptService.getPage(reqVO);
       // 断言
       assertEquals(1, pageResult.getTotal());
       assertEquals(1, pageResult.getList().size());
       assertPojoEquals(db, pageResult.getList().get(0));
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetList() {
       // mock 数据
       BotPromptDO db = randomPojo(BotPromptDO.class, o -> { // 等会查询到
       });
       botPromptMapper.insert(db);
       // 准备参数
       BotPromptExportReqVO reqVO = new BotPromptExportReqVO();

       // 调用
       List<BotPromptDO> list = botPromptService.getList(reqVO);
       // 断言
       assertEquals(1, list.size());
       assertPojoEquals(db, list.get(0));
    }

}
