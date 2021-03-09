package mybatis;

public class LogInterceptor implements Interceptor {
    @Override
    public <T> T plugin(Object target) {
        return TargetProxy.wrap(target, this);
    }

    @Override
    public Object intercept(Invocation invocation) throws Exception {
        System.out.println("------插入前置通知代码-------------");
        return invocation.process();
    }
}