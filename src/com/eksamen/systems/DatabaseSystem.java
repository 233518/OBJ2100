package com.eksamen.systems;

import com.eksamen.components.Logg;
import com.eksamen.systems.loggsystem.LoggSystem;
import com.eksamen.utils.Logging;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Klasse for Databasesystemet som tar vare på alle loggene, samt metode for å vise de.
 */
public class DatabaseSystem implements Logging {
    private Connection sqlConnection;
    private LoggSystem loggSystem;

    public DatabaseSystem(LoggSystem loggSystem) {
        lagTabell();
        this.loggSystem = loggSystem;
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
                loggSystem.refreshLoggSystem();
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

    /**
     * Metode for å få tak i de siste 15 loggene fra databasen.
     * @return Returnerer loggene i form av Logg objekt i en ArrayList.
     */
    public ArrayList<Logg> getLogs() {
        ArrayList<Logg> logg = new ArrayList<Logg>();
        try {
            sqlConnection = DriverManager.getConnection("jdbc:sqlite:ChatProgramDB.db");
            Statement statement = sqlConnection.createStatement();
            ResultSet rs = statement.executeQuery("select * from logg ORDER BY id DESC LIMIT 15");
            while (rs.next()) {
                logg.add(new Logg(rs.getString("dato"),rs.getString("ip"),rs.getString("bruker"),rs.getString("rom"),rs.getString("melding")));
                Collections.sort(logg, new Comparator<Logg>() {
                    @Override
                    public int compare(Logg o1, Logg o2) {
                        return o1.getDato().compareTo(o2.getDato());
                    }
                });
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
}
