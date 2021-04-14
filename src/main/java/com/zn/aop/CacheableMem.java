package com.zn.aop;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE})
public @interface CacheableMem {

    /**
     * 缓存key
     * @return
     */
    String key() default "";

    /**
     * 过期时间 秒
     * @return
     */
	int expiredTime() default 0;
}
