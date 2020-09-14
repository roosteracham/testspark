package mysql.分布式事务;

import com.mysql.jdbc.jdbc2.optional.MysqlXADataSource;

import javax.sql.XAConnection;
import javax.transaction.xa.XAResource;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class TestDemo {

    static MysqlXADataSource getXADataSource(String url, String name, String pwd) {
        try {
            MysqlXADataSource dataSource = new MysqlXADataSource();
            dataSource.setUser(name);
            dataSource.setURL(url);
            dataSource.setPassword(pwd);
            return dataSource;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        String url1 = "jdbc:mysql://localhost:3306/spring2?useUnicode=true&characterEncoding=utf8";
        String url2 = "jdbc:mysql://192.168.150.129:3306/test?useUnicode=true&characterEncoding=utf8";
        String name = "root";
        String pwd = "root123";
        try {
            MysqlXADataSource dataSource1 = getXADataSource(url1, name, pwd);
            MysqlXADataSource dataSource2 = getXADataSource(url2, name, pwd);


            XAConnection xaConnection1 = dataSource1.getXAConnection();
            // xa 事务对象
            XAResource xaResource1 = xaConnection1.getXAResource();
            Connection connection1 = xaConnection1.getConnection();
            Statement connection1Statement = connection1.createStatement();

            XAConnection xaConnection2 = dataSource2.getXAConnection();
            XAResource xaResource2 = xaConnection2.getXAResource();
            Connection connection2 = xaConnection2.getConnection();
            Statement connection2Statement = connection2.createStatement();

            MyXid xid1 = new MyXid(100, new byte[]{0x01}, new byte[]{0x02});
            MyXid xid2 = new MyXid(100, new byte[]{0x11}, new byte[]{0x12});

            try {

                // 开始事务 xa start 'a'
                xaResource1.start(xid1, XAResource.TMNOFLAGS);
                update(connection1Statement, "update t set b = b - 1 where a = 12 limit 1");
                // xa end 'a'
                xaResource1.end(xid1, XAResource.TMSUCCESS);


                xaResource2.start(xid2, XAResource.TMNOFLAGS);
                update(connection2Statement, "update t set b = b + 1 where a = 2 limit 1");
                xaResource2.end(xid2, XAResource.TMSUCCESS);

                // xa prepare 'a'
                int prepare1 = xaResource1.prepare(xid1);
                int prepare2 = xaResource2.prepare(xid2);

                // xa recover 呢？

                // xa commit 'a'
                if (prepare1 == XAResource.XA_OK && prepare1 == prepare2) {
                    xaResource1.commit(xid1, false);
                    xaResource2.commit(xid2, false);
                } else {
                    xaResource1.rollback(xid1);
                    xaResource2.rollback(xid2);
                }
            } catch (Exception e) {
                xaResource1.rollback(xid1);
                xaResource2.rollback(xid2);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void update(Statement connection2Statement, String s) throws SQLException {
        boolean execute = connection2Statement.execute(s);
    }
}
