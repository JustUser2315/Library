package servlet.admin;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import serrvice.BookService;

import java.io.IOException;

@WebServlet("/deleteBook")
public class DeleteBookByIdServlet extends HttpServlet {
    private static final BookService bookService = BookService.getInstance();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var bookId = req.getParameter("bookId");
        var id = Long.parseLong(bookId);
        bookService.deleteBookById(id);
        resp.sendRedirect("/seeAllBooks");
    }
}
