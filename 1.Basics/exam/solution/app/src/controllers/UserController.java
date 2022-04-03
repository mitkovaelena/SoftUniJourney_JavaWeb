package controllers;

import bindingModels.UserLoginBindingModel;
import bindingModels.UserRegisterBindingModel;
import entities.Tube;
import entities.User;
import org.softuni.broccolina.solet.HttpSoletRequest;
import org.softuni.summer.api.*;
import repositories.TubeRepository;
import repositories.UserRepository;

import java.util.List;

@Controller
public class UserController extends BaseController{
    private static final String EMAIL_PATTERN = "^[A-Za-z0-9][A-Za-z0-9.-_]*[A-Za-z0-9]@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*\\w+$";

    private UserRepository userRepository;
    private TubeRepository tubeRepository;

    public UserController() {
        this.userRepository = new UserRepository();
        this.tubeRepository = new TubeRepository();
    }

    @GetMapping(route = "/login")
    @PreAuthorize  //deny access for logged users == isAnonymous()
    public String login(HttpSoletRequest request, Model model) {
        this.hideNotification(model);
        return super.view(request,model, "login");
    }

    @PostMapping(route = "/login")
    @PreAuthorize
    public String loginConfirm(HttpSoletRequest request, UserLoginBindingModel userLoginBindingModel, Model model, BindingResult bindingResult) {
        if (bindingResult.hasErrors()){
            this.showNotification(model,  bindingResult.getErrors().get(0).getMessage(), "danger");
            return super.view(request,model, "login");
        }

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
    public String registerConfirm(UserRegisterBindingModel userRegisterBindingModel, HttpSoletRequest request, Model model,  BindingResult bindingResult) {
        if (bindingResult.hasErrors()){
            this.showNotification(model,  bindingResult.getErrors().get(0).getMessage(), "danger");
            return super.view(request,model, "register");
        }

        if(userRegisterBindingModel == null || userRegisterBindingModel.getUsername() == null
                || userRegisterBindingModel.getPassword() == null
                || userRegisterBindingModel.getConfirmPassword() == null
                ||userRegisterBindingModel.getEmail() == null ) {
            this.showNotification(model, "Please fill in the form.", "danger");
            return super.view(request,model, "register");
        }

        String email = userRegisterBindingModel.getEmail();
        if (!email.matches(EMAIL_PATTERN)) {
            this.showNotification(model, "Invalid email.", "danger");
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

        if(this.userRepository
                .findByEmail(userRegisterBindingModel.getEmail())
                != null) {
            this.showNotification(model, "This email is already registered.", "danger");
            return super.view(request,model, "register");
        }

        User user = new User();

        user.setUsername(userRegisterBindingModel.getUsername());
        user.setPassword(userRegisterBindingModel.getPassword());
        user.setEmail(userRegisterBindingModel.getEmail());

        this.userRepository.createUser(user);

        return super.redirect(request,model, "login");
    }

    @GetMapping(route = "/logout")
    @PreAuthorize(loggedin = true) //grant access only for logged users == isAuthenticated()
    public String logout(HttpSoletRequest request, Model model) {
        request.getSession().invalidate();

        return super.redirect(request,model, "");
    }

    @GetMapping(route = "/profile")
    @PreAuthorize(loggedin = true)
    public String profile(Model model, HttpSoletRequest request) {
        User currentUser = this.userRepository.findById(request.getSession().getAttributes().get("user-id").toString());

        model.addAttribute("username", currentUser.getUsername());
        model.addAttribute("email", currentUser.getEmail());

        StringBuilder result = new StringBuilder();
        List<Tube> uploadedTubes = this.tubeRepository.findUserUploadedTubes(currentUser.getId());

        for (int i = 0; i < uploadedTubes.size(); i++) {
            Tube currentTube = uploadedTubes.get(i);
            result.append(String.format(currentTube.getTubeTableView(), i+1));
        }

        if (!uploadedTubes.isEmpty()) result.append("</div>");

        model.addAttribute("uploadedTubes", result.toString());

        return super.view(request,model, "profile");
    }
}
