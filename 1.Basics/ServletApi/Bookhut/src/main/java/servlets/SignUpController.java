package servlets;

import models.bindingModels.LoginModel;
import services.UserService;
import services.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/signup")
public class SignUpController extends HttpServlet {
    private UserService userService;

    public SignUpController() {
        this.userService = new UserServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getSession().getAttribute("LOGIN_MODEL") == null) {
            request.getRequestDispatcher("/templates/signUp.jsp").forward(request, response);
        } else {
            request.getSession().removeAttribute("HIDDEN");
            request.getSession().setAttribute("ALERT_CLASS", "danger");
            request.getSession().setAttribute("ALERT_NAME", "Unauthorized!");
            request.getSession().setAttribute("ALERT_MESSAGE", "You are already logged in!");
            response.sendRedirect("/bookhut/");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getSession().getAttribute("LOGIN_MODEL") == null) {
            String username = request.getParameter("username");
            String pass = request.getParameter("pass");
            if (!username.isEmpty() && !pass.isEmpty()) {
                LoginModel loginModel = new LoginModel(username, pass);
                if(userService.findByUsername(username) == null) {
                    this.userService.createUser(loginModel);
                    response.sendRedirect("/bookhut/signin");
                } else {
                    request.getSession().removeAttribute("HIDDEN");
                    request.getSession().setAttribute("ALERT_CLASS", "danger");
                    request.getSession().setAttribute("ALERT_NAME", "Username taken!");
                    request.getSession().setAttribute("ALERT_MESSAGE", "User with this username already exists!");
                    response.sendRedirect("/bookhut/signup");
                }
            } else {
                request.getSession().removeAttribute("HIDDEN");
                request.getSession().setAttribute("ALERT_CLASS", "danger");
                request.getSession().setAttribute("ALERT_NAME", "Empty field!");
                request.getSession().setAttribute("ALERT_MESSAGE", "Please fill in all fields!");
                response.sendRedirect("/bookhut/signup");
            }
        } else {
            request.getSession().removeAttribute("HIDDEN");
            request.getSession().setAttribute("ALERT_CLASS", "danger");
            request.getSession().setAttribute("ALERT_NAME", "Unauthorized!");
            request.getSession().setAttribute("ALERT_MESSAGE", "You are already logged in!");
            response.sendRedirect("/bookhut/");
        }
    }

}
