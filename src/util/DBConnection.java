/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;
import java.sql.*;

/**
 *
 * @author harsh
 */
public class DBConnection {
    private static Connection con;
    private static final String CLASS_NAME = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/car_rental";
    private static final String USER = "";
    private static final String PASS = "";

    public static Connection getConnection(){
        try{
            Class.forName(CLASS_NAME);
            con = DriverManager.getConnection(URL, USER, PASS);
        }
        catch (Exception e){
           e.printStackTrace();
        }
        return con;
    }
}
