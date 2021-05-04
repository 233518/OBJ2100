package com.eksamen.systems;

import com.eksamen.utils.Logging;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseSystem implements Logging {
    private Connection con;

    public DatabaseSystem() {
        try {
            DriverManager.getConnection("jdbc:sqlite:demo.db");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    @Override
    public void logMessage() {

    }

    @Override
    public void logLogin() {

    }

    @Override
    public void logJoin() {

    }

    @Override
    public void logLeave() {

    }

    @Override
    public void logLogout() {

    }
}
