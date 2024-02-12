package cn.iocoder.yudao.module.asc.service.enduser;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

import javax.annotation.Resource;

import cn.iocoder.yudao.framework.test.core.ut.BaseDbUnitTest;

import cn.iocoder.yudao.module.asc.controller.admin.enduser.vo.*;
import cn.iocoder.yudao.module.asc.dal.dataobject.enduser.EndUserDO;
import cn.iocoder.yudao.module.asc.dal.mysql.enduser.EndUserMapper;
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
 * {@link EndUserServiceImpl} 的单元测试类
 *
 * @author 超级管理员
 */
@Import(EndUserServiceImpl.class)
public class EndUserServiceImplTest extends BaseDbUnitTest {

    @Resource
    private EndUserServiceImpl endUserService;

    @Resource
    private EndUserMapper endUserMapper;

    @Test
    public void testCreate_success() {
        // 准备参数
        EndUserCreateReqVO reqVO = randomPojo(EndUserCreateReqVO.class);

        // 调用
        Long endUserId = endUserService.create(reqVO);
        // 断言
        assertNotNull(endUserId);
        // 校验记录的属性是否正确
        EndUserDO endUser = endUserMapper.selectById(endUserId);
        assertPojoEquals(reqVO, endUser);
    }

    @Test
    public void testUpdate_success() {
        // mock 数据
        EndUserDO db = randomPojo(EndUserDO.class);
        endUserMapper.insert(db);// @Sql: 先插入出一条存在的数据
        // 准备参数
        EndUserUpdateReqVO reqVO = randomPojo(EndUserUpdateReqVO.class, o -> {
            o.setId(db.getId()); // 设置更新的 ID
        });

        // 调用
        endUserService.update(reqVO);
        // 校验是否更新正确
        EndUserDO endUser = endUserMapper.selectById(reqVO.getId()); // 获取最新的
        assertPojoEquals(reqVO, endUser);
    }

    @Test
    public void testUpdate_notExists() {
        // 准备参数
        EndUserUpdateReqVO reqVO = randomPojo(EndUserUpdateReqVO.class);

        // 调用, 并断言异常
        assertServiceException(() -> endUserService.update(reqVO), END_USER_NOT_EXISTS);
    }

    @Test
    public void testDelete_success() {
        // mock 数据
        EndUserDO db = randomPojo(EndUserDO.class);
        endUserMapper.insert(db);// @Sql: 先插入出一条存在的数据
        // 准备参数
        Long id = db.getId();

        // 调用
        endUserService.delete(id);
       // 校验数据不存在了
       assertNull(endUserMapper.selectById(id));
    }

    @Test
    public void testDelete_notExists() {
        // 准备参数
        Long id = randomLongId();

        // 调用, 并断言异常
        assertServiceException(() -> endUserService.delete(id), END_USER_NOT_EXISTS);
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetPage() {
       // mock 数据
       EndUserDO db = randomPojo(EndUserDO.class, o -> { // 等会查询到
       });
       endUserMapper.insert(db);
       // 准备参数
       EndUserPageReqVO reqVO = new EndUserPageReqVO();

       // 调用
       PageResult<EndUserDO> pageResult = endUserService.getPage(reqVO);
       // 断言
       assertEquals(1, pageResult.getTotal());
       assertEquals(1, pageResult.getList().size());
       assertPojoEquals(db, pageResult.getList().get(0));
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetList() {
       // mock 数据
       EndUserDO db = randomPojo(EndUserDO.class, o -> { // 等会查询到
       });
       endUserMapper.insert(db);
       // 准备参数
       EndUserExportReqVO reqVO = new EndUserExportReqVO();

       // 调用
       List<EndUserDO> list = endUserService.getList(reqVO);
       // 断言
       assertEquals(1, list.size());
       assertPojoEquals(db, list.get(0));
    }

}
