package mybatis;

public class UserInterceptor implements Interceptor {

    @Override
    public <T> T  plugin(Object target) {
        return TargetProxy.wrap(target, this);
    }

    @Override
    public Object intercept(Invocation invocation) throws Exception{
        System.out.println("before");
        Object res = invocation.process();
        System.out.println("after");
        return res;
    }
}
