//package cn.iocoder.yudao.module.asc.dal.mysql.tenant;
//
//import java.util.*;
//
//import cn.iocoder.yudao.framework.common.pojo.PageResult;
//import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
//import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
//import cn.iocoder.yudao.module.asc.dal.dataobject.tenant.TenantDO;
//import org.apache.ibatis.annotations.Mapper;
//import cn.iocoder.yudao.module.asc.controller.admin.tenant.vo.*;
//
///**
// * 租户 Mapper
// *
// * @author 超级管理员
// */
//@Mapper
//public interface TenantMapper extends BaseMapperX<TenantDO> {
//
//    default PageResult<TenantDO> selectPage(TenantPageReqVO reqVO) {
//        return selectPage(reqVO, new LambdaQueryWrapperX<TenantDO>()
//                .likeIfPresent(TenantDO::getName, reqVO.getName())
//                .orderByDesc(TenantDO::getId));
//    }
//
//    default List<TenantDO> selectList(TenantExportReqVO reqVO) {
//        return selectList(new LambdaQueryWrapperX<TenantDO>()
//                .likeIfPresent(TenantDO::getName, reqVO.getName())
//                .orderByDesc(TenantDO::getId));
//    }
//
//}
