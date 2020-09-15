package mysql.sharding_jdbc;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.apache.commons.dbcp.BasicDataSource;
import org.apache.shardingsphere.api.config.sharding.ShardingRuleConfiguration;
import org.apache.shardingsphere.api.config.sharding.TableRuleConfiguration;
import org.apache.shardingsphere.api.config.sharding.strategy.InlineShardingStrategyConfiguration;
import org.apache.shardingsphere.shardingjdbc.api.ShardingDataSourceFactory;
import org.jetbrains.annotations.NotNull;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class TestDemo {
    public static void main(String[] args) throws SQLException, PropertyVetoException {
        Map<String, DataSource> map = getDataSource();
        ShardingRuleConfiguration config = getShardingRuleConfiguration();
        Properties props = new Properties();
        DataSource dataSource = ShardingDataSourceFactory.createDataSource(map, config, props);
        Connection connection = dataSource.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("insert into a values(2, ?,?)");
        preparedStatement.setInt(1, 1);
        preparedStatement.setString(2, "1");
        int update = preparedStatement.executeUpdate();
//        PreparedStatement preparedStatement = connection.prepareStatement("select * from a");

        ResultSet resultSet = connection.prepareStatement("select * from a").executeQuery();
        while (resultSet.next()) {
            int a = resultSet.getInt("a");
            int b = resultSet.getInt("b");
            String c = resultSet.getString("c");
            System.out.println(String.format("a: %d, b: %d, c: %s", a, b, c));
        }
    }

    @NotNull
    private static ShardingRuleConfiguration getShardingRuleConfiguration() {

        // 配置Order表规则
        TableRuleConfiguration orderTableRuleConfig = new TableRuleConfiguration("a","spring2.a_${0..1}");

        // 配置分库 + 分表策略
        orderTableRuleConfig.setTableShardingStrategyConfig(new InlineShardingStrategyConfiguration("a", "a_${a % 2}"));
//        orderTableRuleConfig.setTableShardingStrategyConfig(new InlineShardingStrategyConfiguration("order_id", "t_order${order_id % 2}"));

        // 配置分片规则
        ShardingRuleConfiguration shardingRuleConfig = new ShardingRuleConfiguration();
        shardingRuleConfig.getTableRuleConfigs().add(orderTableRuleConfig);
        return shardingRuleConfig;
    }

    @NotNull
    private static Map<String, DataSource> getDataSource() throws PropertyVetoException {
        // 配置第一个数据源
        Map<String, DataSource> dataSourceMap =  new HashMap<>();
        ComboPooledDataSource dataSource1 = new ComboPooledDataSource();
        dataSource1.setDriverClass("com.mysql.jdbc.Driver");
        dataSource1.setJdbcUrl("jdbc:mysql://localhost:3306/spring2");
        dataSource1.setUser("root");
        dataSource1.setPassword("root123");
        dataSourceMap.put("spring2", dataSource1);

        // 配置第二个数据源
//        BasicDataSource dataSource2 = new BasicDataSource();
//        dataSource2.setDriverClassName("com.mysql.jdbc.Driver");
//        dataSource2.setUrl("jdbc:mysql://localhost:3306/ds1");
//        dataSource2.setUsername("root");
//        dataSource2.setPassword("");
//        dataSourceMap.put("ds1", dataSource2);
        return dataSourceMap;
    }
}
