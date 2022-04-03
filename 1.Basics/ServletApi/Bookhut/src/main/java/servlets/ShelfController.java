package servlets;

import models.viewModels.ViewBookModel;
import services.BookService;
import services.BookServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@WebServlet("/shelves")
public class ShelfController extends HttpServlet {

    private BookService bookService;

    public ShelfController() {
        this.bookService = new BookServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        session.removeAttribute("BOOKS");
        if (request.getSession().getAttribute("LOGIN_MODEL") != null) {
            List<ViewBookModel> viewBookModels = this.bookService.getAllBooks();
            if (!viewBookModels.isEmpty()) {
                session.setAttribute("BOOKS", viewBookModels.stream().filter(Objects::nonNull).collect(Collectors.toList()));
            }
            request.getRequestDispatcher("/templates/shelves.jsp").forward(request, response);
        } else {
            request.getSession().removeAttribute("HIDDEN");
            request.getSession().setAttribute("ALERT_CLASS", "danger");
            request.getSession().setAttribute("ALERT_NAME", "Unauthorized!");
            request.getSession().setAttribute("ALERT_MESSAGE", "You are not logged in!");
            response.sendRedirect("/bookhut/signin");
        }
    }
}
