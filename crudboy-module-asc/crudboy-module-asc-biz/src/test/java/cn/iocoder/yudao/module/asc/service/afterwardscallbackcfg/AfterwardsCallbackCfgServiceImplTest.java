package cn.iocoder.yudao.module.asc.service.afterwardscallbackcfg;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

import javax.annotation.Resource;

import cn.iocoder.yudao.framework.test.core.ut.BaseDbUnitTest;

import cn.iocoder.yudao.module.asc.controller.admin.afterwardscallbackcfg.vo.*;
import cn.iocoder.yudao.module.asc.dal.dataobject.afterwardscallbackcfg.AfterwardsCallbackCfgDO;
import cn.iocoder.yudao.module.asc.dal.mysql.afterwardscallbackcfg.AfterwardsCallbackCfgMapper;
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
 * {@link AfterwardsCallbackCfgServiceImpl} 的单元测试类
 *
 * @author 超级管理员
 */
@Import(AfterwardsCallbackCfgServiceImpl.class)
public class AfterwardsCallbackCfgServiceImplTest extends BaseDbUnitTest {

    @Resource
    private AfterwardsCallbackCfgServiceImpl afterwardsCallbackCfgService;

    @Resource
    private AfterwardsCallbackCfgMapper afterwardsCallbackCfgMapper;

    @Test
    public void testCreate_success() {
        // 准备参数
        AfterwardsCallbackCfgCreateReqVO reqVO = randomPojo(AfterwardsCallbackCfgCreateReqVO.class);

        // 调用
        Long afterwardsCallbackCfgId = afterwardsCallbackCfgService.create(reqVO);
        // 断言
        assertNotNull(afterwardsCallbackCfgId);
        // 校验记录的属性是否正确
        AfterwardsCallbackCfgDO afterwardsCallbackCfg = afterwardsCallbackCfgMapper.selectById(afterwardsCallbackCfgId);
        assertPojoEquals(reqVO, afterwardsCallbackCfg);
    }

    @Test
    public void testUpdate_success() {
        // mock 数据
        AfterwardsCallbackCfgDO db = randomPojo(AfterwardsCallbackCfgDO.class);
        afterwardsCallbackCfgMapper.insert(db);// @Sql: 先插入出一条存在的数据
        // 准备参数
        AfterwardsCallbackCfgUpdateReqVO reqVO = randomPojo(AfterwardsCallbackCfgUpdateReqVO.class, o -> {
            o.setId(db.getId()); // 设置更新的 ID
        });

        // 调用
        afterwardsCallbackCfgService.update(reqVO);
        // 校验是否更新正确
        AfterwardsCallbackCfgDO afterwardsCallbackCfg = afterwardsCallbackCfgMapper.selectById(reqVO.getId()); // 获取最新的
        assertPojoEquals(reqVO, afterwardsCallbackCfg);
    }

    @Test
    public void testUpdate_notExists() {
        // 准备参数
        AfterwardsCallbackCfgUpdateReqVO reqVO = randomPojo(AfterwardsCallbackCfgUpdateReqVO.class);

        // 调用, 并断言异常
        assertServiceException(() -> afterwardsCallbackCfgService.update(reqVO), AFTERWARDS_CALLBACK_CFG_NOT_EXISTS);
    }

    @Test
    public void testDelete_success() {
        // mock 数据
        AfterwardsCallbackCfgDO db = randomPojo(AfterwardsCallbackCfgDO.class);
        afterwardsCallbackCfgMapper.insert(db);// @Sql: 先插入出一条存在的数据
        // 准备参数
        Long id = db.getId();

        // 调用
        afterwardsCallbackCfgService.delete(id);
       // 校验数据不存在了
       assertNull(afterwardsCallbackCfgMapper.selectById(id));
    }

    @Test
    public void testDelete_notExists() {
        // 准备参数
        Long id = randomLongId();

        // 调用, 并断言异常
        assertServiceException(() -> afterwardsCallbackCfgService.delete(id), AFTERWARDS_CALLBACK_CFG_NOT_EXISTS);
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetPage() {
       // mock 数据
       AfterwardsCallbackCfgDO db = randomPojo(AfterwardsCallbackCfgDO.class, o -> { // 等会查询到
           o.setName(null);
       });
       afterwardsCallbackCfgMapper.insert(db);
       // 测试 name 不匹配
       afterwardsCallbackCfgMapper.insert(cloneIgnoreId(db, o -> o.setName(null)));
       // 准备参数
       AfterwardsCallbackCfgPageReqVO reqVO = new AfterwardsCallbackCfgPageReqVO();
       reqVO.setName(null);

       // 调用
       PageResult<AfterwardsCallbackCfgDO> pageResult = afterwardsCallbackCfgService.getPage(reqVO);
       // 断言
       assertEquals(1, pageResult.getTotal());
       assertEquals(1, pageResult.getList().size());
       assertPojoEquals(db, pageResult.getList().get(0));
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetList() {
       // mock 数据
       AfterwardsCallbackCfgDO db = randomPojo(AfterwardsCallbackCfgDO.class, o -> { // 等会查询到
           o.setName(null);
       });
       afterwardsCallbackCfgMapper.insert(db);
       // 测试 name 不匹配
       afterwardsCallbackCfgMapper.insert(cloneIgnoreId(db, o -> o.setName(null)));
       // 准备参数
       AfterwardsCallbackCfgExportReqVO reqVO = new AfterwardsCallbackCfgExportReqVO();
       reqVO.setName(null);

       // 调用
       List<AfterwardsCallbackCfgDO> list = afterwardsCallbackCfgService.getList(reqVO);
       // 断言
       assertEquals(1, list.size());
       assertPojoEquals(db, list.get(0));
    }

}
