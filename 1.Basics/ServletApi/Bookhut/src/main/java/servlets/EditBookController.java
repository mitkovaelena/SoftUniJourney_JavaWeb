package servlets;

import models.bindingModels.AddBookModel;
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
import java.net.URLDecoder;

@WebServlet("/shelves/edit/*")
public class EditBookController extends HttpServlet {

    private BookService bookService;

    public EditBookController() {
        this.bookService = new BookServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getSession().getAttribute("LOGIN_MODEL") != null) {
            HttpSession session = request.getSession();
            session.removeAttribute("book");
            String tokens[] = request.getRequestURI().split("/");
            String title = URLDecoder.decode(tokens[tokens.length - 1], "UTF-8");
            ViewBookModel viewBookModel = this.bookService.findBookByTitle(title);
            if (viewBookModel != null) {
                session.setAttribute("book", viewBookModel);
                request.getRequestDispatcher("/templates/editBook.jsp").forward(request, response);
            } else {
                request.getSession().removeAttribute("HIDDEN");
                request.getSession().setAttribute("ALERT_CLASS", "danger");
                request.getSession().setAttribute("ALERT_NAME", "Oops!");
                request.getSession().setAttribute("ALERT_MESSAGE", "Something went wrong!");
                response.sendRedirect("/bookhut/shelves");
            }
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
            String tokens[] = request.getRequestURI().split("/");
            String oldTitle = URLDecoder.decode(tokens[tokens.length - 1], "UTF-8");
            String title = request.getParameter("title");
            String author = request.getParameter("author");
            String pages = request.getParameter("pages");
            if (!title.isEmpty() && !author.isEmpty() && !pages.isEmpty()) {
                this.bookService.deleteBookByTitle(oldTitle);
                this.bookService.saveBook(new AddBookModel(title, author, Integer.valueOf(pages)));
                response.sendRedirect("/bookhut/shelves");
            } else {
                request.getSession().removeAttribute("HIDDEN");
                request.getSession().setAttribute("ALERT_CLASS", "danger");
                request.getSession().setAttribute("ALERT_NAME", "Empty field!");
                request.getSession().setAttribute("ALERT_MESSAGE", "Please fill in all fields!");
                response.sendRedirect("/bookhut/shelves/edit/" + oldTitle);
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

