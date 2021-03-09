package mybatis;

import java.lang.reflect.Proxy;

public class TargetProxy {

    public static <T> T wrap(Object target, Interceptor interceptor) {
        return (T) Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), new UserDaoProxy(target, interceptor));
    }
}
