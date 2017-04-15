package com.czm.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * Created by mac on 17/4/9.
 * 让切面类实现org.springframework.core.Ordered接口：实现该接口只需要实现一个int getOrder()方法，该方法返回值越小，优先级越高
 *直接使用@Order注解来修饰一个切面类：使用这个注解时可以配置一个int类型的value属性，该属性值越小，优先级越高
 */
@Order
@Aspect
@Configuration
public class TestAop implements Ordered {
    @Pointcut("execution(* com.czm.api.ApiTest.*(..))")
    public void pointcut(){}

    /*
    * 通过连接点切入
    */
    @Before("execution(* com.czm.api.ApiTest.*(..))&&args(s)")//既可以写切入点也可以写切入表达式
    public void before1(String s){
        System.err.println ("切面before执行了。。。。"+s);
    }
    /*
 * 通过连接点切入
 */
    @Before("pointcut()")
    public void before2(JoinPoint point){
        System.out.println("@Before：目标方法为：" +
                point.getSignature().getDeclaringTypeName() +
                "." + point.getSignature().getName());
        System.out.println("@Before：参数为：" + Arrays.toString(point.getArgs()));
        System.out.println("@Before：被织入的目标对象为：" + point.getTarget());
    }
    @After("pointcut()")
    public void releaseResource(JoinPoint point) {
        System.out.println("@After：模拟释放资源...");
    }
    @AfterReturning(value = "pointcut()",returning="returnValue")
    public void log(JoinPoint point, Object returnValue) {
        System.out.println("@AfterReturning：返回值为：" + returnValue);
        System.out.println("@AfterReturning：被织入的目标对象为：" + point.getTarget());
    }
    @Around("pointcut()")
    public Object testaround(ProceedingJoinPoint pjp){
        MethodSignature signature = (MethodSignature) pjp.getSignature();
        Method method = signature.getMethod(); //获取被拦截的方法
        String methodName = method.getName(); //获取被拦截的方法名
        Object[] args = pjp.getArgs();
        Object result=null;
        try {
            result = pjp.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        return result;
    }


    @Override
    public int getOrder() {
        return 1;
    }
}
