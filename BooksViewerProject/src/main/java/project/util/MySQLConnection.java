package project.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLConnection {

    /**
     * kullanıcı 1 ->
     * kullanıcı 2 ->
     * kullanıcı 1 ve 2 izmirde bulunan ortak bir pc veya aynı evde oldukları bir cihazın pc'sinin, localhost database'ine ULAŞMALI.
     */
    private static final String DB_URL = "jdbc:mysql://localhost:3306/books";
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "rootroot";

    private MySQLConnection(){

    }


    public static Connection create() throws SQLException {
        return DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
    }
}