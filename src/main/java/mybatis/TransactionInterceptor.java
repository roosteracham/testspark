package mybatis;

public class TransactionInterceptor implements Interceptor {
    @Override
    public Object intercept(Invocation invocation) throws Exception {
        System.out.println("------插入后置处理代码-------------");
        return null;
    }

    @Override
    public <T> T  plugin(Object target) {
        return null;
    }
}