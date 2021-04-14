package com.zn.bean;


import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

//注：这里的名称fooA，仅仅只是为了后面演示时看得更清楚，非必需
@Component("fooA")
public class FooA implements IFoo , BeanPostProcessor {

    public FooA() {
        System.out.println("FooA is created!");
    }

    public void foo() {
        System.out.println("FooA.foo()");
    }

    public void destroy() throws Exception {
        System.out.println("FooA.destroy()");

    }


}


