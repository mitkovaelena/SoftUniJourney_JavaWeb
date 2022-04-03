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

@WebServlet("/add")
public class AddBookController extends HttpServlet {

    private BookService bookService;

    public AddBookController() {
        this.bookService = new BookServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getSession().getAttribute("LOGIN_MODEL") != null) {
            request.getRequestDispatcher("/templates/addBook.jsp").forward(request, response);
        } else {
            request.getSession().removeAttribute("HIDDEN");
            request.getSession().setAttribute("ALERT_CLASS", "danger");
            request.getSession().setAttribute("ALERT_NAME", "Unauthorized!");
            request.getSession().setAttribute("ALERT_MESSAGE", "You are not logged in!");
            response.sendRedirect("/bookhut/signin");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getSession().getAttribute("LOGIN_MODEL") != null) {
            String title = request.getParameter("title");
            String author = request.getParameter("author");
            String pages = request.getParameter("pages");
            if (!title.isEmpty() && !author.isEmpty() && !pages.isEmpty()) {
                AddBookModel bookModel = new AddBookModel(title, author, Integer.valueOf(pages));
                this.bookService.saveBook(bookModel);
                response.sendRedirect("/bookhut/shelves");
            } else {
                request.getSession().removeAttribute("HIDDEN");
                request.getSession().setAttribute("ALERT_CLASS", "danger");
                request.getSession().setAttribute("ALERT_NAME", "Empty field!");
                request.getSession().setAttribute("ALERT_MESSAGE", "Please fill in all fields!");
                response.sendRedirect("/bookhut/add");
            }
        } else {
            request.getSession().removeAttribute("HIDDEN");
            request.getSession().setAttribute("ALERT_CLASS", "danger");
            request.getSession().setAttribute("ALERT_NAME", "Unauthorized!");
            request.getSession().setAttribute("ALERT_MESSAGE", "You are not logged in!");
            response.sendRedirect("/bookhut/signin");
        }
    }
}

