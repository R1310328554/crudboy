package com.luokup.util;

import lombok.Data;

import java.util.List;

@Data
public class TableEntity {
    /**
     * 字段名
     */
    private String tableName;
    /**
     * 字段类型
     */
    private String comment;
    /**
     * 字段类型
     */
    private String type;
    /**
     * 字段类型
     */
    private String dept;

    private List<ExcelEntity> fields;

    public int size() {
        return fields != null ? fields.size():0;
    }
}