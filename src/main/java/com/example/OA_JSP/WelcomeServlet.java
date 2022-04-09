package com.example.OA_JSP;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.*;
import java.util.Map;
import java.util.ResourceBundle;

@WebServlet("/welcome")
public class WelcomeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        //在欢迎页面进行cookie的获取，如果能够获取到需要的账户和密码的值，就通过cookie进行免登录
        String cookie1 = request.getHeader("Cookie");
        System.out.println(cookie1);
        Cookie[] cookies = request.getCookies();
        String getAccount = null;
        String getPassword = null;
        if (cookies != null&&cookies.length>0) {
            for (Cookie cookie : cookies) {
                String name = cookie.getName();
                if ("Account".equals(name)) {
                    getAccount = cookie.getValue();
                } else if ("Password".equals(name)) {
                    getPassword = cookie.getValue();
                }
            }
        } else {
            response.sendRedirect(request.getContextPath() + "/index.jsp");

        }
        //这里必须要进行这样的验证，因为这两个变量是用户第一次登录的时候输入的账户和密码，只能同时为空或同时不为空
        //但是并不代表这里拿到的数据就可以直接登录成功，因为这十天内用户可能更换了密码，所以要与数据库进行比对
        if (getAccount != null && getPassword != null) {
            Connection connection = null;
            PreparedStatement preparedStatement = null;
            ResultSet resultSet = null;
            ResourceBundle bundle = ResourceBundle.getBundle("DB");
            String driver = bundle.getString("driver");
            String user = bundle.getString("user");
            String url = bundle.getString("url");
            String password = bundle.getString("password");
            boolean success = false;
            try {
                Class.forName(driver);
                connection = DriverManager.getConnection(user, url, password);
                String sql = "select * from user where account=? and pasword=?";
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, getAccount);
                preparedStatement.setString(2, getPassword);
                resultSet = preparedStatement.executeQuery();
                if (resultSet.next()) {             //查询结果集中有数据，即cookie中存储的登录账号和密码都是和数据库中匹配的
                    success = true;
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException e) {
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
            if (success == true) {
                //这里虽然是自动登录，但也是用户登录的一个行为，也是要创建一个session对象的
                //因为后面的代码中，只要是访问子页面就要判定是否有session对象
                HttpSession session = request.getSession();
                session.setAttribute("Account", getAccount);
                response.sendRedirect(request.getContextPath() + "/dept/list");
            }else{
                response.sendRedirect(request.getContextPath() +"/index.jsp");
            }
        }
    }
}
