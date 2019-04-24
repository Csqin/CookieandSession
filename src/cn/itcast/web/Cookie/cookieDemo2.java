package cn.itcast.web.Cookie;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * cookie获取输出
 */
@WebServlet("/cookieDemo2")
public class cookieDemo2 extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       //获取浏览器发送的cookie，并且遍历cookie数组
        Cookie[] cks = request.getCookies();
        if (cks!=null)
        {
            for (Cookie ck : cks) {
                String name = ck.getName();
                String value = ck.getValue();
                System.out.println(name+":"+value);
            }
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
