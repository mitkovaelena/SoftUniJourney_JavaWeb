package controllers;

import entities.User;
import org.softuni.broccolina.solet.HttpSoletRequest;
import org.softuni.summer.api.*;
import repositories.UserRepository;

import java.util.List;

@Controller
public class FriendsController extends BaseController {
    private UserRepository userRepository;

    public FriendsController() {
        this.userRepository = new UserRepository();
    }

    @GetMapping(route = "/friends/add/{id}")
    @PreAuthorize(loggedin = true)
    public String addFriend(@PathVariable String id, HttpSoletRequest request, Model model) {
        String userId = String.valueOf(request.getSession().getAttributes().get("user-id"));
        this.userRepository.addFriend(userId, id);

        return super.redirect(request,model, "friends");
    }

    @GetMapping(route = "/friends")
    @PreAuthorize(loggedin = true)
    public String friends(HttpSoletRequest request, Model model) {
        String userId = String.valueOf(request.getSession().getAttributes().get("user-id"));
        List<User> friends = this.userRepository.findFriends(userId);

        //<div class="row mb-4 col-md-8 d-flex justify-content-center">

        StringBuilder result = new StringBuilder();

        for (int i = 0; i < friends.size(); i++) {
            User currentUser = friends.get(i);

            if (i == 0) {
                result
                        .append("<div class=\"row mb-4 col-md-8 d-flex justify-content-center\">");
            } else {
                result
                        .append("</div>")
                        .append("<div class=\"row mb-4 col-md-8 d-flex justify-content-center\">");
            }
            result.append(currentUser.getUserRemoveFriendView());
        }

        if (!friends.isEmpty()) result.append("</div>");

        model.addAttribute("friends", result.toString());

        return super.view(request,model, "friends");
    }

    @GetMapping(route = "/friends/unfriend/{id}")
    @PreAuthorize(loggedin = true)
    public String removeFriend(@PathVariable String id, HttpSoletRequest request, Model model) {
        String userId = String.valueOf(request.getSession().getAttributes().get("user-id"));
        this.userRepository.removeFriend(userId, id);

        return super.redirect(request,model, "home");
    }
}
