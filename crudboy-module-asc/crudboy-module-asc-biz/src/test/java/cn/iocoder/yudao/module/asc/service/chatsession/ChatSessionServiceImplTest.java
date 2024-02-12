package cn.iocoder.yudao.module.asc.service.chatsession;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

import javax.annotation.Resource;

import cn.iocoder.yudao.framework.test.core.ut.BaseDbUnitTest;

import cn.iocoder.yudao.module.asc.controller.admin.chatsession.vo.*;
import cn.iocoder.yudao.module.asc.dal.dataobject.chatsession.ChatSessionDO;
import cn.iocoder.yudao.module.asc.dal.mysql.chatsession.ChatSessionMapper;
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
 * {@link ChatSessionServiceImpl} 的单元测试类
 *
 * @author 超级管理员
 */
@Import(ChatSessionServiceImpl.class)
public class ChatSessionServiceImplTest extends BaseDbUnitTest {

    @Resource
    private ChatSessionServiceImpl chatSessionService;

    @Resource
    private ChatSessionMapper chatSessionMapper;

    @Test
    public void testCreate_success() {
        // 准备参数
        ChatSessionCreateReqVO reqVO = randomPojo(ChatSessionCreateReqVO.class);

        // 调用
        Long chatSessionId = chatSessionService.create(reqVO);
        // 断言
        assertNotNull(chatSessionId);
        // 校验记录的属性是否正确
        ChatSessionDO chatSession = chatSessionMapper.selectById(chatSessionId);
        assertPojoEquals(reqVO, chatSession);
    }

    @Test
    public void testUpdate_success() {
        // mock 数据
        ChatSessionDO db = randomPojo(ChatSessionDO.class);
        chatSessionMapper.insert(db);// @Sql: 先插入出一条存在的数据
        // 准备参数
        ChatSessionUpdateReqVO reqVO = randomPojo(ChatSessionUpdateReqVO.class, o -> {
            o.setId(db.getId()); // 设置更新的 ID
        });

        // 调用
        chatSessionService.update(reqVO);
        // 校验是否更新正确
        ChatSessionDO chatSession = chatSessionMapper.selectById(reqVO.getId()); // 获取最新的
        assertPojoEquals(reqVO, chatSession);
    }

    @Test
    public void testUpdate_notExists() {
        // 准备参数
        ChatSessionUpdateReqVO reqVO = randomPojo(ChatSessionUpdateReqVO.class);

        // 调用, 并断言异常
        assertServiceException(() -> chatSessionService.update(reqVO), CHAT_SESSION_NOT_EXISTS);
    }

    @Test
    public void testDelete_success() {
        // mock 数据
        ChatSessionDO db = randomPojo(ChatSessionDO.class);
        chatSessionMapper.insert(db);// @Sql: 先插入出一条存在的数据
        // 准备参数
        Long id = db.getId();

        // 调用
        chatSessionService.delete(id);
       // 校验数据不存在了
       assertNull(chatSessionMapper.selectById(id));
    }

    @Test
    public void testDelete_notExists() {
        // 准备参数
        Long id = randomLongId();

        // 调用, 并断言异常
        assertServiceException(() -> chatSessionService.delete(id), CHAT_SESSION_NOT_EXISTS);
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetPage() {
       // mock 数据
       ChatSessionDO db = randomPojo(ChatSessionDO.class, o -> { // 等会查询到
       });
       chatSessionMapper.insert(db);
       // 准备参数
       ChatSessionPageReqVO reqVO = new ChatSessionPageReqVO();

       // 调用
       PageResult<ChatSessionDO> pageResult = chatSessionService.getPage(reqVO);
       // 断言
       assertEquals(1, pageResult.getTotal());
       assertEquals(1, pageResult.getList().size());
       assertPojoEquals(db, pageResult.getList().get(0));
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetList() {
       // mock 数据
       ChatSessionDO db = randomPojo(ChatSessionDO.class, o -> { // 等会查询到
       });
       chatSessionMapper.insert(db);
       // 准备参数
       ChatSessionExportReqVO reqVO = new ChatSessionExportReqVO();

       // 调用
       List<ChatSessionDO> list = chatSessionService.getList(reqVO);
       // 断言
       assertEquals(1, list.size());
       assertPojoEquals(db, list.get(0));
    }

}
