package controllers;

import org.softuni.broccolina.solet.HttpSoletRequest;
import org.softuni.summer.api.Model;

public class BaseController {

    private boolean isLoggedIn(HttpSoletRequest request) {
        return request.getSession().getAttributes().containsKey("user-id");
    }

    protected void setLayout(HttpSoletRequest request, Model model){
               if (this.isLoggedIn(request)) {
            model.addPartial("navbar", "/partials/navbar-logged");
        } else {
            model.addPartial("navbar", "/partials/navbar-default");
        }

        model.addPartial("footer", "/partials/footer");
        model.addPartial("notification", "/partials/notification");
    }

    protected void showNotification(Model model, String message, String type) {
        model.addAttribute("type", type);
        model.addAttribute("message", message);
        model.addAttribute("display", "style=\"display:block;\"");
    }

    protected void hideNotification(Model model) {
        model.addAttribute("display", "style=\"display:none;\"");
    }

    protected String view(HttpSoletRequest request, Model model, String view) {
        this.setLayout(request, model);
        return "template:" + view;
    }

    protected String redirect(HttpSoletRequest request, Model model, String view) {
        this.setLayout(request, model);
        return "redirect:/" + view;
    }
}
