package spring.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Component
public class MyDataSourceTransactionManager extends DataSourceTransactionManager {

    public MyDataSourceTransactionManager() {
    }

    @Autowired
    public MyDataSourceTransactionManager(DataSource dataSource) {
        super(dataSource);
    }
}
