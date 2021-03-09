package mybatis;

import domain.User;

public class UserDao implements IUserDao {
    @Override
    public User getUser() {
        return new User("a", 1.0, 1);
    }
}
