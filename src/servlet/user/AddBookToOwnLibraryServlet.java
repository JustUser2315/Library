package servlet.user;

import dto.UserBooksListDto;
import dto.UserDto;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import serrvice.BookService;
import serrvice.UserBooksListService;

import java.io.IOException;

@WebServlet("/addBookToOwnLibrary")
public class AddBookToOwnLibraryServlet extends HttpServlet {
    private static final UserBooksListService userBooksListService = UserBooksListService.getInstance();
    private static final BookService bookService = BookService.getInstance();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var bookName = req.getParameter("book");
        var bookId = bookService.findBookId(bookName).get();
        var user = (UserDto)req.getSession().getAttribute("user");
        var userId = user.getId();
       var dto =  UserBooksListDto.builder()
                        .bookId(bookId)
                        .userId(userId)
                        .build();
        userBooksListService.save(dto);
        resp.sendRedirect("/seeAllBooks");

    }
}
