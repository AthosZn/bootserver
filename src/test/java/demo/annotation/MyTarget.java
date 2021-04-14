package demo.annotation;


import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
public @interface MyTarget {
}

//public enum RetentionPolicy {
//    SOURCE,
//    CLASS,
//    RUNTIME
//}
//
//可以发现这个枚举类定义了三个值，这三个值分别代表的是我们定义的MyTarget如何保持。
//用@Retention(RetentionPolicy.CLASS)修饰的注解，表示注解的信息被保留在class文件(字节码文件)中当程序编译时，但不会被虚拟机读取在运行的时候；
//用@Retention(RetentionPolicy.SOURCE )修饰的注解,表示注解的信息会被编译器抛弃，不会留在class文件中，注解的信息只会留在源文件中；
//用@Retention(RetentionPolicy.RUNTIME )修饰的注解，表示注解的信息被保留在class文件(字节码文件)中当程序编译时，会被虚拟机保留在运行时，
//---------------------
//作者：wangpengzhi19891223
//来源：CSDN
//原文：https://blog.csdn.net/wangpengzhi19891223/article/details/78131137/
//版权声明：本文为博主原创文章，转载请附上博文链接！