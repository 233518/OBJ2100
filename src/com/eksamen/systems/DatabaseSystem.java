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

    /**
     * Metode for å lage oppkoblings tabellen i databasen.
     */
    @Override
    public void lagOppkoblingTabell() {
        try {
            sqlConnection = DriverManager.getConnection("jdbc:sqlite:ChatProgramDB.db");
            Statement statement = sqlConnection.createStatement();
            statement.executeUpdate("create table if not exists oppkobling (id integer NOT NULL AUTO_INCREMENT UNIQUE PRIMARY KEY, bruker TEXT, ip TEXT, dato TEXT)");
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
     * Metode for å lage blimed tabellen i databasen.
     */
    @Override
    public void lagBliMedTabell() {
        try {
            sqlConnection = DriverManager.getConnection("jdbc:sqlite:ChatProgramDB.db");
            Statement statement = sqlConnection.createStatement();
            statement.executeUpdate("create table if not exists bliMedRom (id integer NOT NULL AUTO_INCREMENT UNIQUE PRIMARY KEY, bruker TEXT, ip TEXT, rom TEXT, dato TEXT)");
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
     * Metode for å lage forlat tabellen i databasen.
     */
    @Override
    public void lagForlatTabell() {
        try {
            sqlConnection = DriverManager.getConnection("jdbc:sqlite:ChatProgramDB.db");
            Statement statement = sqlConnection.createStatement();
            statement.executeUpdate("create table if not exists forlatRom (id integer NOT NULL AUTO_INCREMENT UNIQUE PRIMARY KEY, bruker TEXT, ip TEXT, rom TEXT, dato TEXT)");
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
     * Metode for å lage melding tabellen i databasen.
     */
    @Override
    public void lagMeldingTabell() {
        try {
            sqlConnection = DriverManager.getConnection("jdbc:sqlite:ChatProgramDB.db");
            Statement statement = sqlConnection.createStatement();
            statement.executeUpdate("create table if not exists meldinger (id integer NOT NULL AUTO_INCREMENT UNIQUE PRIMARY KEY, bruker TEXT, melding TEXT, ip TEXT, rom TEXT, dato TEXT)");
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
     * Metode for å lage opprettetrom tabellen i databasen.
     */
    @Override
    public void lagOpprettetRomTabell() {
        try {
            sqlConnection = DriverManager.getConnection("jdbc:sqlite:ChatProgramDB.db");
            Statement statement = sqlConnection.createStatement();
            statement.executeUpdate("create table if not exists opprettRom (id integer NOT NULL AUTO_INCREMENT UNIQUE PRIMARY KEY, bruker TEXT, ip TEXT, rom TEXT, dato TEXT)");
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
     * Metode for å logge melding i databasen.
     * @param bruker Brukernavnet i String.
     * @param melding Melding i string.
     * @param ip IP-Adressen i String.
     * @param rom Rom navnet i String.
     * @param dato Dato i String.
     */
    @Override
    public void loggMelding(String bruker, String melding, String ip, String rom, String dato) {
        try {
            sqlConnection = DriverManager.getConnection("jdbc:sqlite:ChatProgramDB.db");
            Statement statement = sqlConnection.createStatement();
            statement.executeUpdate("insert into meldinger(bruker, melding, ip, rom, dato) values('"+bruker+"','"+melding+"','"+ip+"','"+rom+"','"+dato+"')");
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
     * Metode for å logge når noen logger inn i chattesystemet i databasen.
     * @param bruker Brukernavnet i String.
     * @param ip IP-Adressen i String.
     * @param rom Rom navnet i String.
     * @param dato Dato i String.
     */
    @Override
    public void loggLoggInn(String bruker, String ip, String rom, String dato) {
        try {
            sqlConnection = DriverManager.getConnection("jdbc:sqlite:ChatProgramDB.db");
            Statement statement = sqlConnection.createStatement();
            statement.executeUpdate("insert into meldinger(bruker, melding, ip, rom, dato) values('"+bruker+"','"+ip+"','"+rom+"','"+dato+"')");
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
     * Metode for å logge når noen blir med i et rom i databasen.
     * @param bruker Brukernavnet i String.
     * @param ip IP-Adressen i String.
     * @param rom Rom navnet i String.
     * @param dato Dato i String.
     */
    @Override
    public void loggBliMed(String bruker, String ip, String rom, String dato) {
        try {
            sqlConnection = DriverManager.getConnection("jdbc:sqlite:ChatProgramDB.db");
            Statement statement = sqlConnection.createStatement();
            statement.executeUpdate("insert into meldinger(bruker, melding, ip, rom, dato) values('"+bruker+"','"+ip+"','"+rom+"','"+dato+"')");
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
     * Metode for å logge når noen logger inn i databasen.
     * @param bruker Brukernavnet i String.
     * @param ip IP-Adressen i String.
     * @param rom Rom navnet i String.
     * @param dato Dato i String.
     */
    @Override
    public void loggForlat(String bruker, String ip, String rom, String dato) {
        try {
            sqlConnection = DriverManager.getConnection("jdbc:sqlite:ChatProgramDB.db");
            Statement statement = sqlConnection.createStatement();
            statement.executeUpdate("insert into meldinger(bruker, melding, ip, rom, dato) values('"+bruker+"','"+ip+"','"+rom+"','"+dato+"')");
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
     * Metode for å logge når noen logger ut i databasen.
     * @param bruker Brukernavnet i String.
     * @param ip IP-Adressen i String.
     * @param rom Rom navnet i String.
     * @param dato Dato i String.
     */
    @Override
    public void loggLoggUt(String bruker, String ip, String rom, String dato) {
        try {
            sqlConnection = DriverManager.getConnection("jdbc:sqlite:ChatProgramDB.db");
            Statement statement = sqlConnection.createStatement();
            statement.executeUpdate("insert into meldinger(bruker, melding, ip, rom, dato) values('"+bruker+"','"+ip+"','"+rom+"','"+dato+"')");
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
     * Metode for å logge når noen lager ett nytt rom i databasen.
     * @param bruker Brukernavnet i String.
     * @param ip IP-Adressen i String.
     * @param rom Rom navnet i String.
     * @param dato Dato i String.
     */
    @Override
    public void logNewRoom(String bruker, String ip, String rom, String dato) {
        try {
            sqlConnection = DriverManager.getConnection("jdbc:sqlite:ChatProgramDB.db");
            Statement statement = sqlConnection.createStatement();
            statement.executeUpdate("insert into meldinger(bruker, melding, ip, rom, dato) values('"+bruker+"','"+ip+"','"+rom+"','"+dato+"')");
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
}
