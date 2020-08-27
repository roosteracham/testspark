package mybatis;

import spring.jdbc.User;

public interface UserMapper {
    void insert(User user);
    User getUserById(Integer id);
}
