package de.immernochnoah.api.mysql;

import de.immernochnoah.api.ServerAPI;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SqlSync {

    public void createPlayer(String name, String uuid) {
        if(!stringExists("uuid" ,uuid)) {
            ServerAPI.mysql.update("INSERT INTO server_api(name, uuid, coins, level, level_xp, next_level_xp) VALUES('"+name+"', '"+uuid+"', '0', '1', '0', '0');");
        }
    }

    public boolean stringExists(String field, String key) {
        try {
            ResultSet rs = ServerAPI.mysql.query("SELECT * FROM server_api WHERE "+ field +"= '"+ key + "'");
            if(rs.next()) {
                return rs.getString(field) != null;
            }
            rs.close();
        } catch (Exception e) { }
        return false;
    }

    public int getMysqlData(String field, String key) {
        ResultSet rs = ServerAPI.mysql.query("SELECT * FROM server_api WHERE uuid= '"+ key + "'");
        try {
            if(rs.next()) {
                return rs.getInt(field);
            }
            rs.close();
        } catch (SQLException e) { e.printStackTrace(); }
        return 0;
    }

    public void updatePlayerMysqlData(String field, String name, String uuid, String data) {
        if(stringExists("uuid" ,uuid)) {
            ServerAPI.mysql.update("UPDATE server_api SET "+ field +"= '" + data + "' WHERE uuid= '"+uuid+"'");
        }else {
            createPlayer(name, uuid);
            updatePlayerMysqlData(field, name, uuid, data);
        }
    }


}
