package spring.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.Types;
import java.util.List;

@Service
public class UserServiceImpl implements IUserService {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public UserServiceImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public void save(User user) {
        jdbcTemplate.update("insert into user (name, age, sex) value (?, ?, ?)",
                new Object[]{user.getName(), user.getAge(), user.getSex()},
                new int[]{Types.VARCHAR, Types.INTEGER, Types.VARCHAR});
    }

    @Override
    public List<User> getUsers() {
        return jdbcTemplate.query("select * from user;", new UserRowMapper());
    }

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring" +
                ".xml");
        UserServiceImpl bean = context.getBean("userServiceImpl", UserServiceImpl.class);
//        User user = new User(1, "root", "male", 10);
//        bean.save(user);

        System.out.println(bean.getUsers());
    }
}
