package com.luokup.util;

import lombok.Data;

@Data
public class ExcelEntity {
    /**
     * 字段名
     */
    private String filedName;
    /**
     * 字段类型
     */
    private String filedType;
    /**
     * 字段注释
     */
    private String notes;
    /**
     * 字段属性
     */
    private String attribute;
    /**
     * 字段索引
     */
    private String index;


    private Boolean required;

    private String unit;

    private String example;
}