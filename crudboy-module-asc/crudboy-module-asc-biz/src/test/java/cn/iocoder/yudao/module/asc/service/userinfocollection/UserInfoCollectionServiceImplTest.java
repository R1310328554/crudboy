package cn.iocoder.yudao.module.asc.service.userinfocollection;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

import javax.annotation.Resource;

import cn.iocoder.yudao.framework.test.core.ut.BaseDbUnitTest;

import cn.iocoder.yudao.module.asc.controller.admin.userinfocollection.vo.*;
import cn.iocoder.yudao.module.asc.dal.dataobject.userinfocollection.UserInfoCollectionDO;
import cn.iocoder.yudao.module.asc.dal.mysql.userinfocollection.UserInfoCollectionMapper;
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
 * {@link UserInfoCollectionServiceImpl} 的单元测试类
 *
 * @author 超级管理员
 */
@Import(UserInfoCollectionServiceImpl.class)
public class UserInfoCollectionServiceImplTest extends BaseDbUnitTest {

    @Resource
    private UserInfoCollectionServiceImpl userInfoCollectionService;

    @Resource
    private UserInfoCollectionMapper userInfoCollectionMapper;

    @Test
    public void testCreate_success() {
        // 准备参数
        UserInfoCollectionCreateReqVO reqVO = randomPojo(UserInfoCollectionCreateReqVO.class);

        // 调用
        Long userInfoCollectionId = userInfoCollectionService.create(reqVO);
        // 断言
        assertNotNull(userInfoCollectionId);
        // 校验记录的属性是否正确
        UserInfoCollectionDO userInfoCollection = userInfoCollectionMapper.selectById(userInfoCollectionId);
        assertPojoEquals(reqVO, userInfoCollection);
    }

    @Test
    public void testUpdate_success() {
        // mock 数据
        UserInfoCollectionDO db = randomPojo(UserInfoCollectionDO.class);
        userInfoCollectionMapper.insert(db);// @Sql: 先插入出一条存在的数据
        // 准备参数
        UserInfoCollectionUpdateReqVO reqVO = randomPojo(UserInfoCollectionUpdateReqVO.class, o -> {
            o.setId(db.getId()); // 设置更新的 ID
        });

        // 调用
        userInfoCollectionService.update(reqVO);
        // 校验是否更新正确
        UserInfoCollectionDO userInfoCollection = userInfoCollectionMapper.selectById(reqVO.getId()); // 获取最新的
        assertPojoEquals(reqVO, userInfoCollection);
    }

    @Test
    public void testUpdate_notExists() {
        // 准备参数
        UserInfoCollectionUpdateReqVO reqVO = randomPojo(UserInfoCollectionUpdateReqVO.class);

        // 调用, 并断言异常
        assertServiceException(() -> userInfoCollectionService.update(reqVO), USER_INFO_COLLECTION_NOT_EXISTS);
    }

    @Test
    public void testDelete_success() {
        // mock 数据
        UserInfoCollectionDO db = randomPojo(UserInfoCollectionDO.class);
        userInfoCollectionMapper.insert(db);// @Sql: 先插入出一条存在的数据
        // 准备参数
        Long id = db.getId();

        // 调用
        userInfoCollectionService.delete(id);
       // 校验数据不存在了
       assertNull(userInfoCollectionMapper.selectById(id));
    }

    @Test
    public void testDelete_notExists() {
        // 准备参数
        Long id = randomLongId();

        // 调用, 并断言异常
        assertServiceException(() -> userInfoCollectionService.delete(id), USER_INFO_COLLECTION_NOT_EXISTS);
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetPage() {
       // mock 数据
       UserInfoCollectionDO db = randomPojo(UserInfoCollectionDO.class, o -> { // 等会查询到
       });
       userInfoCollectionMapper.insert(db);
       // 准备参数
       UserInfoCollectionPageReqVO reqVO = new UserInfoCollectionPageReqVO();

       // 调用
       PageResult<UserInfoCollectionDO> pageResult = userInfoCollectionService.getPage(reqVO);
       // 断言
       assertEquals(1, pageResult.getTotal());
       assertEquals(1, pageResult.getList().size());
       assertPojoEquals(db, pageResult.getList().get(0));
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetList() {
       // mock 数据
       UserInfoCollectionDO db = randomPojo(UserInfoCollectionDO.class, o -> { // 等会查询到
       });
       userInfoCollectionMapper.insert(db);
       // 准备参数
       UserInfoCollectionExportReqVO reqVO = new UserInfoCollectionExportReqVO();

       // 调用
       List<UserInfoCollectionDO> list = userInfoCollectionService.getList(reqVO);
       // 断言
       assertEquals(1, list.size());
       assertPojoEquals(db, list.get(0));
    }

}
