package servlet.admin;

import dto.BookDto;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import serrvice.BookService;
import util.JspHelper;

import java.io.IOException;

@WebServlet("/addBookToLibrary")
@MultipartConfig(fileSizeThreshold = 1024*1024)
public class AddBookServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(JspHelper.getAdminFilePath("addBookToLibrary")).forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BookDto bookDto = BookDto.builder()
                .id(req.getParameter("bookId"))
                .title(req.getParameter("bookTitle"))
                .author(req.getParameter("bookAuthor"))
                .year(req.getParameter("bookYear"))
                .amount(req.getParameter("bookAmount"))
                .cover(req.getPart("cover"))
                .genre(req.getParameter("genre"))
                .build();
        BookService.save(bookDto);
        resp.sendRedirect("/adminOptions");
    }
}
