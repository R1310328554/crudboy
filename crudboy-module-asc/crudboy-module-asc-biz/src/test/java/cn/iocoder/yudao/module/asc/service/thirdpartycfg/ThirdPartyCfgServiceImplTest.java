package cn.iocoder.yudao.module.asc.service.thirdpartycfg;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

import javax.annotation.Resource;

import cn.iocoder.yudao.framework.test.core.ut.BaseDbUnitTest;

import cn.iocoder.yudao.module.asc.controller.admin.thirdpartycfg.vo.*;
import cn.iocoder.yudao.module.asc.dal.dataobject.thirdpartycfg.ThirdPartyCfgDO;
import cn.iocoder.yudao.module.asc.dal.mysql.thirdpartycfg.ThirdPartyCfgMapper;
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
 * {@link ThirdPartyCfgServiceImpl} 的单元测试类
 *
 * @author 超级管理员
 */
@Import(ThirdPartyCfgServiceImpl.class)
public class ThirdPartyCfgServiceImplTest extends BaseDbUnitTest {

    @Resource
    private ThirdPartyCfgServiceImpl thirdPartyCfgService;

    @Resource
    private ThirdPartyCfgMapper thirdPartyCfgMapper;

    @Test
    public void testCreate_success() {
        // 准备参数
        ThirdPartyCfgCreateReqVO reqVO = randomPojo(ThirdPartyCfgCreateReqVO.class);

        // 调用
        Long thirdPartyCfgId = thirdPartyCfgService.create(reqVO);
        // 断言
        assertNotNull(thirdPartyCfgId);
        // 校验记录的属性是否正确
        ThirdPartyCfgDO thirdPartyCfg = thirdPartyCfgMapper.selectById(thirdPartyCfgId);
        assertPojoEquals(reqVO, thirdPartyCfg);
    }

    @Test
    public void testUpdate_success() {
        // mock 数据
        ThirdPartyCfgDO db = randomPojo(ThirdPartyCfgDO.class);
        thirdPartyCfgMapper.insert(db);// @Sql: 先插入出一条存在的数据
        // 准备参数
        ThirdPartyCfgUpdateReqVO reqVO = randomPojo(ThirdPartyCfgUpdateReqVO.class, o -> {
            o.setId(db.getId()); // 设置更新的 ID
        });

        // 调用
        thirdPartyCfgService.update(reqVO);
        // 校验是否更新正确
        ThirdPartyCfgDO thirdPartyCfg = thirdPartyCfgMapper.selectById(reqVO.getId()); // 获取最新的
        assertPojoEquals(reqVO, thirdPartyCfg);
    }

    @Test
    public void testUpdate_notExists() {
        // 准备参数
        ThirdPartyCfgUpdateReqVO reqVO = randomPojo(ThirdPartyCfgUpdateReqVO.class);

        // 调用, 并断言异常
        assertServiceException(() -> thirdPartyCfgService.update(reqVO), THIRD_PARTY_CFG_NOT_EXISTS);
    }

    @Test
    public void testDelete_success() {
        // mock 数据
        ThirdPartyCfgDO db = randomPojo(ThirdPartyCfgDO.class);
        thirdPartyCfgMapper.insert(db);// @Sql: 先插入出一条存在的数据
        // 准备参数
        Long id = db.getId();

        // 调用
        thirdPartyCfgService.delete(id);
       // 校验数据不存在了
       assertNull(thirdPartyCfgMapper.selectById(id));
    }

    @Test
    public void testDelete_notExists() {
        // 准备参数
        Long id = randomLongId();

        // 调用, 并断言异常
        assertServiceException(() -> thirdPartyCfgService.delete(id), THIRD_PARTY_CFG_NOT_EXISTS);
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetPage() {
       // mock 数据
       ThirdPartyCfgDO db = randomPojo(ThirdPartyCfgDO.class, o -> { // 等会查询到
           o.setName(null);
       });
       thirdPartyCfgMapper.insert(db);
       // 测试 name 不匹配
       thirdPartyCfgMapper.insert(cloneIgnoreId(db, o -> o.setName(null)));
       // 准备参数
       ThirdPartyCfgPageReqVO reqVO = new ThirdPartyCfgPageReqVO();
       reqVO.setName(null);

       // 调用
       PageResult<ThirdPartyCfgDO> pageResult = thirdPartyCfgService.getPage(reqVO);
       // 断言
       assertEquals(1, pageResult.getTotal());
       assertEquals(1, pageResult.getList().size());
       assertPojoEquals(db, pageResult.getList().get(0));
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetList() {
       // mock 数据
       ThirdPartyCfgDO db = randomPojo(ThirdPartyCfgDO.class, o -> { // 等会查询到
           o.setName(null);
       });
       thirdPartyCfgMapper.insert(db);
       // 测试 name 不匹配
       thirdPartyCfgMapper.insert(cloneIgnoreId(db, o -> o.setName(null)));
       // 准备参数
       ThirdPartyCfgExportReqVO reqVO = new ThirdPartyCfgExportReqVO();
       reqVO.setName(null);

       // 调用
       List<ThirdPartyCfgDO> list = thirdPartyCfgService.getList(reqVO);
       // 断言
       assertEquals(1, list.size());
       assertPojoEquals(db, list.get(0));
    }

}
