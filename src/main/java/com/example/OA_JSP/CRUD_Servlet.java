package com.example.OA_JSP;


import javax.print.attribute.ResolutionSyntax;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

@WebServlet({"/dept/list", "/dept/details", "/dept/delete", "/dept/add", "/dept/alert"})
public class CRUD_Servlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {


        String servletpath = request.getServletPath();
        //这里获取session的作用是为了用户在进行其他操作的时候进行判断其是否已经登录的，所以这里必须传递false,如果没有session也不能创建session对象

        HttpSession session = request.getSession(false);

        if (session != null && session.getAttribute("Account") != null) {
            if ("/dept/list".equals(servletpath)) {
                Getlist(request, response);
            } else if ("/dept/details".equals(servletpath)) {
                Details(request, response);
            } else if ("/dept/delete".equals(servletpath)) {
                Delete(request, response);
            } else if ("/dept/add".equals(servletpath)) {
                Add(request, response);
            } else if ("/dept/alert".equals(servletpath)) {
                Alert(request, response);
            }
        } else {
            response.sendRedirect(request.getContextPath()+"/index.jsp");
        }
    }

    private void Alert(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String Number = request.getParameter("Number");
        String Name = request.getParameter("Name");
        String Location = request.getParameter("Location");

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        ResourceBundle bundle = ResourceBundle.getBundle("DB");
        String driver = bundle.getString("driver");
        String user = bundle.getString("user");
        String url = bundle.getString("url");
        String password = bundle.getString("password");

        int count = 0;

        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(url, user, password);
            String sql = "update person set name=?,location =? where number=?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, Name);
            preparedStatement.setString(2, Location);
            preparedStatement.setString(3, Number);
            count = preparedStatement.executeUpdate();


        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
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
        if (count == 1) {
            String contextPath = request.getContextPath();
            response.sendRedirect(contextPath + "/dept/list");
        }
    }


    private void Add(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String Number = request.getParameter("Number");
        String Name = request.getParameter("Name");
        String Location = request.getParameter("Location");

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        ResourceBundle bundle = ResourceBundle.getBundle("DB");
        String driver = bundle.getString("driver");
        String user = bundle.getString("user");
        String url = bundle.getString("url");
        String password = bundle.getString("password");

        int count = 0;
        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(url, user, password);
            String sql = "insert into person(number,name,location) values(?,?,?)";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, Number);
            preparedStatement.setString(2, Name);
            preparedStatement.setString(3, Location);
            count = preparedStatement.executeUpdate();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
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
        if (count == 1) {
            String contextpath = request.getContextPath();
            response.sendRedirect(contextpath + "/dept/list");
        }

    }

    private void Delete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String number = request.getParameter("number");

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        ResourceBundle bundle = ResourceBundle.getBundle("DB");
        String driver = bundle.getString("driver");
        String user = bundle.getString("user");
        String url = bundle.getString("url");
        String password = bundle.getString("password");

        int count = 0;
        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(url, user, password);
            String sql = "delete from person where number=?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, number);
            count = preparedStatement.executeUpdate();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
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
        if (count == 1) {
            response.sendRedirect("/OA/dept/list");
        }
    }

    private void Details(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String number = request.getParameter("number");

        Dept dept = new Dept();
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
            String sql = "select * from person where number=?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, number);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                String Number = resultSet.getString(1);
                String Name = resultSet.getString(2);
                String Location = resultSet.getString(3);

                dept.setNumber(Number);
                dept.setName(Name);
                dept.setLocation(Location);

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
        request.setAttribute("deptlist", dept);
        String f = request.getParameter("f");
        if ("edit".equals(f)) {
            //转发到修改页面
            request.getRequestDispatcher("/edit.jsp").forward(request, response);
        } else if ("detail".equals(f)) {
            //转发到详情页面
            request.getRequestDispatcher("/details.jsp").forward(request, response);
        }


    }

    //连接数据库，查询所有的数据库信息
    private void Getlist(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<Dept> depts = new ArrayList<Dept>();     //创建一个集合用于存储之后查询出来并封装的数据

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        ResourceBundle bundle = ResourceBundle.getBundle("DB");
        String driver = bundle.getString("driver");
        String url = bundle.getString("url");
        String user = bundle.getString("user");
        String password = bundle.getString("password");

        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(url, user, password);
            String sql = "select * from person";
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String number = resultSet.getString(1);
                String name = resultSet.getString(2);
                String location = resultSet.getString(3);

                Dept dept = new Dept();       //因为每一次查询出来的数据是很繁杂的，所以将其封装进集合对象中
                dept.setNumber(number);
                dept.setName(name);
                dept.setLocation(location);

                depts.add(dept);            //将查询出来的对象放进集合中

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

        request.setAttribute("deptlist", depts);             //将集合封装到请求域中

        request.getRequestDispatcher("/list.jsp").forward(request, response);    //使用转发机制，转发对象对list.jsp
    }
}
