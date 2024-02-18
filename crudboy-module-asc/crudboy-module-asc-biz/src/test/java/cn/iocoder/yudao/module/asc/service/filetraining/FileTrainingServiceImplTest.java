package cn.iocoder.yudao.module.asc.service.filetraining;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

import javax.annotation.Resource;

import cn.iocoder.yudao.framework.test.core.ut.BaseDbUnitTest;

import cn.iocoder.yudao.module.asc.controller.admin.filetraining.vo.*;
import cn.iocoder.yudao.module.asc.dal.dataobject.filetraining.FileTrainingDO;
import cn.iocoder.yudao.module.asc.dal.mysql.filetraining.FileTrainingMapper;
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
 * {@link FileTrainingServiceImpl} 的单元测试类
 *
 * @author 超级管理员
 */
@Import(FileTrainingServiceImpl.class)
public class FileTrainingServiceImplTest extends BaseDbUnitTest {

    @Resource
    private FileTrainingServiceImpl fileTrainingService;

    @Resource
    private FileTrainingMapper fileTrainingMapper;

    @Test
    public void testCreate_success() {
        // 准备参数
        FileTrainingCreateReqVO reqVO = randomPojo(FileTrainingCreateReqVO.class);

        // 调用
        Long fileTrainingId = fileTrainingService.create(reqVO);
        // 断言
        assertNotNull(fileTrainingId);
        // 校验记录的属性是否正确
        FileTrainingDO fileTraining = fileTrainingMapper.selectById(fileTrainingId);
        assertPojoEquals(reqVO, fileTraining);
    }

    @Test
    public void testUpdate_success() {
        // mock 数据
        FileTrainingDO db = randomPojo(FileTrainingDO.class);
        fileTrainingMapper.insert(db);// @Sql: 先插入出一条存在的数据
        // 准备参数
        FileTrainingUpdateReqVO reqVO = randomPojo(FileTrainingUpdateReqVO.class, o -> {
            o.setId(db.getId()); // 设置更新的 ID
        });

        // 调用
        fileTrainingService.update(reqVO);
        // 校验是否更新正确
        FileTrainingDO fileTraining = fileTrainingMapper.selectById(reqVO.getId()); // 获取最新的
        assertPojoEquals(reqVO, fileTraining);
    }

    @Test
    public void testUpdate_notExists() {
        // 准备参数
        FileTrainingUpdateReqVO reqVO = randomPojo(FileTrainingUpdateReqVO.class);

        // 调用, 并断言异常
        assertServiceException(() -> fileTrainingService.update(reqVO), FILE_TRAINING_NOT_EXISTS);
    }

    @Test
    public void testDelete_success() {
        // mock 数据
        FileTrainingDO db = randomPojo(FileTrainingDO.class);
        fileTrainingMapper.insert(db);// @Sql: 先插入出一条存在的数据
        // 准备参数
        Long id = db.getId();

        // 调用
        fileTrainingService.delete(id);
       // 校验数据不存在了
       assertNull(fileTrainingMapper.selectById(id));
    }

    @Test
    public void testDelete_notExists() {
        // 准备参数
        Long id = randomLongId();

        // 调用, 并断言异常
        assertServiceException(() -> fileTrainingService.delete(id), FILE_TRAINING_NOT_EXISTS);
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetPage() {
       // mock 数据
       FileTrainingDO db = randomPojo(FileTrainingDO.class, o -> { // 等会查询到
       });
       fileTrainingMapper.insert(db);
       // 准备参数
       FileTrainingPageReqVO reqVO = new FileTrainingPageReqVO();

       // 调用
       PageResult<FileTrainingDO> pageResult = fileTrainingService.getPage(reqVO);
       // 断言
       assertEquals(1, pageResult.getTotal());
       assertEquals(1, pageResult.getList().size());
       assertPojoEquals(db, pageResult.getList().get(0));
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetList() {
       // mock 数据
       FileTrainingDO db = randomPojo(FileTrainingDO.class, o -> { // 等会查询到
       });
       fileTrainingMapper.insert(db);
       // 准备参数
       FileTrainingExportReqVO reqVO = new FileTrainingExportReqVO();

       // 调用
       List<FileTrainingDO> list = fileTrainingService.getList(reqVO);
       // 断言
       assertEquals(1, list.size());
       assertPojoEquals(db, list.get(0));
    }

}
