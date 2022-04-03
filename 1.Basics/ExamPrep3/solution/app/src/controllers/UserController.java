package controllers;

import bindingModels.UserLoginBindingModel;
import bindingModels.UserRegisterBindingModel;
import entities.Gender;
import entities.User;
import org.softuni.broccolina.solet.HttpSoletRequest;
import org.softuni.summer.api.*;
import repositories.UserRepository;

import java.util.HashSet;

@Controller
public class UserController extends BaseController{
    private UserRepository userRepository;

    public UserController() {
        this.userRepository = new UserRepository();
    }

    @GetMapping(route = "/login")
    @PreAuthorize  //deny access for logged users == isAnonymous()
    public String login(HttpSoletRequest request, Model model) {
        this.hideNotification(model);
        return super.view(request,model, "login");
    }

    @PostMapping(route = "/login")
    @PreAuthorize
    public String loginConfirm(HttpSoletRequest request, UserLoginBindingModel userLoginBindingModel, Model model) {
        if(userLoginBindingModel == null || userLoginBindingModel.getUsername() == null || userLoginBindingModel.getPassword() == null ) {
            this.showNotification(model, "Please fill in the form.", "danger");
            return super.view(request,model, "login");
        }

        User registeredUser = this.userRepository.findByUsername(userLoginBindingModel.getUsername());

        if(registeredUser == null ||
                !registeredUser.getPassword().equals(userLoginBindingModel.getPassword())) {
            this.showNotification(model, "Invalid credentials.", "danger");
            return super.view(request,model, "login");
        }

        request.getSession().addAttribute("user-id", registeredUser.getId());
        request.getSession().addAttribute("username", registeredUser.getUsername());

        return "redirect:/home";
    }

    @GetMapping(route = "/register")
    @PreAuthorize
    public String register(HttpSoletRequest request, Model model) {
        this.hideNotification(model);
        return super.view(request,model, "register");
    }

    @PostMapping(route = "/register")
    @PreAuthorize
    public String registerConfirm(UserRegisterBindingModel userRegisterBindingModel, HttpSoletRequest request, Model model) {
        if(userRegisterBindingModel == null || userRegisterBindingModel.getUsername() == null
                || userRegisterBindingModel.getPassword() == null || userRegisterBindingModel.getGender() == null ) {
            this.showNotification(model, "Please fill in the form.", "danger");
            return super.view(request,model, "register");
        }

        String gender = userRegisterBindingModel.getGender().toLowerCase();
        if (!gender.equals(Gender.getSimpleValue(Gender.FEMALE))
                && !gender.equals(Gender.getSimpleValue(Gender.MALE))) {
            this.showNotification(model, "Gender should be Female or Male", "danger");
            return super.view(request,model, "register");
        }

        if(!userRegisterBindingModel.getPassword()
                .equals(userRegisterBindingModel.getConfirmPassword())) {
            this.showNotification(model, "Passwords do not match.", "danger");
            return super.view(request,model, "register");
        }

        if(this.userRepository
                .findByUsername(userRegisterBindingModel.getUsername())
                != null) {
            this.showNotification(model, "This username is already taken.", "danger");
            return super.view(request,model, "register");
        }

        User user = new User();

        user.setUsername(userRegisterBindingModel.getUsername());
        user.setPassword(userRegisterBindingModel.getPassword());
        user.setGender(Gender.parseValue(userRegisterBindingModel.getGender()));

        this.userRepository.createUser(user);

        return super.redirect(request,model, "login");
    }

    @GetMapping(route = "/logout")
    @PreAuthorize(loggedin = true) //grant access only for logged users == isAuthenticated()
    public String logout(HttpSoletRequest request, Model model) {
        request.getSession().invalidate();

        return super.redirect(request,model, "");
    }

    @GetMapping(route = "/profile/{id}")
    @PreAuthorize(loggedin = true)
    public String profile(@PathVariable String id, Model model, HttpSoletRequest request) {
        String currentUserId = request.getSession().getAttributes().get("user-id").toString();
        User targetUser = this.userRepository.findById(id);

        if (targetUser == null) {
            return "redirect:/home";
        }

        if (!currentUserId.equals(targetUser.getId()) && !this.isFriend(currentUserId, targetUser)) {
            return "redirect:/home";
        }

        model.addAttribute("user", targetUser.getProfileView());

        return super.view(request,model, "profile");
    }

    private boolean isFriend(String currentUserId, User target) {

        for (User user : target.getFriends()) {
            if (user.getId().equals(currentUserId)) {
                return true;
            }
        }
        return false;
    }
}
