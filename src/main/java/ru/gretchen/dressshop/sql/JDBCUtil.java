package ru.gretchen.dressshop.sql;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class JDBCUtil {

    private static final String DB_NAME = "java:comp/env/jdbc/dressShop";

    private static DataSource dataSource;
    private static Connection connection;

    public JDBCUtil() {
        try {
            Context context = new InitialContext();
            dataSource = (DataSource) context.lookup(DB_NAME);
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() throws IOException, SQLException, ClassNotFoundException {
        return dataSource.getConnection();
    }

    public void disconnect() throws SQLException {
        if (connection != null) {
            connection.close();
        }
    }
}
