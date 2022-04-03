package services;

import entities.User;
import models.bindingModels.LoginModel;
import org.modelmapper.ModelMapper;
import repositories.UserRepository;
import repositories.UserRepositoryImpl;
import utils.MappingUtil;

import java.util.UUID;

public class UserServiceImpl implements UserService {
    private UserRepository repository;

    public UserServiceImpl() {
        this.repository = UserRepositoryImpl.getInstance();
    }

    @Override
    public void createUser(LoginModel loginModel) {
        User user = MappingUtil.convert(loginModel, User.class);
        user.setId(UUID.randomUUID());
        this.repository.createUser(user);
    }

    @Override
    public LoginModel findByUsernameAndPassword(String username, String password) {
        User user = repository.findByUsernameAndPassword(username, password);
        return MappingUtil.convert(user, LoginModel.class);
    }

    @Override
    public LoginModel findByUsername(String username) {
        User user = repository.findByUsername(username);
        return MappingUtil.convert(user, LoginModel.class);
    }
}
