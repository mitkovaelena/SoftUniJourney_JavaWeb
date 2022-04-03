package controllers;

import entities.User;
import org.softuni.broccolina.solet.HttpSoletRequest;
import org.softuni.summer.api.Controller;
import org.softuni.summer.api.GetMapping;
import org.softuni.summer.api.Model;
import org.softuni.summer.api.PreAuthorize;
import repositories.UserRepository;

import java.util.List;

@Controller
public class HomeController extends BaseController {
    private UserRepository userRepository;

    public HomeController() {
        this.userRepository = new UserRepository();
    }

    @GetMapping(route = "/")
    @PreAuthorize
    public String index(HttpSoletRequest request, Model model) {
        return super.view(request,model, "index");
    }

    @GetMapping(route = "/home")
    @PreAuthorize(loggedin = true)
    public String home(HttpSoletRequest request, Model model) {
        String userId = String.valueOf(request.getSession().getAttributes().get("user-id"));

        model.addAttribute("username", request.getSession().getAttributes().get("username"));
        model.addAttribute("id", userId);

        //<div class="row mb-4 col-md-12 d-flex justify-content-around">

        StringBuilder result = new StringBuilder();
        List<User> allUsers = this.userRepository.findStrangers(userId);

        for (int i = 0; i < allUsers.size(); i++) {
            User currentUser = allUsers.get(i);

            if (i == 0) {
                result
                        .append("<div class=\"row mb-4 col-md-12 d-flex justify-content-around\">");
            } else if (i % 4 == 0) {
                result
                        .append("</div>")
                        .append("<div class=\"row mb-4 col-md-12 d-flex justify-content-around\">");
            }
            result.append(currentUser.getUserAddFriendView());
        }

        if (!allUsers.isEmpty()) result.append("</div>");

        model.addAttribute("allUsers", result.toString());

        return super.view(request,model, "home");
    }
}
