package cn.itcast.web.Cookie;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 传递多个cookie
 */
@WebServlet("/cookieDemo3")
public class cookieDemo3 extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //
        Cookie ck1=new Cookie("msg","hellow");
        Cookie ck2=new Cookie("msg2","你好呀。");

        response.addCookie(ck1);
        response.addCookie(ck2);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
