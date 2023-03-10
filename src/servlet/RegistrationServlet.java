package servlet;

import dto.AdminDto;
import dto.UserDto;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import serrvice.AdminService;
import serrvice.UserService;
import util.JspHelper;
import exception.ValidationException;

import java.io.IOException;
import java.util.List;

@WebServlet("/registration")
public class RegistrationServlet extends HttpServlet {
    private static final UserService userService = UserService.getInstance();
    private static final AdminService adminService = AdminService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<String>roles = List.of("Admin", "User");
        req.setAttribute("roles", roles);
        req.getRequestDispatcher(JspHelper.getPath("registration")).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var role = req.getParameter("role");
        if(role.equals("Admin")){
        AdminDto adminDto = AdminDto.builder()
                .firstName(req.getParameter("first.name"))
                .lastName(req.getParameter("LastName"))
                .birthday(req.getParameter("Birthday"))
                .email(req.getParameter("email"))
                .password(req.getParameter("Password"))
                .build();
            try {
                adminService.save(adminDto);
                resp.sendRedirect("/");
            } catch (ValidationException e) {
                req.setAttribute("valid", e.getErrors());
                doGet(req, resp);

            }
        }else if (role.equals("User")) {

            UserDto userDto = UserDto.builder()
                    .firstName(req.getParameter("first.name"))
                    .lastName(req.getParameter("LastName"))
                    .birthday(req.getParameter("Birthday"))
                    .email(req.getParameter("email"))
                    .password(req.getParameter("Password"))
                    .build();
            try {
                userService.save(userDto);
                resp.sendRedirect("/");
            } catch (ValidationException e) {
                req.setAttribute("valid", e.getErrors());
                doGet(req, resp);

            }
        }

    }
}
