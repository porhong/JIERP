/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.sql.*;

/**
 *
 * @author vanreach
 */
public class Database {

    public static Connection con;
    public static PreparedStatement s;
    public static ResultSet rs;
    public static void connectionDb(String ip, String database, String userName, String password)throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
        con = DriverManager.getConnection("jdbc:mysql://" + ip + "/" + database, userName, password);
        
    }

}
