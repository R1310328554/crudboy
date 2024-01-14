//package com.testlk.util;
//
//import com.alibaba.fastjson.JSONObject;
////import com.carshow.data.base.mapper.BaseMapper;
////import com.carshow.data.base.service.IBaseService;
////import com.carshow.data.common.contant.ResultBody;
////import com.carshow.data.ftp.FtpUtil;
//import org.apache.commons.net.ftp.FTP;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Primary;
//import org.springframework.stereotype.Repository;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//import org.springframework.web.multipart.MultipartFile;
//import org.springframework.web.multipart.MultipartHttpServletRequest;
//import org.springframework.web.multipart.commons.CommonsMultipartResolver;
//
//import javax.servlet.http.HttpServletRequest;
//import java.io.IOException;
//import java.io.InputStream;
//import java.lang.reflect.ParameterizedType;
//import java.math.BigInteger;
//import java.text.SimpleDateFormat;
//import java.util.*;
//
///**
// * @Author xw
// * @Description 公用实现类
// * @Date 2021/1/11  19:13
// */
//@Service
//@Transactional//开启声明式事务注解，为该类添加事务支持。
//public abstract class BaseServiceImpl<T, ID, EXAMPLE> implements IBaseService<T, ID, EXAMPLE> {
//
//    // 定义抽象方法getMyMapper获取当前实体Mapper对象
//    protected abstract BaseMapper<T> getBaseMapper();
//
//    private Class<T> modelClass;//当前泛型的真实类型Class
//    public BaseServiceImpl() {
//        ParameterizedType pt = (ParameterizedType) this.getClass().getGenericSuperclass();
//        modelClass = (Class<T>) pt.getActualTypeArguments()[0];
//    }
//
//    @Override
//    @Transactional(rollbackFor = Exception.class)
//    public List<T> selectAll() {
//        return getBaseMapper().selectAll();
//    }
//
//    @Override
//    @Transactional(rollbackFor = Exception.class)
//    public T selectById(String id) {
//        return getBaseMapper().selectByPrimaryKey(id);
//    }
//
//    @Override
//    @Transactional(rollbackFor = Exception.class)
//    public int save(T record) {
//        int res = getBaseMapper().insert(record);
//        return res;
//    }
//
//    @Override
//    @Transactional(rollbackFor = Exception.class)
//    public int saveSelective(T record) {
//        int res = getBaseMapper().insertSelective(record);
//        return res;
//    }
//
////    @Override
////    @Transactional(rollbackFor = Exception.class)
////    public int saveList(java.util.List<? extends T> list) {
////        int res = getBaseMapper().insertList(list);
////        return res;
////    }
//
//    @Override
//    @Transactional(rollbackFor = Exception.class)
//    public int delete(T record) {
//        int res = getBaseMapper().delete(record);
//        return res;
//    }
//
//    @Override
//    @Transactional(rollbackFor = Exception.class)
//    public int deleteByExample(T record, EXAMPLE example) {
//        int res = getBaseMapper().deleteByExample(example);
//        return res;
//    }
//
//    @Override
//    @Transactional(rollbackFor = Exception.class)
//    public int deleteByPrimaryKey(ID key) {
//        int res = getBaseMapper().deleteByPrimaryKey(key);
//        return res;
//    }
//
//    @Override
//    @Transactional(rollbackFor = Exception.class)
//    public int updateByExample(T record, EXAMPLE example) {
//        int res = getBaseMapper().updateByExample(record, example);
//        return res;
//    }
//
//    @Override
//    @Transactional(rollbackFor = Exception.class)
//    public int updateByExampleSelective(T record, EXAMPLE example) {
//        int res = getBaseMapper().updateByExampleSelective(record, example);
//        return res;
//    }
//
//    @Override
//    @Transactional(rollbackFor = Exception.class)
//    public int updateByPrimaryKey(T record) {
//        int res = getBaseMapper().updateByPrimaryKey(record);
//        return res;
//    }
//
//    @Override
//    @Transactional(rollbackFor = Exception.class)
//    public int updateByPrimaryKeySelective(T record) {
//        int res = getBaseMapper().updateByPrimaryKeySelective(record);
//        return res;
//    }
//}
//
