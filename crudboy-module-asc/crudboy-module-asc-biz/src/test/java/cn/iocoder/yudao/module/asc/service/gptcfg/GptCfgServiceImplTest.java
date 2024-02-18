package cn.iocoder.yudao.module.asc.service.gptcfg;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

import javax.annotation.Resource;

import cn.iocoder.yudao.framework.test.core.ut.BaseDbUnitTest;

import cn.iocoder.yudao.module.asc.controller.admin.gptcfg.vo.*;
import cn.iocoder.yudao.module.asc.dal.dataobject.gptcfg.GptCfgDO;
import cn.iocoder.yudao.module.asc.dal.mysql.gptcfg.GptCfgMapper;
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
 * {@link GptCfgServiceImpl} 的单元测试类
 *
 * @author 超级管理员
 */
@Import(GptCfgServiceImpl.class)
public class GptCfgServiceImplTest extends BaseDbUnitTest {

    @Resource
    private GptCfgServiceImpl gptCfgService;

    @Resource
    private GptCfgMapper gptCfgMapper;

    @Test
    public void testCreate_success() {
        // 准备参数
        GptCfgCreateReqVO reqVO = randomPojo(GptCfgCreateReqVO.class);

        // 调用
        Long gptCfgId = gptCfgService.create(reqVO);
        // 断言
        assertNotNull(gptCfgId);
        // 校验记录的属性是否正确
        GptCfgDO gptCfg = gptCfgMapper.selectById(gptCfgId);
        assertPojoEquals(reqVO, gptCfg);
    }

    @Test
    public void testUpdate_success() {
        // mock 数据
        GptCfgDO db = randomPojo(GptCfgDO.class);
        gptCfgMapper.insert(db);// @Sql: 先插入出一条存在的数据
        // 准备参数
        GptCfgUpdateReqVO reqVO = randomPojo(GptCfgUpdateReqVO.class, o -> {
            o.setId(db.getId()); // 设置更新的 ID
        });

        // 调用
        gptCfgService.update(reqVO);
        // 校验是否更新正确
        GptCfgDO gptCfg = gptCfgMapper.selectById(reqVO.getId()); // 获取最新的
        assertPojoEquals(reqVO, gptCfg);
    }

    @Test
    public void testUpdate_notExists() {
        // 准备参数
        GptCfgUpdateReqVO reqVO = randomPojo(GptCfgUpdateReqVO.class);

        // 调用, 并断言异常
        assertServiceException(() -> gptCfgService.update(reqVO), GPT_CFG_NOT_EXISTS);
    }

    @Test
    public void testDelete_success() {
        // mock 数据
        GptCfgDO db = randomPojo(GptCfgDO.class);
        gptCfgMapper.insert(db);// @Sql: 先插入出一条存在的数据
        // 准备参数
        Long id = db.getId();

        // 调用
        gptCfgService.delete(id);
       // 校验数据不存在了
       assertNull(gptCfgMapper.selectById(id));
    }

    @Test
    public void testDelete_notExists() {
        // 准备参数
        Long id = randomLongId();

        // 调用, 并断言异常
        assertServiceException(() -> gptCfgService.delete(id), GPT_CFG_NOT_EXISTS);
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetPage() {
       // mock 数据
       GptCfgDO db = randomPojo(GptCfgDO.class, o -> { // 等会查询到
       });
       gptCfgMapper.insert(db);
       // 准备参数
       GptCfgPageReqVO reqVO = new GptCfgPageReqVO();

       // 调用
       PageResult<GptCfgDO> pageResult = gptCfgService.getPage(reqVO);
       // 断言
       assertEquals(1, pageResult.getTotal());
       assertEquals(1, pageResult.getList().size());
       assertPojoEquals(db, pageResult.getList().get(0));
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetList() {
       // mock 数据
       GptCfgDO db = randomPojo(GptCfgDO.class, o -> { // 等会查询到
       });
       gptCfgMapper.insert(db);
       // 准备参数
       GptCfgExportReqVO reqVO = new GptCfgExportReqVO();

       // 调用
       List<GptCfgDO> list = gptCfgService.getList(reqVO);
       // 断言
       assertEquals(1, list.size());
       assertPojoEquals(db, list.get(0));
    }

}
