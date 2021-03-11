package mybatis;

import org.apache.commons.lang.reflect.FieldUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.session.ResultHandler;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.Statement;
import java.util.Properties;

@Intercepts({@Signature(type = StatementHandler.class, method = "prepare", args = {Connection.class, Integer.class})})
public class SelectOneInterceptor implements Interceptor {
    @Override
    public Object intercept(Invocation invocation) throws Throwable {

        StatementHandler target = (StatementHandler) invocation.getTarget();
        BoundSql boundSql = target.getBoundSql();
        String sql = boundSql.getSql();
        sql += " limit 1";
        FieldUtils.writeField(boundSql, "sql", sql, true);
        return invocation.proceed();
    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {

    }
}
