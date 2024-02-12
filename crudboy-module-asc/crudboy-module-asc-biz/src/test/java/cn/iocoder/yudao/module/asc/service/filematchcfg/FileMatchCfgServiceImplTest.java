package cn.iocoder.yudao.module.asc.service.filematchcfg;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

import javax.annotation.Resource;

import cn.iocoder.yudao.framework.test.core.ut.BaseDbUnitTest;

import cn.iocoder.yudao.module.asc.controller.admin.filematchcfg.vo.*;
import cn.iocoder.yudao.module.asc.dal.dataobject.filematchcfg.FileMatchCfgDO;
import cn.iocoder.yudao.module.asc.dal.mysql.filematchcfg.FileMatchCfgMapper;
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
 * {@link FileMatchCfgServiceImpl} 的单元测试类
 *
 * @author 超级管理员
 */
@Import(FileMatchCfgServiceImpl.class)
public class FileMatchCfgServiceImplTest extends BaseDbUnitTest {

    @Resource
    private FileMatchCfgServiceImpl fileMatchCfgService;

    @Resource
    private FileMatchCfgMapper fileMatchCfgMapper;

    @Test
    public void testCreate_success() {
        // 准备参数
        FileMatchCfgCreateReqVO reqVO = randomPojo(FileMatchCfgCreateReqVO.class);

        // 调用
        Long fileMatchCfgId = fileMatchCfgService.create(reqVO);
        // 断言
        assertNotNull(fileMatchCfgId);
        // 校验记录的属性是否正确
        FileMatchCfgDO fileMatchCfg = fileMatchCfgMapper.selectById(fileMatchCfgId);
        assertPojoEquals(reqVO, fileMatchCfg);
    }

    @Test
    public void testUpdate_success() {
        // mock 数据
        FileMatchCfgDO db = randomPojo(FileMatchCfgDO.class);
        fileMatchCfgMapper.insert(db);// @Sql: 先插入出一条存在的数据
        // 准备参数
        FileMatchCfgUpdateReqVO reqVO = randomPojo(FileMatchCfgUpdateReqVO.class, o -> {
            o.setId(db.getId()); // 设置更新的 ID
        });

        // 调用
        fileMatchCfgService.update(reqVO);
        // 校验是否更新正确
        FileMatchCfgDO fileMatchCfg = fileMatchCfgMapper.selectById(reqVO.getId()); // 获取最新的
        assertPojoEquals(reqVO, fileMatchCfg);
    }

    @Test
    public void testUpdate_notExists() {
        // 准备参数
        FileMatchCfgUpdateReqVO reqVO = randomPojo(FileMatchCfgUpdateReqVO.class);

        // 调用, 并断言异常
        assertServiceException(() -> fileMatchCfgService.update(reqVO), FILE_MATCH_CFG_NOT_EXISTS);
    }

    @Test
    public void testDelete_success() {
        // mock 数据
        FileMatchCfgDO db = randomPojo(FileMatchCfgDO.class);
        fileMatchCfgMapper.insert(db);// @Sql: 先插入出一条存在的数据
        // 准备参数
        Long id = db.getId();

        // 调用
        fileMatchCfgService.delete(id);
       // 校验数据不存在了
       assertNull(fileMatchCfgMapper.selectById(id));
    }

    @Test
    public void testDelete_notExists() {
        // 准备参数
        Long id = randomLongId();

        // 调用, 并断言异常
        assertServiceException(() -> fileMatchCfgService.delete(id), FILE_MATCH_CFG_NOT_EXISTS);
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetPage() {
       // mock 数据
       FileMatchCfgDO db = randomPojo(FileMatchCfgDO.class, o -> { // 等会查询到
           o.setName(null);
       });
       fileMatchCfgMapper.insert(db);
       // 测试 name 不匹配
       fileMatchCfgMapper.insert(cloneIgnoreId(db, o -> o.setName(null)));
       // 准备参数
       FileMatchCfgPageReqVO reqVO = new FileMatchCfgPageReqVO();
       reqVO.setName(null);

       // 调用
       PageResult<FileMatchCfgDO> pageResult = fileMatchCfgService.getPage(reqVO);
       // 断言
       assertEquals(1, pageResult.getTotal());
       assertEquals(1, pageResult.getList().size());
       assertPojoEquals(db, pageResult.getList().get(0));
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetList() {
       // mock 数据
       FileMatchCfgDO db = randomPojo(FileMatchCfgDO.class, o -> { // 等会查询到
           o.setName(null);
       });
       fileMatchCfgMapper.insert(db);
       // 测试 name 不匹配
       fileMatchCfgMapper.insert(cloneIgnoreId(db, o -> o.setName(null)));
       // 准备参数
       FileMatchCfgExportReqVO reqVO = new FileMatchCfgExportReqVO();
       reqVO.setName(null);

       // 调用
       List<FileMatchCfgDO> list = fileMatchCfgService.getList(reqVO);
       // 断言
       assertEquals(1, list.size());
       assertPojoEquals(db, list.get(0));
    }

}
