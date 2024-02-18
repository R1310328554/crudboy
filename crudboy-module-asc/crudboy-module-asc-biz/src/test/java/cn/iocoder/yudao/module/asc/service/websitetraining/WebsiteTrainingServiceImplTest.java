package cn.iocoder.yudao.module.asc.service.websitetraining;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

import javax.annotation.Resource;

import cn.iocoder.yudao.framework.test.core.ut.BaseDbUnitTest;

import cn.iocoder.yudao.module.asc.controller.admin.websitetraining.vo.*;
import cn.iocoder.yudao.module.asc.dal.dataobject.websitetraining.WebsiteTrainingDO;
import cn.iocoder.yudao.module.asc.dal.mysql.websitetraining.WebsiteTrainingMapper;
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
 * {@link WebsiteTrainingServiceImpl} 的单元测试类
 *
 * @author 超级管理员
 */
@Import(WebsiteTrainingServiceImpl.class)
public class WebsiteTrainingServiceImplTest extends BaseDbUnitTest {

    @Resource
    private WebsiteTrainingServiceImpl websiteTrainingService;

    @Resource
    private WebsiteTrainingMapper websiteTrainingMapper;

    @Test
    public void testCreate_success() {
        // 准备参数
        WebsiteTrainingCreateReqVO reqVO = randomPojo(WebsiteTrainingCreateReqVO.class);

        // 调用
        Long websiteTrainingId = websiteTrainingService.create(reqVO);
        // 断言
        assertNotNull(websiteTrainingId);
        // 校验记录的属性是否正确
        WebsiteTrainingDO websiteTraining = websiteTrainingMapper.selectById(websiteTrainingId);
        assertPojoEquals(reqVO, websiteTraining);
    }

    @Test
    public void testUpdate_success() {
        // mock 数据
        WebsiteTrainingDO db = randomPojo(WebsiteTrainingDO.class);
        websiteTrainingMapper.insert(db);// @Sql: 先插入出一条存在的数据
        // 准备参数
        WebsiteTrainingUpdateReqVO reqVO = randomPojo(WebsiteTrainingUpdateReqVO.class, o -> {
            o.setId(db.getId()); // 设置更新的 ID
        });

        // 调用
        websiteTrainingService.update(reqVO);
        // 校验是否更新正确
        WebsiteTrainingDO websiteTraining = websiteTrainingMapper.selectById(reqVO.getId()); // 获取最新的
        assertPojoEquals(reqVO, websiteTraining);
    }

    @Test
    public void testUpdate_notExists() {
        // 准备参数
        WebsiteTrainingUpdateReqVO reqVO = randomPojo(WebsiteTrainingUpdateReqVO.class);

        // 调用, 并断言异常
        assertServiceException(() -> websiteTrainingService.update(reqVO), WEBSITE_TRAINING_NOT_EXISTS);
    }

    @Test
    public void testDelete_success() {
        // mock 数据
        WebsiteTrainingDO db = randomPojo(WebsiteTrainingDO.class);
        websiteTrainingMapper.insert(db);// @Sql: 先插入出一条存在的数据
        // 准备参数
        Long id = db.getId();

        // 调用
        websiteTrainingService.delete(id);
       // 校验数据不存在了
       assertNull(websiteTrainingMapper.selectById(id));
    }

    @Test
    public void testDelete_notExists() {
        // 准备参数
        Long id = randomLongId();

        // 调用, 并断言异常
        assertServiceException(() -> websiteTrainingService.delete(id), WEBSITE_TRAINING_NOT_EXISTS);
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetPage() {
       // mock 数据
       WebsiteTrainingDO db = randomPojo(WebsiteTrainingDO.class, o -> { // 等会查询到
       });
       websiteTrainingMapper.insert(db);
       // 准备参数
       WebsiteTrainingPageReqVO reqVO = new WebsiteTrainingPageReqVO();

       // 调用
       PageResult<WebsiteTrainingDO> pageResult = websiteTrainingService.getPage(reqVO);
       // 断言
       assertEquals(1, pageResult.getTotal());
       assertEquals(1, pageResult.getList().size());
       assertPojoEquals(db, pageResult.getList().get(0));
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetList() {
       // mock 数据
       WebsiteTrainingDO db = randomPojo(WebsiteTrainingDO.class, o -> { // 等会查询到
       });
       websiteTrainingMapper.insert(db);
       // 准备参数
       WebsiteTrainingExportReqVO reqVO = new WebsiteTrainingExportReqVO();

       // 调用
       List<WebsiteTrainingDO> list = websiteTrainingService.getList(reqVO);
       // 断言
       assertEquals(1, list.size());
       assertPojoEquals(db, list.get(0));
    }

}
