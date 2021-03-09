package mybatis;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;

public class UserDaoProxy implements InvocationHandler {

    private Object target;

    private List<Interceptor> interceptorList;

    public UserDaoProxy(Object target, List<Interceptor> interceptorList) {
        this.target = target;
        this.interceptorList = interceptorList;
    }


    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        for (Interceptor interceptor : interceptorList) {
            interceptor.intercept();
        }
        return method.invoke(target, args);
    }

    public <T> T getProxy() {
        return (T) Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), this);
    }

    public static void main(String[] args) {
        UserDao userDao = new UserDao();
        List<Interceptor> interceptors = new ArrayList<>();
        interceptors.add(new LogInterceptor());
        interceptors.add(new TransactionInterceptor());
        UserDaoProxy proxy = new UserDaoProxy(userDao, interceptors);
        IUserDao userDaoProxy = proxy.getProxy();
        System.out.println(userDaoProxy.getUser());
    }
}
