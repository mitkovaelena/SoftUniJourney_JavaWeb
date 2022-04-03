package servlets;

import models.bindingModels.LoginModel;
import services.UserService;
import services.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/signin")
public class SignInController extends HttpServlet {

    private UserService userService;

    public SignInController() {
        this.userService = new UserServiceImpl();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getSession().getAttribute("LOGIN_MODEL") == null) {
            request.getRequestDispatcher("templates/signIn.jsp").forward(request, response);
        } else {
            request.getSession().removeAttribute("HIDDEN");
            request.getSession().setAttribute("ALERT_CLASS", "danger");
            request.getSession().setAttribute("ALERT_NAME", "Unauthorized!");
            request.getSession().setAttribute("ALERT_MESSAGE", "You are already logged in!");
            response.sendRedirect("/bookhut/");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getSession().getAttribute("LOGIN_MODEL") == null) {
            String username = request.getParameter("username");
            String pass = request.getParameter("pass");
            if (!username.isEmpty() && !pass.isEmpty()) {
                LoginModel loginModel = this.userService.findByUsernameAndPassword(username, pass);

                if (loginModel != null) {
                    HttpSession session = request.getSession();
                    session.setAttribute("LOGIN_MODEL", loginModel);
                    session.setAttribute("USERNAME", username);
                    response.sendRedirect("/bookhut");
                } else {
                    request.getSession().removeAttribute("HIDDEN");
                    request.getSession().setAttribute("ALERT_CLASS", "danger");
                    request.getSession().setAttribute("ALERT_NAME", "Wrong username or password!");
                    request.getSession().setAttribute("ALERT_MESSAGE", "Please fill in your details again!");
                    response.sendRedirect("/bookhut/signin");
                }
            } else {
                request.getSession().removeAttribute("HIDDEN");
                request.getSession().setAttribute("ALERT_CLASS", "danger");
                request.getSession().setAttribute("ALERT_NAME", "Empty field!");
                request.getSession().setAttribute("ALERT_MESSAGE", "Please fill in all fields!");
                response.sendRedirect("/bookhut/signin");
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
