package com.eksamen.systems;

import com.eksamen.utils.Logging;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseSystem implements Logging {
    private Connection sqlConnection;

    public DatabaseSystem() {
        lagOppkoblingTabell();
        lagOpprettetRomTabell();
        lagMeldingTabell();
        lagBliMedTabell();
        lagForlatTabell();
    }

    @Override
    public void lagOppkoblingTabell() {
        try {
            sqlConnection = DriverManager.getConnection("jdbc:sqlite:ChatProgramDB.db");
            Statement statement = sqlConnection.createStatement();
            statement.executeUpdate("create table if not exists oppkobling (id integer NOT NULL AUTO_INCREMENT UNIQUE PRIMARY KEY, bruker TEXT, ip TEXT, dato DATETIME)");
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                sqlConnection.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    @Override
    public void lagBliMedTabell() {
        try {
            sqlConnection = DriverManager.getConnection("jdbc:sqlite:ChatProgramDB.db");
            Statement statement = sqlConnection.createStatement();
            statement.executeUpdate("create table if not exists bliMedRom (id integer NOT NULL AUTO_INCREMENT UNIQUE PRIMARY KEY, bruker TEXT, ip TEXT, rom TEXT, dato DATETIME)");
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                sqlConnection.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    @Override
    public void lagForlatTabell() {
        try {
            sqlConnection = DriverManager.getConnection("jdbc:sqlite:ChatProgramDB.db");
            Statement statement = sqlConnection.createStatement();
            statement.executeUpdate("create table if not exists forlatRom (id integer NOT NULL AUTO_INCREMENT UNIQUE PRIMARY KEY, bruker string, ip string, rom string, dato DATETIME)");
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                sqlConnection.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    @Override
    public void lagMeldingTabell() {
        try {
            sqlConnection = DriverManager.getConnection("jdbc:sqlite:ChatProgramDB.db");
            Statement statement = sqlConnection.createStatement();
            statement.executeUpdate("create table if not exists meldinger (id integer NOT NULL AUTO_INCREMENT UNIQUE PRIMARY KEY, bruker string, melding string, ip string, rom string, dato DATETIME)");
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                sqlConnection.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    @Override
    public void lagOpprettetRomTabell() {
        try {
            sqlConnection = DriverManager.getConnection("jdbc:sqlite:ChatProgramDB.db");
            Statement statement = sqlConnection.createStatement();
            statement.executeUpdate("create table if not exists opprettRom (id integer NOT NULL AUTO_INCREMENT UNIQUE PRIMARY KEY, bruker string, ip string, rom string, dato DATETIME)");
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                sqlConnection.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    @Override
    public void loggMelding(String bruker, String melding, String ip, String rom, String dato) {
        try {
            sqlConnection = DriverManager.getConnection("jdbc:sqlite:ChatProgramDB.db");
            Statement statement = sqlConnection.createStatement();
            //id integer NOT NULL AUTO_INCREMENT UNIQUE PRIMARY KEY, bruker string, melding string, ip string, rom string, dato DATETIME
            statement.executeUpdate("insert into meldinger(bruker, melding, ip, rom, dato) values("+bruker+",)");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                sqlConnection.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    @Override
    public void loggLoggInn(String bruker, String melding, String ip, String rom, String dato) {

    }

   // @Override
   // public void loggLoggInn() {
//
   // }

    @Override
    public void loggBliMed() {

    }

    @Override
    public void loggForlat() {

    }

    @Override
    public void loggLoggUt() {

    }

    @Override
    public void logNewRoom() {

    }
}
