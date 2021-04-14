package com.zn.bean;


public class FooB implements IFoo {

    public FooB() {
        System.out.println("FooB is created!");
    }

    public void foo() {
        System.out.println("FooB.foo()");
    }

    public void destroy() throws Exception {
        System.out.println("FooB.destroy()");
    }
}
