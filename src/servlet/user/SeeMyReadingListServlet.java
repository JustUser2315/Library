package servlet.user;

import dto.BookDto;
import dto.UserDto;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import serrvice.UserBooksListService;
import util.JspHelper;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;


@WebServlet("/seeMyReadingList")
public class SeeMyReadingListServlet extends HttpServlet {
    private static final UserBooksListService userBooksListService = UserBooksListService.getInstance();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var user = (UserDto)req.getSession().getAttribute("user");
        var userId = user.getId();
        var books = userBooksListService.getMyBooksList(userId);
        req.setAttribute("books", books);
        req.getRequestDispatcher(JspHelper.getUserFilePath("seeMyReadingList"))
                .forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var user = (UserDto)req.getSession().getAttribute("user");
        var userId = user.getId();
        var books = userBooksListService.getMyBooksList(userId);
        books = myBooksListSort(req, books);
        req.setAttribute("books", books);
        req.getRequestDispatcher(JspHelper.getUserFilePath("seeMyReadingList"))
                .include(req,resp);
    }

    private static List<BookDto> myBooksListSort(HttpServletRequest req, List<BookDto> books) {
        var sort = req.getParameter("sort");
        if(sort.isBlank()||sort.isEmpty()){
            books = books;
        }
        else if(sort.equals("Id")){
            books = books.stream().sorted(Comparator.comparing(o -> Long.valueOf(o.getId()))).toList();
        }else if(sort.equals("Title")){
            books = books.stream().sorted(Comparator.comparing(BookDto::getTitle)).toList();
        }else if(sort.equals("Author")){
            books = books.stream().sorted(Comparator.comparing(BookDto::getAuthor)).toList();
        }else if(sort.equals("Year")){
            books = books.stream().sorted(Comparator.comparing(o -> Long.valueOf(o.getYear()))).toList();
        } else if(sort.equals("Genre")){
            books = books.stream().sorted(Comparator.comparing(BookDto::getGenre)).toList();
        }
        return booksSearch(req, books);
    }

    private static List<BookDto> booksSearch(HttpServletRequest req, List<BookDto> books) {
        var search = req.getParameter("search");
        if(search.isBlank()||search.isEmpty()){
            return books;
        }else {
            return books.stream().filter(o->o.getTitle().contains(search)||o.getAuthor().contains(search)||o.getYear().contains(search)
            || o.getGenre().contains(search)).toList();
        }
    }
}
