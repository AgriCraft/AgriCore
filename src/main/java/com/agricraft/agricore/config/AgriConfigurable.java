package com.agricraft.agricore.config;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface AgriConfigurable {

    AgriConfigCategory category();

    String key();

    String comment();

    String min() default "0";

    String max() default "1";

}
