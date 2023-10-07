package cn.iocoder.yudao.framework.excel.core.util;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.read.listener.ReadListener;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;

// 有个很重要的点 UserImportExcelVOListener 不能被spring管理，要每次读取excel都要new,然后里面用到spring可以构造方法传进去
@Slf4j
public class DynamicExcelDataListener implements ReadListener {

    private Class excelVoClass;

    public DynamicExcelDataListener(Class excelVoClass) {
        this.excelVoClass = excelVoClass;
    }

    @Override
    public void invoke(Object o, AnalysisContext analysisContext) {
//                System.out.println("o = " + o + ", analysisContext = " + analysisContext);
        Field[] declaredFields = excelVoClass.getDeclaredFields();
        try {
            for (int i = 0; i < declaredFields.length; i++) {
                Field declaredField = declaredFields[i];
                Class<?> type = declaredField.getType();
                if (type.isAssignableFrom(String.class)) {
                    declaredField.setAccessible(true);
                    if (declaredField.get(o) == null) {
                        // null 值改为空值，不然无法导入！
//                                System.out.println("declaredField = " + declaredField);
                        declaredField.set(o, "");
                    }
                }
            }
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
//                System.out.println("analysisContext = " + analysisContext);
    }
}