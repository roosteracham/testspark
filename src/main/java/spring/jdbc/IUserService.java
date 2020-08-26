package spring.jdbc;

import java.util.List;

public interface IUserService {
    void save(User user);

    List<User> getUsers();
}
