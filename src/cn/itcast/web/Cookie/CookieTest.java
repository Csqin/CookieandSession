package cn.itcast.web.Cookie;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.crypto.Data;
import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.SimpleTimeZone;

/**
 * 	6. 案例：记住上一次访问时间
         1. 需求：
                 1. 访问一个Servlet，如果是第一次访问，则提示：您好，欢迎您首次访问。
                 2. 如果不是第一次访问，则提示：欢迎回来，您上次访问时间为:显示时间字符串

         2. 分析：
                 1. 可以采用Cookie来完成
                 2. 在服务器中的Servlet判断是否有一个名为lastTime的cookie
         1. 有：不是第一次访问
                 1. 响应数据：欢迎回来，您上次访问时间为:2018年6月10日11:50:20
                 2. 写回Cookie：lastTime=2018年6月10日11:50:01
         2. 没有：是第一次访问
             1. 响应数据：您好，欢迎您首次访问
             2. 写回Cookie：lastTime=2018年6月10日11:50:01
 */
@WebServlet("/CookieTest")
public class CookieTest extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //设置响应体的数据格式及编码
        response.setContentType("text/html;charset=utf-8");
        //创建Cookie对象
        Cookie[] ck = request.getCookies();
        boolean flag=false;//没有cookie为lastTime
       //当Cookie数组中的值不为空并且长度大于0时进行遍历
        if ( ck != null || ck.length>0)
        {
            for (Cookie cookie : ck) {
                String name = cookie.getName();

                if ("lastTime".equals(name))
                {
                    //有：不是第一次访问
                    flag=true;//有lastTime的cookie
                    //存在lastTime的Cookie，获取其值输出到页面上
                    String value = cookie.getValue();
                    //输出前将value进行URL解码
                    String values = URLDecoder.decode(value, "utf-8");
                    response.getWriter().write("欢迎回来，您上次访问时间为:"+values);

                    // //获取当前时间的字符串，重新设置Cookie的值，重新发送cookie

                    //获取到美国形式的日期
                    Date date=new Date();
                    System.out.println(date);
                    System.out.println("----------------------");
                    //设置为中国形式的日期形式
                     SimpleDateFormat sdf=new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
                    String dates = sdf.format(date);
                    //特殊字符需要进行URL转码
                    String str_value = URLEncoder.encode(dates,"utf-8");
                    //重新赋值
                    cookie.setValue(str_value);
                    //设置Cookie的存活时间,1个月
                    cookie.setMaxAge(60*60*24*30);
                    //重新发送cookie
                    response.addCookie(cookie);
                    //找到目标cookie，下面的cookie就不用遍历啦
                    break;
                }
            }

        }

        if (ck==null || ck.length==0 || flag==false)
        {
            /**
                 * 2. 没有：是第一次访问
                         1. 响应数据：您好，欢迎您首次访问
                         2. 写回Cookie：lastTime=2018年6月10日11:50:01
             */
            //获取到美国形式的日期
            Date date=new Date();
            //设置为中国形式的日期形式
            SimpleDateFormat sdf=new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
            String dates = sdf.format(date);
            //特殊字符需要进行URL转码
            String str_value = URLEncoder.encode(dates,"utf-8");
            Cookie cookie=new Cookie("lastTime",str_value);
            //设置Cookie的存活时间,1个月
            cookie.setMaxAge(60*60*24*30);
            //发送cookie
            response.addCookie(cookie);
            response.getWriter().write("您好，欢迎您首次访问");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
