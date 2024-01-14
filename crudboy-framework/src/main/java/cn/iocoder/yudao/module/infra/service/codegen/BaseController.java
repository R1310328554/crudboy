package cn.iocoder.yudao.module.infra.service.codegen;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.operatelog.core.annotations.OperateLog;
import cn.iocoder.yudao.module.system.dal.dataobject.permission.MenuDO;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.PageDTO;
import com.baomidou.mybatisplus.extension.service.IService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static cn.iocoder.yudao.framework.operatelog.core.enums.OperateTypeEnum.EXPORT;

public class BaseController<S extends IService<E>, E> {

    @Autowired
    protected S baseService;

//    @LogOperation
//    @ApiOperation(value = "[通用方法]通过主键获取一条对应实体类的数据库记录")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "id", value = "实体主键", dataTypeClass = Integer.class, required = true)
//    })
    @GetMapping("{id}")
    public CommonResult<E> getById(@PathVariable("id") Integer id) throws Exception {
        E entity = baseService.getById(id);
        return CommonResult.success(entity);
    }

//    @LogOperation
//    @ApiOperation(value = "[通用方法]插入一条对应实体类的数据库记录")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "entity", value = "实体对象", dataTypeClass = Object.class, required = true)
//    })
    @PostMapping
    public CommonResult<String> save(@RequestBody E entity) throws Exception {
        baseService.save(entity);
        return CommonResult.success("添加成功");
    }


//    @LogOperation
//    @ApiOperation(value = "[通用方法]更新一条对应实体类的数据库记录")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "entity", value = "实体对象", dataTypeClass = Object.class, required = true)
//    })
    @PutMapping
    public CommonResult<String> updateById(@RequestBody E entity) throws Exception {
        baseService.updateById(entity);
        return CommonResult.success("更新成功");
    }


//    @LogOperation
//    @ApiOperation(value = "[通用方法]通过主键删除一条对应实体类的数据库记录")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "id", value = "实体主键", dataTypeClass = Integer.class, required = true)
//    })
    @DeleteMapping("{id}")
    public CommonResult<String> deleteById(@PathVariable("id") Integer id) throws Exception {
        baseService.removeById(id);
        return CommonResult.success("删除成功");
    }


    @GetMapping
    @Operation(summary = "获得${table.classComment}列表")
    @Parameter(name = "ids", description = "编号列表", required = true, example = "1024,2048")
    @PreAuthorize("@ss.hasPermission('${permissionPrefix}:query')")
    public CommonResult<List<E>> listByIds(@RequestParam("ids")Collection<Long> ids) {
        List<E> list = baseService.listByIds(ids);
        return CommonResult.success(list);
    }

//    @GetMapping
    @Operation(summary = "获得${table.classComment}分页")
    @PreAuthorize("@ss.hasPermission('${permissionPrefix}:query')")
    public CommonResult<IPage<E>> page(@Valid IPage<E> pageVO, Wrapper<E> queryWrapper) {
//        org.springframework.beans.BeanInstantiationException: Failed to instantiate [com.baomidou.mybatisplus.core.conditions.Wrapper]: Is it an abstract class?; nested exception is java.lang.InstantiationException
//        PageDTO<E> pageResult = baseService.page(pageVO);
//        IPage<MenuDO> menuDOIPage = baseService.getBaseMapper().selectPage(pageVO, queryWrapper);
//        PageResult<E> asd = null;
        IPage<E> pageResult = baseService.getBaseMapper().selectPage(pageVO, queryWrapper);
        return CommonResult.success(pageResult);
    }

    @GetMapping
    @Operation(summary = "导出${table.classComment} Excel")
    @PreAuthorize("@ss.hasPermission('${permissionPrefix}:export')")
    @OperateLog(type = EXPORT)
    public void exportExcel(@Valid E exportReqVO,
    HttpServletResponse response) throws IOException {
        Wrapper<E> w = new LambdaQueryWrapperX<>();
//        exportReqVO
        List<E> list = baseService.list(w);
        if (list == null) {
            return;
        }
        Class<E> entityClass = (Class) ((ParameterizedType) getClass()
                .getGenericSuperclass()).getActualTypeArguments()[1];

//        E e = list.get(0);
        // 导出 Excel
        List datas = list;
        ExcelUtils.write(response, "${table.classComment}.xls", "数据", entityClass, datas);
    }
}
