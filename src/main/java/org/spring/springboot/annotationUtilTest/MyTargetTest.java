package org.spring.springboot.annotationUtilTest;

import org.spring.springboot.annotation.MyTarget;

import java.lang.reflect.Method;

/**
 * Created by 殷效恩 on 2017/12/27.
 */
public class MyTargetTest {
    @MyTarget
    public void doSomething()
    {
        System.out.println("hello world");
    }

    public static void main(String[] args) throws Exception
    {
        Method method = MyTargetTest.class.getMethod("doSomething",null);
        if(method.isAnnotationPresent(MyTarget.class))//如果doSomething方法上存在注解@MyTarget，则为true
        {
            System.out.println("success");
            System.out.println(method.getAnnotation(MyTarget.class));
        }
    }
}
