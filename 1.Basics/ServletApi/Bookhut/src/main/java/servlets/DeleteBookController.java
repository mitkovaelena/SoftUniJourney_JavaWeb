package servlets;

import models.bindingModels.AddBookModel;
import services.BookService;
import services.BookServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLDecoder;

@WebServlet("/shelves/delete/*")
public class DeleteBookController extends HttpServlet {

    private BookService bookService;

    public DeleteBookController() {
        this.bookService = new BookServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getSession().getAttribute("LOGIN_MODEL") != null) {
            String tokens[] = request.getRequestURI().split("/");
            String title = URLDecoder.decode(tokens[tokens.length - 1], "UTF-8");
            if (title != null) {
                this.bookService.deleteBookByTitle(title);
            } else {
                request.getSession().removeAttribute("HIDDEN");
                request.getSession().setAttribute("ALERT_CLASS", "danger");
                request.getSession().setAttribute("ALERT_NAME", "Oops!");
                request.getSession().setAttribute("ALERT_MESSAGE", "Something went wrong!");
            }
            response.sendRedirect("/bookhut/shelves");
        } else {
            request.getSession().removeAttribute("HIDDEN");
            request.getSession().setAttribute("ALERT_CLASS", "danger");
            request.getSession().setAttribute("ALERT_NAME", "Unauthorized!");
            request.getSession().setAttribute("ALERT_MESSAGE", "You are not logged in!");
            response.sendRedirect("/bookhut/signin");
        }
    }
}


