package com.sample.di.demo7;

import java.sql.Connection;
import java.sql.SQLException;

public class EmployeeDao {

    private String driverClassName;
    private String url;
    private String username;
    private String password;

    private Connection getConnection() throws SQLException {

        return null;
    }
}
