package servlet.admin;

import dto.UserDto;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import serrvice.UserService;
import util.DateFormatHelper;
import util.JspHelper;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;

@WebServlet("/seeAllUsers")
public class SeeAllUsersServlet extends HttpServlet {
    private static final UserService userService = UserService.getInstance();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var users = userService.getList();
        req.setAttribute("users", users);
        req.getRequestDispatcher(JspHelper.getAdminFilePath("seeAllUsers")).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var users = userService.getList();
        users = userSort(req, users);
        req.setAttribute("users", users);
        req.getRequestDispatcher(JspHelper.getAdminFilePath("seeAllUsers")).include(req, resp);
    }


    private static List<UserDto> userSort(HttpServletRequest req, List<UserDto> users) {
        var sort = req.getParameter("sort");
        if (req.getParameter("sort").equals("Id")) {
            users = users.stream().sorted((Comparator.comparing(UserDto::getId))).toList();
        } else if (req.getParameter("sort").equals("Name")) {
            users = users.stream().sorted((Comparator.comparing(UserDto::getFirstName))).toList();
        } else if (req.getParameter("sort").equals("Birthday")) {
            users = users.stream().sorted((Comparator.comparing(o -> DateFormatHelper.format(o.getBirthday())))).toList();
        } else if (req.getParameter("sort").equals("email")) {
            users = users.stream().sorted((Comparator.comparing(UserDto::getEmail))).toList();
        } else if (req.getParameter("sort").equals("Password")) {
            users = users.stream().sorted((Comparator.comparing(UserDto::getPassword))).toList();
        }
        return userSearch(req, users);
    }
    private static List<UserDto> userSearch(HttpServletRequest req, List<UserDto> users){
        var search = req.getParameter("search");
        if(search.isBlank()||search.isEmpty()){
            return users;
        }else {
            return users.stream().filter(o -> o.getFirstName().contains(search) || o.getLastName().contains(search) || o.getBirthday().contains(search) ||
                    o.getEmail().contains(search) || o.getPassword().contains(search)).toList();
        }

    }
}
