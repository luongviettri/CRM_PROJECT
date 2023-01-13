package config;

import java.sql.Connection;

public class MysqlConfig {

    private static String DRIVER_NAME = "com.mysql.cj.jdbc.Driver";
    private static String URL = "jdbc:mysql://localhost:3307/crm_app";
    private static String USER_NAME = "root";
    private static String PASSWORD = "1234";

    public static Connection getConnection() {
        Connection connection = null;
        //! đăng kí sử dụng driver cho mySQL
        Class.forName(DRIVER_NAME);

        return connection;
    }
}
