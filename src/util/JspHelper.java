package util;

import lombok.experimental.UtilityClass;

@UtilityClass
public class JspHelper {
    private static final String FORMAT_ADMIN = "WEB-INF/jsp/admin/%s.jsp";
    private static final String FORMAT_USER = "WEB-INF/jsp/user/%s.jsp";
    private static final String FORMAT = "WEB-INF/jsp/%s.jsp";

    public static String  getAdminFilePath(String fileName){
        return String.format(FORMAT_ADMIN,fileName);
    }
    public static String  getUserFilePath(String fileName){
        return String.format(FORMAT_USER,fileName);
    }
    public static String  getPath(String fileName){
        return String.format(FORMAT,fileName);
    }
}
