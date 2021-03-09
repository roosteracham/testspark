package mybatis;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;

public class UserDaoProxy implements InvocationHandler {

    private Object target;

    private Interceptor interceptorList;

    public UserDaoProxy(Object target, Interceptor interceptorList) {
        this.target = target;
        this.interceptorList = interceptorList;
    }


    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Invocation invocation = new Invocation(target, method, args);
        return interceptorList.intercept(invocation);
    }

    public static void main(String[] args) {
        UserDao userDao = new UserDao();
        UserInterceptor interceptor = new UserInterceptor();
        LogInterceptor logInterceptor = new LogInterceptor();
        InterceptorChain chain = new InterceptorChain();
        chain.addInterceptor(interceptor);
        chain.addInterceptor(logInterceptor);
        IUserDao userDaoProxy = chain.pluginAll(userDao);
        System.out.println(userDaoProxy.getUser());
    }
}
