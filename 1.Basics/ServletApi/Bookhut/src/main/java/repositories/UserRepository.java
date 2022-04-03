package repositories;

import entities.User;

public interface UserRepository {

    void createUser(User user);

    User findByUsernameAndPassword(String username, String password);

    User findByUsername(String username);
}
