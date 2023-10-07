package cn.iocoder.yudao.module.system.lombok.extension;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.SOURCE)
public @interface CreateUpdateFieldByAndTime {

    boolean callSuper() default false;
    String createByFieldType() default "Varchar(64)";
    String updateByFieldType() default "Varchar(64)";
    boolean overrideSameNameField() default false;

}
