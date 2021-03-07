package think.in.java.reflect.proxy.dynamic;

import think.in.java.reflect.proxy.ITest;
import think.in.java.reflect.proxy.Test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class MyDynamicProxy implements InvocationHandler {

    private Object target;

    public MyDynamicProxy(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("before");
        Object res = method.invoke(target, args);
        System.out.println("after");
        return res;
    }

    public static void main(String[] args) {
        Test test = new Test();
        MyDynamicProxy myDynamicProxy = new MyDynamicProxy(test);
        ITest proxy = (ITest)Proxy.newProxyInstance(Test.class.getClassLoader(), test.getClass().getInterfaces(), myDynamicProxy);
        proxy.test();
    }
}
