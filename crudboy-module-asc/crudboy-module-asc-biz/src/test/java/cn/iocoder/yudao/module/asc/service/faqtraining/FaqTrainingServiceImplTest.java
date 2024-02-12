package cn.iocoder.yudao.module.asc.service.faqtraining;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

import javax.annotation.Resource;

import cn.iocoder.yudao.framework.test.core.ut.BaseDbUnitTest;

import cn.iocoder.yudao.module.asc.controller.admin.faqtraining.vo.*;
import cn.iocoder.yudao.module.asc.dal.dataobject.faqtraining.FaqTrainingDO;
import cn.iocoder.yudao.module.asc.dal.mysql.faqtraining.FaqTrainingMapper;
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
 * {@link FaqTrainingServiceImpl} 的单元测试类
 *
 * @author 超级管理员
 */
@Import(FaqTrainingServiceImpl.class)
public class FaqTrainingServiceImplTest extends BaseDbUnitTest {

    @Resource
    private FaqTrainingServiceImpl faqTrainingService;

    @Resource
    private FaqTrainingMapper faqTrainingMapper;

    @Test
    public void testCreate_success() {
        // 准备参数
        FaqTrainingCreateReqVO reqVO = randomPojo(FaqTrainingCreateReqVO.class);

        // 调用
        Long faqTrainingId = faqTrainingService.create(reqVO);
        // 断言
        assertNotNull(faqTrainingId);
        // 校验记录的属性是否正确
        FaqTrainingDO faqTraining = faqTrainingMapper.selectById(faqTrainingId);
        assertPojoEquals(reqVO, faqTraining);
    }

    @Test
    public void testUpdate_success() {
        // mock 数据
        FaqTrainingDO db = randomPojo(FaqTrainingDO.class);
        faqTrainingMapper.insert(db);// @Sql: 先插入出一条存在的数据
        // 准备参数
        FaqTrainingUpdateReqVO reqVO = randomPojo(FaqTrainingUpdateReqVO.class, o -> {
            o.setId(db.getId()); // 设置更新的 ID
        });

        // 调用
        faqTrainingService.update(reqVO);
        // 校验是否更新正确
        FaqTrainingDO faqTraining = faqTrainingMapper.selectById(reqVO.getId()); // 获取最新的
        assertPojoEquals(reqVO, faqTraining);
    }

    @Test
    public void testUpdate_notExists() {
        // 准备参数
        FaqTrainingUpdateReqVO reqVO = randomPojo(FaqTrainingUpdateReqVO.class);

        // 调用, 并断言异常
        assertServiceException(() -> faqTrainingService.update(reqVO), FAQ_TRAINING_NOT_EXISTS);
    }

    @Test
    public void testDelete_success() {
        // mock 数据
        FaqTrainingDO db = randomPojo(FaqTrainingDO.class);
        faqTrainingMapper.insert(db);// @Sql: 先插入出一条存在的数据
        // 准备参数
        Long id = db.getId();

        // 调用
        faqTrainingService.delete(id);
       // 校验数据不存在了
       assertNull(faqTrainingMapper.selectById(id));
    }

    @Test
    public void testDelete_notExists() {
        // 准备参数
        Long id = randomLongId();

        // 调用, 并断言异常
        assertServiceException(() -> faqTrainingService.delete(id), FAQ_TRAINING_NOT_EXISTS);
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetPage() {
       // mock 数据
       FaqTrainingDO db = randomPojo(FaqTrainingDO.class, o -> { // 等会查询到
       });
       faqTrainingMapper.insert(db);
       // 准备参数
       FaqTrainingPageReqVO reqVO = new FaqTrainingPageReqVO();

       // 调用
       PageResult<FaqTrainingDO> pageResult = faqTrainingService.getPage(reqVO);
       // 断言
       assertEquals(1, pageResult.getTotal());
       assertEquals(1, pageResult.getList().size());
       assertPojoEquals(db, pageResult.getList().get(0));
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetList() {
       // mock 数据
       FaqTrainingDO db = randomPojo(FaqTrainingDO.class, o -> { // 等会查询到
       });
       faqTrainingMapper.insert(db);
       // 准备参数
       FaqTrainingExportReqVO reqVO = new FaqTrainingExportReqVO();

       // 调用
       List<FaqTrainingDO> list = faqTrainingService.getList(reqVO);
       // 断言
       assertEquals(1, list.size());
       assertPojoEquals(db, list.get(0));
    }

}
