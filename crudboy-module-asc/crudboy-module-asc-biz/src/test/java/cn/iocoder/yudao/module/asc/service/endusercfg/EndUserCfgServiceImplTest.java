package cn.iocoder.yudao.module.asc.service.endusercfg;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

import javax.annotation.Resource;

import cn.iocoder.yudao.framework.test.core.ut.BaseDbUnitTest;

import cn.iocoder.yudao.module.asc.controller.admin.endusercfg.vo.*;
import cn.iocoder.yudao.module.asc.dal.dataobject.endusercfg.EndUserCfgDO;
import cn.iocoder.yudao.module.asc.dal.mysql.endusercfg.EndUserCfgMapper;
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
 * {@link EndUserCfgServiceImpl} 的单元测试类
 *
 * @author 超级管理员
 */
@Import(EndUserCfgServiceImpl.class)
public class EndUserCfgServiceImplTest extends BaseDbUnitTest {

    @Resource
    private EndUserCfgServiceImpl endUserCfgService;

    @Resource
    private EndUserCfgMapper endUserCfgMapper;

    @Test
    public void testCreate_success() {
        // 准备参数
        EndUserCfgCreateReqVO reqVO = randomPojo(EndUserCfgCreateReqVO.class);

        // 调用
        Long endUserCfgId = endUserCfgService.create(reqVO);
        // 断言
        assertNotNull(endUserCfgId);
        // 校验记录的属性是否正确
        EndUserCfgDO endUserCfg = endUserCfgMapper.selectById(endUserCfgId);
        assertPojoEquals(reqVO, endUserCfg);
    }

    @Test
    public void testUpdate_success() {
        // mock 数据
        EndUserCfgDO db = randomPojo(EndUserCfgDO.class);
        endUserCfgMapper.insert(db);// @Sql: 先插入出一条存在的数据
        // 准备参数
        EndUserCfgUpdateReqVO reqVO = randomPojo(EndUserCfgUpdateReqVO.class, o -> {
            o.setId(db.getId()); // 设置更新的 ID
        });

        // 调用
        endUserCfgService.update(reqVO);
        // 校验是否更新正确
        EndUserCfgDO endUserCfg = endUserCfgMapper.selectById(reqVO.getId()); // 获取最新的
        assertPojoEquals(reqVO, endUserCfg);
    }

    @Test
    public void testUpdate_notExists() {
        // 准备参数
        EndUserCfgUpdateReqVO reqVO = randomPojo(EndUserCfgUpdateReqVO.class);

        // 调用, 并断言异常
        assertServiceException(() -> endUserCfgService.update(reqVO), END_USER_CFG_NOT_EXISTS);
    }

    @Test
    public void testDelete_success() {
        // mock 数据
        EndUserCfgDO db = randomPojo(EndUserCfgDO.class);
        endUserCfgMapper.insert(db);// @Sql: 先插入出一条存在的数据
        // 准备参数
        Long id = db.getId();

        // 调用
        endUserCfgService.delete(id);
       // 校验数据不存在了
       assertNull(endUserCfgMapper.selectById(id));
    }

    @Test
    public void testDelete_notExists() {
        // 准备参数
        Long id = randomLongId();

        // 调用, 并断言异常
        assertServiceException(() -> endUserCfgService.delete(id), END_USER_CFG_NOT_EXISTS);
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetPage() {
       // mock 数据
       EndUserCfgDO db = randomPojo(EndUserCfgDO.class, o -> { // 等会查询到
           o.setName(null);
       });
       endUserCfgMapper.insert(db);
       // 测试 name 不匹配
       endUserCfgMapper.insert(cloneIgnoreId(db, o -> o.setName(null)));
       // 准备参数
       EndUserCfgPageReqVO reqVO = new EndUserCfgPageReqVO();
       reqVO.setName(null);

       // 调用
       PageResult<EndUserCfgDO> pageResult = endUserCfgService.getPage(reqVO);
       // 断言
       assertEquals(1, pageResult.getTotal());
       assertEquals(1, pageResult.getList().size());
       assertPojoEquals(db, pageResult.getList().get(0));
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetList() {
       // mock 数据
       EndUserCfgDO db = randomPojo(EndUserCfgDO.class, o -> { // 等会查询到
           o.setName(null);
       });
       endUserCfgMapper.insert(db);
       // 测试 name 不匹配
       endUserCfgMapper.insert(cloneIgnoreId(db, o -> o.setName(null)));
       // 准备参数
       EndUserCfgExportReqVO reqVO = new EndUserCfgExportReqVO();
       reqVO.setName(null);

       // 调用
       List<EndUserCfgDO> list = endUserCfgService.getList(reqVO);
       // 断言
       assertEquals(1, list.size());
       assertPojoEquals(db, list.get(0));
    }

}
