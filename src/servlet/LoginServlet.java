package servlet;

import dto.AdminDto;
import dto.UserDto;
import exception.ValidationException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;
import serrvice.AdminService;
import serrvice.UserService;
import util.JspHelper;

import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private static final AdminService adminService = AdminService.getInstance();
    private static final UserService userService = UserService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(JspHelper.getPath("login")).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var role = req.getParameter("role");
        var email = req.getParameter("Email");
        var password = req.getParameter("Password");
        try {
            if (role.equals("Admin")) {
                adminService.loginAdmin(email, password).ifPresentOrElse(adminDto -> {
                    onLoginSuccessAdmin(req, resp, adminDto);
                }, () -> onLoginFailureAdmin(resp));
            }
            else if (role.equals("User")) {
                userService.loginUser(email, password).ifPresentOrElse(userDto -> {
                    onLoginSuccessUser(req, resp, userDto);
                }, () -> onLoginFailUser(resp));
            }

        } catch (ValidationException e) {
            req.setAttribute("valid", e.getErrors());
            doGet(req, resp);
        }
    }

    @SneakyThrows
    private void onLoginFailUser(HttpServletResponse resp) {
        resp.sendRedirect("/login");
    }

    @SneakyThrows
    private void onLoginSuccessUser(HttpServletRequest req, HttpServletResponse resp, UserDto userDto) {
        req.getSession().setAttribute("user", userDto);
        resp.sendRedirect("/userOptions");
    }

    @SneakyThrows
    private void onLoginSuccessAdmin(HttpServletRequest req, HttpServletResponse resp, AdminDto adminDto) {
        req.getSession().setAttribute("admin", adminDto);
        resp.sendRedirect("/adminOptions");

    }

    @SneakyThrows
    private void onLoginFailureAdmin(HttpServletResponse resp) {
        resp.sendRedirect("/login");
    }
}
