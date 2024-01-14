package com.testlk.util;

/**
 * @Author xw
 * @Description 声明泛型基类
 * @Date 2021/4/13  10:41
 */
public abstract  class AbstractController<T, K> {

    /**
     * 新增
     * @param t
     * @return
     */
    public abstract int addJson(T t);

    /**
     * 修改
     * @param t
     * @return
     */
    public abstract int updateJson(T t);

    /**
     * 单个实体对象主键删除
     * @param
     * @return
     */
    public abstract int delJson(K id);
}

