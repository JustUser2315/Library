package filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Set;

@WebFilter("/*")
public class AuthorizationFilter implements Filter {
    private static final Set<String> PUBLIC_PAGES = Set.of("/login", "/seeAllBooks", "/registration", "/images", "/", "/locale", "/startPage");
    private static final Set<String> ADMIN_PAGES = Set.of("/deleteBook", "/adminOptions", "/seeMyReadingList", "/seeUsersBook", "/logout", "/deleteUser", "/addBookToLibrary", "/seeAllUsers");
    private static final Set<String> USER_PAGES = Set.of("/deleteMyBook", "/userOptions", "/seeMyReadingList", "/logout", "/addBookToOwnLibrary");

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        var uri = ((HttpServletRequest) servletRequest).getRequestURI();
        if (isPublicPath(uri)) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else if (isAdminPath(uri) && adminIsLogged(servletRequest)) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else if (isUserPath(uri) && userIsLogged(servletRequest)) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            ((HttpServletResponse) servletResponse).sendRedirect("/");
        }
    }

    private boolean userIsLogged(ServletRequest servletRequest) {
        var user = ((HttpServletRequest) servletRequest).getSession().getAttribute("user");
        return user != null;
    }

    private boolean adminIsLogged(ServletRequest servletRequest) {
        var admin = ((HttpServletRequest) servletRequest).getSession().getAttribute("admin");
        return admin != null;
    }

    private boolean isPublicPath(String uri) {
        return PUBLIC_PAGES.stream().anyMatch(e -> e.startsWith(uri));
    }

    private boolean isAdminPath(String uri) {
        return ADMIN_PAGES.stream().anyMatch(e -> e.startsWith(uri));
    }

    private boolean isUserPath(String uri) {
        return USER_PAGES.stream().anyMatch(e -> e.startsWith(uri));
    }
}
