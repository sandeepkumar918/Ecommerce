package com.example.ecommerce_d;
import javafx.scene.chart.PieChart;

import java.sql.*;

public class DatabaseConnection {
    Connection con=null;
    String SQLURL="jdbc:mysql://localhost:3306/ecommerce?useSSL=false";
    String userName="root";
    String password="Sandeep@123";
    DatabaseConnection() throws SQLException {
        con= DriverManager.getConnection(SQLURL,userName,password);
        if(con!=null)
            System.out.println("Connected");

    }
    public ResultSet executeQuery(String query) throws SQLException {
        ResultSet result=null;
        try{
            Statement statement=con.createStatement();
            result=statement.executeQuery(query);
            return result;
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }
    public int executeUpdate(String query) throws SQLException {
        int row=0;
        try{
            Statement statement=con.createStatement();
            row=statement.executeUpdate(query);
            return row;
        }catch (Exception e){
            e.printStackTrace();
        }
        return row;
    }
}
