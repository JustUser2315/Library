package servlet.admin;

import dto.BookDto;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import serrvice.BookService;
import util.JspHelper;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;

@WebServlet("/seeAllBooks")
public class SeeAllBooksServlet extends HttpServlet {
    private static final BookService bookService = BookService.getInstance();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var books = bookService.getList().stream().sorted((o1, o2) -> Long.valueOf(o1.getId()).compareTo(Long.valueOf(o2.getId())))
                .toList();
        req.setAttribute("books", books);
        req.getRequestDispatcher(JspHelper.getPath("seeAllBooks")).forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        var books = bookService.getList();

        books = booksSort(req, books);
        //booksSearch(req, books);
        req.setAttribute("books", books);
        req.getRequestDispatcher(JspHelper.getPath("seeAllBooks")).include(req, resp);

    }

    private static List<BookDto> booksSearch(HttpServletRequest req, List<BookDto> books) {
        var search = req.getParameter("search");
        if(search.isEmpty()||search.isBlank()){
            return books;
        }else {
            return books.stream().filter(e -> e.getTitle().contains(search) || e.getAuthor().contains(search) || e.getYear().contains(search)
                    ||e.getGenre().contains(search))
                    .toList();
        }
    }

    private static List<BookDto> booksSort(HttpServletRequest req, List<BookDto> books) {
        var sort = req.getParameter("sort");
        if(sort.isBlank() || sort.isEmpty()){
             books = books;
        } else if (sort.equals("Author")) {
            books = books.stream().sorted(Comparator.comparing(BookDto::getAuthor)).toList();
        } else if (sort.equals("Id")) {
            books = books.stream().sorted(Comparator.comparing(o -> Long.valueOf(o.getId()))).toList();
        } else if (sort.equals("Title")) {
            books = books.stream().sorted(Comparator.comparing(BookDto::getTitle)).toList();
        } else if (sort.equals("Year")) {
            books = books.stream().sorted(Comparator.comparing(o -> Long.valueOf(o.getYear()))).toList();
        } else if (sort.equals("Amount")) {
            books = books.stream().sorted(Comparator.comparing(o -> Long.valueOf(o.getAmount()))).toList();
        } else if (sort.equals("Genre")) {
            books = books.stream().sorted(Comparator.comparing(BookDto::getGenre)).toList();
        }
        return booksSearch(req, books);
    }
}
