package mybatis;

import spring.jdbc.User;

import java.util.List;

public interface UserMapper {
    void insert(User user);
    User getUserById(Integer id);
    List<User> getUsers();
}
