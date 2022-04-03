package repositories;

import entities.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

//ToDo implement db
public class UserRepositoryImpl implements UserRepository {
    private static UserRepository userRepository;
    private List<User> users;

    private UserRepositoryImpl() {
        this.users = new ArrayList<>();
    }

    public static UserRepository getInstance() {
        if (userRepository == null) {
            userRepository = new UserRepositoryImpl();
        }

        return userRepository;
    }
    @Override
    public void createUser(User user) {
        if(this.findByUsername(user.getUsername()) == null){
            this.users.add(user);
        }
    }

    @Override
    public User findByUsernameAndPassword(String username, String password) {
        User user = this.findByUsername(username);
        return user != null && user.getPassword().equals(password) ? user : null;
    }

    @Override
    public User findByUsername(String username) {
        Optional<User> userOptional = this.users.stream()
                .filter(u -> u.getUsername().equals(username))
                .findFirst();
        return userOptional.orElse(null);
    }

}
