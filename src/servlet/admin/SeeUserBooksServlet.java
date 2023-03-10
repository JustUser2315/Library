package servlet.admin;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import serrvice.UserBooksListService;
import util.JspHelper;

import java.io.IOException;
@WebServlet("/seeUsersBook")
public class SeeUserBooksServlet extends HttpServlet {
    private static final UserBooksListService userBooksListService = UserBooksListService.getInstance();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var books = userBooksListService.getMyBooksList(Long.valueOf(req.getParameter("userId")));
        req.setAttribute("books", books);
        req.getRequestDispatcher(JspHelper.getUserFilePath("seeMyReadingList"))
                .forward(req,resp);
    }
}
