package com.example.airlinereservastionsystem_team_1;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class DataBaseConnection {
        static String user = "root";
        static String password = "gopinath";
        static String url = "jdbc:mysql://localhost:3306/airlinereservationsystem";
        static String driver = "com.mysql.cj.jdbc.Driver";
        public static Connection getConnection(){
            Connection connection = null;
            try {
                Class.forName(driver);
            }
            catch(ClassNotFoundException e){
                throw new RuntimeException(e);
            }
            try {
                connection = DriverManager.getConnection(url, user, password);
            }
            catch(SQLException e){
                throw new RuntimeException(e);
            }
            return connection;
        }
}
