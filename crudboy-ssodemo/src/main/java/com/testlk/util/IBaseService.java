package com.testlk.util;


//import com.carshow.data.common.contant.ResultBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface IBaseService<T, ID, EXAMPLE> {

    List<T> selectAll();

    T selectById(String id);

    /**
     * 单个实体对象全量字段保存
     *
     * @param record
     * @return
     */
    int save(T record);

    /**
     * 单个实体对象非空字段保存
     *
     * @param record
     * @return
     */
    int saveSelective(T record);

    /**
     * 多个实体对象保存
     *
     * @param list
     * @return
     */
//    int saveList(java.util.List<? extends T> list);

    /**
     * 单个实体对象删除
     *
     * @param record
     * @return
     */
    int delete(T record);

    /**
     * 单个实体对象条件删除
     *
     * @param record
     * @param example
     * @return
     */
    int deleteByExample(T record, EXAMPLE example);

    /**
     * 单个实体对象主键删除
     *
     * @param key
     * @return
     */
    int deleteByPrimaryKey(ID key);

    /**
     * 单个实体对象条件全量字段更新
     *
     * @param record
     * @param example
     * @return
     */
    int updateByExample(T record, EXAMPLE example);

    /**
     * 单个实体对象条件非空字段更新
     *
     * @param record
     * @param example
     * @return
     */
    int updateByExampleSelective(T record, EXAMPLE example);

    /**
     * 单个实体对象主键全量字段更新
     *
     * @param record
     * @return
     */
    int updateByPrimaryKey(T record);

    /**
     * 单个实体对象主键非空字段更新
     *
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(T record);

}

