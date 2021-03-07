package spring.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class TestAspect {

    @Pointcut("execution(* spring.aop.Test.test(..))")
    public void test2() {
    }

    @Pointcut("execution(* spring.aop.Test.test(..))")
    public void test() {
    }

    @Before("test()")
    public void testBefore() {
        System.out.println("testBefore");
    }

    @Before("test2()")
    public void testBefore2() {
        System.out.println("testBefore2");
    }
}
