package mysql.sharding_jdbc;

import org.apache.shardingsphere.shardingjdbc.api.yaml.YamlShardingDataSourceFactory;

import javax.sql.DataSource;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class YamlDemo {
    public static void main(String[] args) throws IOException, SQLException {
        DataSource dataSource = YamlShardingDataSourceFactory.createDataSource(new File("D:\\IdeaProjects\\testspark" +
                "\\src\\main\\resources\\config\\sharding-spring2.yml"));
        Connection connection = dataSource.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("select * from a");
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            int a = resultSet.getInt("a");
            int b = resultSet.getInt("b");
            String c = resultSet.getString("c");
            System.out.println(String.format("a: %d, b: %d, c: %s", a, b, c));
        }
    }
}
