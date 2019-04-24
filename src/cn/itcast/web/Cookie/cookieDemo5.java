package cn.itcast.web.Cookie;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
     cookie共享问题？
         1. 假设在一个tomcat服务器中，部署了多个web项目，那么在这些web项目中cookie能不能共享？
                 * 默认情况下cookie不能共享

                 * setPath(String path):设置cookie的获取范围。默认情况下，设置当前的虚拟目录
                 * 如果要共享，则可以将path设置为"/"
 */
@WebServlet("/cookieDemo5")
public class cookieDemo5 extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Cookie ck1=new Cookie("msg","hellow");
        ck1.setPath("/");
        response.addCookie(ck1);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
