package demo.annotation;

import javax.annotation.Resource;
import java.lang.reflect.Method;

/**
 * Created by zn on 2019/5/17.
 */
public class MyTargetTest {

    @MyTarget
    public void doSomething() { }

    @Resource
    public static void main(String[] args) throws Exception {
        Method method = MyTargetTest.class.getMethod("doSomething", null);
        if (method.isAnnotationPresent(MyTarget.class)) {
            System.out.println(method.getAnnotation(MyTarget.class));
        }
    }

}
