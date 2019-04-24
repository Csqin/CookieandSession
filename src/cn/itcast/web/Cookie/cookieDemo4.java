package cn.itcast.web.Cookie;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * cookie在浏览器中保存多长时间
        * 默认情况下，当浏览器关闭后，Cookie数据被销毁setMaxAge()参数为负数
 */
@WebServlet("/cookieDemo4")
public class cookieDemo4 extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
/**
 * 持久化存储：
 * setMaxAge(int seconds)
         1. 正数：将Cookie数据写到硬盘的文件中。持久化存储。并指定cookie存活时间，时间到后，cookie文件自动失效
         2. 负数：默认值
         3. 零：删除cookie信息
 */
        Cookie ck1=new Cookie("msg","hellow");
        //储存cookie数据到硬盘上的文件中，300s后删除。
        //ck1.setMaxAge(300);
        //清除Cookie
        ck1.setMaxAge(0);
        response.addCookie(ck1);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
