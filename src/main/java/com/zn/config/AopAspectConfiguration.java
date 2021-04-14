package com.zn.config;

import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;


//即使用jdk默认代理模式，AspectJ代理模式是CGLIB代理模式
//如果目标对象实现了接口，默认情况下会采用JDK的动态代理实现AOP
//如果目标对象实现了接口，可以强制使用CGLIB实现AOP (此例子我们就是强制使用cglib实现aop)
//如果目标对象没有实现了接口，必须采用CGLIB库，spring会自动在JDK动态代理和CGLIB之间转换

//用于定义配置类，可替换xml配置文件
@Configuration
//开启AspectJ 自动代理模式,如果不填proxyTargetClass=true，默认为false，
@EnableAspectJAutoProxy(proxyTargetClass=true)
//扫描注入类
@ComponentScan(basePackages = "com.zn.*")
@Component
@Aspect
public class AopAspectConfiguration {

}
