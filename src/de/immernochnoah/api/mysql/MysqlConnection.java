package de.immernochnoah.api.mysql;

import org.bukkit.Bukkit;

import java.sql.*;

public class MysqlConnection {

    private String HOST = "";
    private String PORT = "";
    private String DATABASE = "";
    private String USER = "";
    private String PASSWORD = "";

    private Connection con;

    public MysqlConnection(String host, String port, String database, String user, String password) {
        this.HOST = host;
        this.PORT = port;
        this.DATABASE = database;
        this.USER = user;
        this.PASSWORD = password;

        connect();
    }

    public void connect() {
        try {
            con = DriverManager.getConnection("jdbc:mysql://" + HOST + ":" + PORT+"/" + DATABASE + "?autoReconnect=true", USER, PASSWORD);
            Bukkit.getConsoleSender().sendMessage("Die Verbindung zur MySQL wurde hergestellt!");
        } catch (SQLException e) {

            Bukkit.getConsoleSender().sendMessage("Die Verbindung zur MySQL ist fehlgeschlagen!");
        }
    }

    public void close() {
        try {
            if(con != null) {
                con.close();

                Bukkit.getConsoleSender().sendMessage("Die Verbindung zur MySQL wurde Erfolgreich beendet");
            }
        } catch (SQLException e) {
            Bukkit.getConsoleSender().sendMessage("Fehler beim beenden der Verbindung zur MySQL!");

        }
    }

    public void update(String qry) {
        try {
            Statement st = con.createStatement();
            st.executeUpdate(qry);
        } catch (SQLException e) {
            connect();
            System.err.println(e);
        }
    }

    public ResultSet query(String qry) {
        ResultSet rs = null;

        try {
            Statement st = con.createStatement();
            rs = st.executeQuery(qry);
        } catch (SQLException e) {
            connect();
            System.err.println(e);
        }
        return rs;
    }
}