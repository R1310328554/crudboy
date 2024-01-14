//package com.testlk.util;
//
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import javax.annotation.Resource;
//
//@RestController
//@RequestMapping(Common.PATH_URL+"/user")
////Api注解，描述信息 可通过tag进行分类
//@Api(value = "UserController", description = "关于用户管理信息接口")
//public class UserController extends BaseController<TUser,String> {
//    @Autowired
//    private IUserService userService;
//  }
//
//
//
//
//public interface IUserService extends IBaseService<TUser,String,Object> {}
//
//
//
///**
// * @Author xw
// * @Description 用户实现类
// * @Date 2021/4/9  14:08
// */
//@Slf4j
//@Service
//public class UserServiceImpl  extends BaseServiceImpl<TUser,String,Object> implements IUserService {
//
//    @Resource
//    private UserMapper userMapper;
//    @Override
//    protected BaseMapper<TUser> getBaseMapper() {
//        return userMapper;
//    }
//  }
//
//
//@Mapper
//public interface UserMapper extends BaseMapper<TUser> {}
//
