package servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/signout")
public class SignOutController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(request.getSession().getAttribute("LOGIN_MODEL") != null){
            HttpSession session = request.getSession();
            session.invalidate();
            response.sendRedirect("/bookhut");
        } else {
            request.getSession().removeAttribute("HIDDEN");
            request.getSession().setAttribute("ALERT_CLASS", "danger");
            request.getSession().setAttribute("ALERT_NAME", "Unauthorized!");
            request.getSession().setAttribute("ALERT_MESSAGE", "You are not logged in!");
            response.sendRedirect("/bookhut/signin");
        }
    }
}
