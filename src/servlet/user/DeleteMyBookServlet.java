package servlet.user;

import dto.UserDto;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import serrvice.BookService;
import serrvice.UserBooksListService;
import util.JspHelper;

import java.io.IOException;
@WebServlet("/deleteMyBook")
public class DeleteMyBookServlet extends HttpServlet {
    private static final UserBooksListService userBooksListService = UserBooksListService.getInstance();
    private static final BookService bookService = BookService.getInstance();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var user = (UserDto)req.getSession().getAttribute("user");
        var userId = user.getId();
        var myBooksList = userBooksListService.getMyBooksList(userId);
        req.setAttribute("books", myBooksList);
        req.getRequestDispatcher(JspHelper.getUserFilePath("seeMyReadingList"))
                .forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var s = req.getParameter("booksToDelete");
        var bookId = bookService.findBookId(s);
        userBooksListService.deleteFromMyLibrary(bookId.get());
        resp.sendRedirect("/seeMyReadingList");
    }
}
