package spring.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.sql.Types;
import java.util.List;

@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = RuntimeException.class)
public class UserServiceImpl implements IUserService {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public UserServiceImpl(@Qualifier("dataSource0") DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public void save(User user) {
        jdbcTemplate.update("insert into user (name, age, sex) value (?, ?, ?)",
                new Object[]{user.getName(), user.getAge(), user.getSex()},
                new int[]{Types.VARCHAR, Types.INTEGER, Types.VARCHAR});
        throw new RuntimeException();
    }

    @Override
    public List<User> getUsers() {
        return jdbcTemplate.query("select * from user;", new UserRowMapper());
    }

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring" +
                ".xml");
        IUserService bean = context.getBean("userServiceImpl", IUserService.class);
        User user = new User(1, "root", "male", 11);
        bean.save(user);
//        User user = bean.jdbcTemplate.queryForObject("select user from user limit 1", User.class);
//        System.out.println(bean.getUsers());
    }
}
