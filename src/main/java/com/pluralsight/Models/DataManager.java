package com.pluralsight.Models;

import org.apache.commons.dbcp2.BasicDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class DataManager {

    private static BasicDataSource dataSource;

    static {
        dataSource = new BasicDataSource();
        dataSource.setUrl("jdbc:mysql://localhost:3306/CarDealershipDatabase");

        String user = System.getProperty("db.user");
        String pass = System.getProperty("db.pass");

        dataSource.setUsername(user);
        dataSource.setPassword(pass);
    }


    public static Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

    public static DataSource getDataSource() {
        return dataSource;
    }
}
