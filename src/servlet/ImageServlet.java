package servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;
import serrvice.ImageService;

import java.io.IOException;
import java.io.InputStream;

@WebServlet("/images/*")
public class ImageServlet extends HttpServlet {
    private static final ImageService imageService = ImageService.getInstance();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var replace = req.getRequestURI().replace("/images", "");
        imageService.loadImageToPage(replace).ifPresentOrElse(inputStream -> {
            write(resp,inputStream);
        }, ()->resp.setStatus(404));

    }
    @SneakyThrows
    private static void write(HttpServletResponse r, InputStream i){
        int b;
        var outputStream = r.getOutputStream();
        try(i; outputStream){
            while ((b= i.read())!=-1){
                outputStream.write(b);
            }
        }
    }
}
