//package com.testlk.util;
//
//import com.carshow.data.base.service.IBaseService;
//import io.swagger.annotations.ApiOperation;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//
//import javax.annotation.Resource;
//
//
///**
// * @Author xw
// * @Description 实现泛型父类
// * @Date 2021/4/13  10:49
// */
//public class BaseController<T, K> extends AbstractController<T, K> {
//
//    @Autowired
//    private IBaseService<T, K,Object> baseService;
//
//    @PostMapping("/addJson")
////    @ApiOperation(value = "新增",notes = "公用方法")
//    @Override
//    public int addJson(@RequestBody T t) {
//        return baseService.save(t);
//    }
//
//    @PostMapping("/updateJson")
////    @ApiOperation(value = "修改",notes = "公用方法")
//    @Override
//    public int updateJson(@RequestBody T t) {
//        return baseService.updateByPrimaryKeySelective(t);
//    }
//
//    @GetMapping("/delJson")
////    @ApiOperation(value = "删除",notes = "公用方法")
//    @Override
//    public int delJson(K id) {
//        return baseService.deleteByPrimaryKey(id);
//    }
//}
//
