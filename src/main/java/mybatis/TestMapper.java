package mybatis;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import spring.jdbc.User;

import java.util.List;

public class TestMapper {
    private SqlSessionFactory sessionFactory = MybatisUtil.sqlSessionFactory;

    public static void main(String[] args) {
        TestMapper testMapper = new TestMapper();
        SqlSession session = testMapper.sessionFactory.openSession();
        UserMapper mapper = session.getMapper(UserMapper.class);
        List<User> user = mapper.getUsers();
        System.out.println(user);
        session.close();
    }
}
