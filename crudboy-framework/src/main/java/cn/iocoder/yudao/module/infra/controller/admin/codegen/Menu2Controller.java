package cn.iocoder.yudao.module.infra.controller.admin.codegen;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.util.MyBatisUtils;
import cn.iocoder.yudao.module.infra.service.codegen.BaseController;
import cn.iocoder.yudao.module.infra.service.codegen.BaseServiceImpl;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.convertSet;

@RestController
@RequestMapping("/infra/Menu2")
@Validated
public class Menu2Controller extends BaseController<MeAABaseService, DeptDO> {

    @GetMapping
//    public CommonResult<PageDTO<E>> page(@Valid PageDTO<E> pageVO) {
//    public CommonResult<DeptDO> getRecordPage(DeptDO pageReqVO, @RequestParam(required = false) PageParam pageParam) {
    public CommonResult<IPage<DeptDO>> page(DeptDO pageReqVO, @RequestParam(required = false) PageParam pageParam) {
        // 根据用户昵称查询出用户 ids
        Set<Long> userIds = null;
//        if (StringUtils.isNotBlank(nickname)) {
//            List<DeptDO> users = memberUserApi.getUserListByNickname(nickname);
//            // 如果查询用户结果为空直接返回无需继续查询
//            if (CollectionUtils.isEmpty(users)) {
//                return PageResult.empty();
//            }
//            userIds = convertSet(users, DeptDO::getId);
//        }
        // 执行查询

        if (pageParam == null) {
            pageParam = new PageParam();
        }

        IPage<DeptDO> mpPage = MyBatisUtils.buildPage(pageParam);
        LambdaQueryWrapperX<DeptDO> queryWrapper = new LambdaQueryWrapperX<DeptDO>()
//        PageParam pageParam = new PageParam();
//                .inIfPresent(DeptDO::getParentId, userIds)
//                likeIfPresent
                .eqIfPresent(DeptDO::getName, pageReqVO.getName())
                .eqIfPresent(DeptDO::getStatus, pageReqVO.getStatus())
                .orderByDesc(DeptDO::getId);
//        return baseService.getBaseMapper().selectPage(pageParam, queryWrapper);
//        IPage<DeptDO> menuDOIPage = baseService.getBaseMapper().selectPage(mpPage, queryWrapper);
//        return CommonResult.success(menuDOIPage);
        return super.page(mpPage, queryWrapper);
    }

}

@Component
class MeAABaseService extends BaseServiceImpl<MenuDOBaseMapper, DeptDO> {

}

@Mapper
interface MenuDOBaseMapper extends BaseMapperX<DeptDO> {

}

