package services;

import models.bindingModels.LoginModel;

public interface UserService {

    void createUser(LoginModel loginModel);

    LoginModel findByUsernameAndPassword(String username, String password);

    LoginModel findByUsername(String username);
}
