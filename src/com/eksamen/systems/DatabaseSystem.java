package com.eksamen.systems;

import com.eksamen.components.Logg;
import com.eksamen.systems.loggsystem.LoggSystem;
import com.eksamen.utils.Logging;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class DatabaseSystem implements Logging {
    private Connection sqlConnection;
    private LoggSystem loggSystem;

    public DatabaseSystem(LoggSystem loggSystem) {
        lagTabell();
        this.loggSystem = loggSystem;
    }
    public DatabaseSystem() {
        lagTabell();
    }

    /**
     * Metode for å lage logg tabell i databasen.
     */
    @Override
    public void lagTabell() {
        try {
            sqlConnection = DriverManager.getConnection("jdbc:sqlite:ChatProgramDB.db");
            Statement statement = sqlConnection.createStatement();
            statement.executeUpdate("create table if not exists logg (id integer NOT NULL UNIQUE PRIMARY KEY, bruker TEXT, melding TEXT, ip TEXT, rom TEXT, dato TEXT)");
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                sqlConnection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    /**
     * Metode for å logge ting i databasen.
     * @param bruker Brukernavnet i String.
     * @param melding Melding i string.
     * @param ip IP-Adressen i String.
     * @param rom Rom navnet i String.
     */
    @Override
    public void logg(String bruker, String melding, String ip, String rom) {
        try {
            sqlConnection = DriverManager.getConnection("jdbc:sqlite:ChatProgramDB.db");
            Statement statement = sqlConnection.createStatement();
            statement.executeUpdate("insert into logg(bruker, melding, ip, rom, dato) values('"+bruker+"','"+melding+"','"+ip+"','"+rom+"','"+ LocalDateTime.now().toString()+"')");
            //Denne gir feilmelding
            if(loggSystem != null) {
                loggSystem.oppdaterTableView();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                sqlConnection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public ArrayList<Logg> getLogs() {
        ArrayList<Logg> logg = new ArrayList<Logg>();
        try {
            sqlConnection = DriverManager.getConnection("jdbc:sqlite:ChatProgramDB.db");
            Statement statement = sqlConnection.createStatement();
            ResultSet rs = statement.executeQuery("select * from logg ORDER BY id DESC LIMIT 10");
            while (rs.next()) {
                logg.add(new Logg(rs.getString("dato"),rs.getString("ip"),rs.getString("bruker"),rs.getString("rom"),rs.getString("melding")));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            try {
                sqlConnection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return logg;
    }

    //Midlertidig metode for å printe innholdet i databasen.
    public static void print() {
        try {
            Connection con = DriverManager.getConnection("jdbc:sqlite:ChatProgramDB.db");
            System.out.println("Koblet til Databasen!");
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery("select * from logg");
            System.out.println("Innhold i databasen:");
            while (rs.next()) {
                // Skriver ut innholdet i konsollvinduet
                System.out.print(" | " + "ID = " + rs.getInt("id"));
                System.out.print(" | " + "Bruker = " + rs.getString("bruker"));
                System.out.print(" | " + "Melding = " + rs.getString("melding"));
                System.out.print(" | " + "IP = " + rs.getString("ip"));
                System.out.print(" | " + "Rom: = " + rs.getString("rom"));
                System.out.print(" | " + "Dato = " + rs.getString("dato"));
                System.out.println();
                System.out.println(" -------------------------------------------");
            }
            con.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

}
