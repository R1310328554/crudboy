package cn.iocoder.yudao.module.asc.service.chatmsg;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

import javax.annotation.Resource;

import cn.iocoder.yudao.framework.test.core.ut.BaseDbUnitTest;

import cn.iocoder.yudao.module.asc.controller.admin.chatmsg.vo.*;
import cn.iocoder.yudao.module.asc.dal.dataobject.chatmsg.ChatMsgDO;
import cn.iocoder.yudao.module.asc.dal.mysql.chatmsg.ChatMsgMapper;
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
 * {@link ChatMsgServiceImpl} 的单元测试类
 *
 * @author 超级管理员
 */
@Import(ChatMsgServiceImpl.class)
public class ChatMsgServiceImplTest extends BaseDbUnitTest {

    @Resource
    private ChatMsgServiceImpl chatMsgService;

    @Resource
    private ChatMsgMapper chatMsgMapper;

    @Test
    public void testCreate_success() {
        // 准备参数
        ChatMsgCreateReqVO reqVO = randomPojo(ChatMsgCreateReqVO.class);

        // 调用
        Long chatMsgId = chatMsgService.create(reqVO);
        // 断言
        assertNotNull(chatMsgId);
        // 校验记录的属性是否正确
        ChatMsgDO chatMsg = chatMsgMapper.selectById(chatMsgId);
        assertPojoEquals(reqVO, chatMsg);
    }

    @Test
    public void testUpdate_success() {
        // mock 数据
        ChatMsgDO db = randomPojo(ChatMsgDO.class);
        chatMsgMapper.insert(db);// @Sql: 先插入出一条存在的数据
        // 准备参数
        ChatMsgUpdateReqVO reqVO = randomPojo(ChatMsgUpdateReqVO.class, o -> {
            o.setId(db.getId()); // 设置更新的 ID
        });

        // 调用
        chatMsgService.update(reqVO);
        // 校验是否更新正确
        ChatMsgDO chatMsg = chatMsgMapper.selectById(reqVO.getId()); // 获取最新的
        assertPojoEquals(reqVO, chatMsg);
    }

    @Test
    public void testUpdate_notExists() {
        // 准备参数
        ChatMsgUpdateReqVO reqVO = randomPojo(ChatMsgUpdateReqVO.class);

        // 调用, 并断言异常
        assertServiceException(() -> chatMsgService.update(reqVO), CHAT_MSG_NOT_EXISTS);
    }

    @Test
    public void testDelete_success() {
        // mock 数据
        ChatMsgDO db = randomPojo(ChatMsgDO.class);
        chatMsgMapper.insert(db);// @Sql: 先插入出一条存在的数据
        // 准备参数
        Long id = db.getId();

        // 调用
        chatMsgService.delete(id);
       // 校验数据不存在了
       assertNull(chatMsgMapper.selectById(id));
    }

    @Test
    public void testDelete_notExists() {
        // 准备参数
        Long id = randomLongId();

        // 调用, 并断言异常
        assertServiceException(() -> chatMsgService.delete(id), CHAT_MSG_NOT_EXISTS);
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetPage() {
       // mock 数据
       ChatMsgDO db = randomPojo(ChatMsgDO.class, o -> { // 等会查询到
           o.setStatus(null);
       });
       chatMsgMapper.insert(db);
       // 测试 status 不匹配
       chatMsgMapper.insert(cloneIgnoreId(db, o -> o.setStatus(null)));
       // 准备参数
       ChatMsgPageReqVO reqVO = new ChatMsgPageReqVO();
       reqVO.setStatus(null);

       // 调用
       PageResult<ChatMsgDO> pageResult = chatMsgService.getPage(reqVO);
       // 断言
       assertEquals(1, pageResult.getTotal());
       assertEquals(1, pageResult.getList().size());
       assertPojoEquals(db, pageResult.getList().get(0));
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetList() {
       // mock 数据
       ChatMsgDO db = randomPojo(ChatMsgDO.class, o -> { // 等会查询到
           o.setStatus(null);
       });
       chatMsgMapper.insert(db);
       // 测试 status 不匹配
       chatMsgMapper.insert(cloneIgnoreId(db, o -> o.setStatus(null)));
       // 准备参数
       ChatMsgExportReqVO reqVO = new ChatMsgExportReqVO();
       reqVO.setStatus(null);

       // 调用
       List<ChatMsgDO> list = chatMsgService.getList(reqVO);
       // 断言
       assertEquals(1, list.size());
       assertPojoEquals(db, list.get(0));
    }

}
