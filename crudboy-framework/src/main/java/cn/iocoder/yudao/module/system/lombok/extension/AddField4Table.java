package cn.iocoder.yudao.module.system.lombok.extension;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.SOURCE)
public @interface AddField4Table {

    String fields() default "id-Integer, name-Varchar(64)";
    boolean overrideSameNameField() default false;
}