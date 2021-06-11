/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBmanager {

    private static Connection con;
    private PreparedStatement ps;
    private ResultSet rs;
    static int count = 0;
    static int countClose = 0;
    static int countOpen = 0;

    static {

        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DBmanager.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {

            con = DriverManager.getConnection("jdbc:sqlite:DataBase\\KDB.db");

        } catch (SQLException ex) {
            Logger.getLogger(DBmanager.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static PreparedStatement getPS(String sql) {

        try {
            return con.prepareStatement(sql);

        } catch (SQLException ex) {
            Logger.getLogger(DBmanager.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public static ResultSet exeQuery(String query) {

        try {

            return con.createStatement().executeQuery(query);

        } catch (SQLException ex) {
            Logger.getLogger(DBmanager.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }

    }

    public static int getGroupId(String name) {

        try {

            PreparedStatement ps = getPS("SELECT group_id FROM group_ WHERE group_.name = ? ");
            ps.setString(1, name);
            ps.execute();
            int id = ps.getResultSet().getInt(1);
            return id;

        } catch (SQLException ex) {
            Logger.getLogger(DBmanager.class.getName()).log(Level.SEVERE, null, ex);
            return 0;

        }
    }

    public static int getLastRowId(String name) {
        try {

            return exeQuery("SELECT MAX(rowid) FROM " + name).getInt(1);

        } catch (SQLException ex) {
            Logger.getLogger(DBmanager.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }

}
