package com.zn.bean;

import org.apache.log4j.Logger;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;


/**
 * <从Spring IOC容器中获取Bean对象> <功能详细描述>
 * @author wzh
 * @version 2018-09-23 19:32
 * @see [相关类/方法] (可选)
 **/
@Component
public class BeanHeader implements ApplicationContextAware, BeanPostProcessor
{

    private static Logger log = Logger.getLogger(BeanHeader.class);

    // 上下文对象
    private static ApplicationContext applicationContext;

    /**
     * 实现ApplicationContextAware接口的回调方法，注入上下文对象
     * @param applicationContext
     * @throws BeansException
     */
    @Override
    public void setApplicationContext(ApplicationContext applicationContext)
        throws BeansException
    {
        BeanHeader.applicationContext = applicationContext;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName)
            throws BeansException {

//        DefaultListableBeanFactory defaultListableBeanFactory = (DefaultListableBeanFactory) applicationContext.getAutowireCapableBeanFactory();
////创建bean信息.
//        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(TestService.class);
//        beanDefinitionBuilder.addPropertyValue("name","张三");
////动态注册bean.
//        defaultListableBeanFactory.registerBeanDefinition("testService", beanDefinitionBuilder.getBeanDefinition());
////获取动态注册的bean.
//        TestService testService =applicationContext.getBean(TestService.class);
//        testService.print();


//        System.out.println("对象" + beanName + "实例化完成");
//
//        System.out.println("------------------------------");

        return bean;

    }


    @PostConstruct
    public void init(){

        DefaultListableBeanFactory defaultListableBeanFactory = (DefaultListableBeanFactory) applicationContext.getAutowireCapableBeanFactory();
//创建bean信息.
        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(TestService.class);
        beanDefinitionBuilder.addPropertyValue("name","张三");
//动态注册bean.
        defaultListableBeanFactory.registerBeanDefinition("testService", beanDefinitionBuilder.getBeanDefinition());
//获取动态注册的bean.
        TestService testService =applicationContext.getBean(TestService.class);
        testService.print();
        System.out.println("spring init");

        defaultListableBeanFactory.removeBeanDefinition("testService");

    }


    /**
     * 获取上下文对象
     * @return applicationContext
     */
    public static ApplicationContext getApplicationContext()
    {
        return applicationContext;
    }

    /**
     * 判断上下文对象是否为空
     *
     * @return
     */
    public static boolean checkapplicationContext()
    {
        boolean flag = getApplicationContext() != null;
        if (!flag)
        {
            log.error("applicaitonContext未注入,实现ApplicationContextAware的类必须被spring管理");
        }
        return flag;
    }

    /**
     * 根据name获取bean
     * @param name
     * @param <T>
     * @return
     */
    public static <T> T getBean(String name)
    {
        if (checkapplicationContext())
        {
            return (T)getApplicationContext().getBean(name);
        }
        else
        {
            return null;
        }
    }

    /**
     * 根据class 获取bean
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T getBean(Class<T> clazz)
    {
        if (checkapplicationContext())
        {
            return getApplicationContext().getBean(clazz);
        }
        else
        {
            return null;
        }
    }

    /**
     * 根据name,以及Clazz返回指定的Bean
     * @param name
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T getBean(String name, Class<T> clazz)
    {
        if (checkapplicationContext())
        {
            return getApplicationContext().getBean(name, clazz);
        }
        else
        {
            return null;
        }
    }
}

