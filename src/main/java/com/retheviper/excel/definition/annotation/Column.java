package com.retheviper.excel.definition.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Mark field as cell in sheet.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Column {

    /**
     * Cell's name. If null or "", it will be considered as field's name.
     */
    String name() default "";

    /**
     * Cell's position, such as "A" or "AA". Necessary to input correct value.
     */
    String position() default "A";
}
