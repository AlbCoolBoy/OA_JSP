package com.example.OA_JSP;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.*;
import java.util.ResourceBundle;



@WebServlet({"/user/login", "/user/exit"})
public class Login extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String servletPath = request.getServletPath();
        if ("/user/login".equals(servletPath)) {
            doLogin(request, response);
        } else if ("/user/exit".equals(servletPath)) {
            doExit(request, response);
        }

    }

    private void doExit(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if(session!=null){
            //手动销毁session的方法
            session.invalidate();
        }
        String contextPath = request.getContextPath();
        response.sendRedirect(contextPath+"/quit.jsp");
    }


    protected void doLogin(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        boolean success = false;        //判断登录成功与否的标志


        String Account = request.getParameter("account");
        String Password = request.getParameter("password");

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        ResourceBundle bundle = ResourceBundle.getBundle("DB");
        String driver = bundle.getString("driver");
        String user = bundle.getString("user");
        String url = bundle.getString("url");
        String password = bundle.getString("password");

        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(url, user, password);
            String sql = "select * from user where account=? and password=?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, Account);
            preparedStatement.setString(2, Password);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                success = true;
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        /*下面代码的逻辑h
        * 1.使用session机制，只有用户成功登陆了才能进行一系列的操作，即使用户直接向地址栏输入正确的请求地址
        * 2.使用cookie机制，只有用户成功登录并且选择了十天内免登录的选项，就会将用户的登录信息放进cookie中，并设置10天的过期时间
        * 并且使用cookie的时候，还要人为设置cookie的路径，将cookie响应给浏览器*/
        if (success) {      //登录成功
            //用户登录的时候是必须要获取session对象的，即使没有也要新建一个session对象
            HttpSession session = request.getSession();
            session.setAttribute("Account", Account);
            String f=request.getParameter("f");
            String host=request.getHeader("host");

            //只有在用户登录成功的情况下才会考虑使用cookie，否则没有意义
            //下面的代码是通过创建cookie对象并向其中绑定登录数据以实现一个十天内自动登录的功能
            if("y".equals(f)){
                Cookie cookie_Account=new Cookie("Account",Account);
                Cookie cookie_Password=new Cookie("Password",Password); //真实情况下这里的密码是加密的
                //设置cookie的过期时间为十天
                cookie_Account.setMaxAge(864000);
                cookie_Password.setMaxAge(864000);
                //设置cookie路径
                cookie_Account.setPath("/");
                cookie_Password.setPath("/");


                //一定要记得响应cookie给浏览器
                response.addCookie(cookie_Account);
                response.addCookie(cookie_Password);

            }

            String contextPath = request.getContextPath();
            response.sendRedirect(contextPath + "/dept/list");
        } else {
            response.sendRedirect("/OA/error.jsp");
        }


    }
}
