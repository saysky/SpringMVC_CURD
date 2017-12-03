package com.liuyanzhao.blog.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author 言曌
 * @date 2017/12/2 下午9:44
 */

public class DBUtil {

    private static final String URL = "jdbc:mysql://localhost:3306/springmvc_curd"
            + "?useUnicode=true&characterEncoding=utf8";//防止中文读取乱码
    private static final String USER = "root";
    private static final String PASSWORD = "123456";
    public static Connection conn = null;
    //静态代码块最先执行，先执行一次
    static {
        try {
            //加载驱动
            Class.forName("com.mysql.jdbc.Driver");
            //获取连接
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    //单例模式，使对象唯一性
    public static Connection getConnection() {
        return conn;
    }
}
