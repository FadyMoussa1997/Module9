
package com.example.demodemo;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Books {

    private static final String tableName = "books" ;

    private int id ;
    private String title ;
    private String author ;
    private String genre ;
    private String status ;
    private LocalDate dueDate;


public static boolean addBook (Connection connection, String title, String author, String genre) {

    try{

        PreparedStatement add = connection.prepareStatement("INSERT INTO "+tableName+" (title , author , genre) \n" +
                "VALUES (? , ? , ?)");

        add.setString(1, title);
        add.setString(2, author);
        add.setString(3, genre);
        int rowsaffected = add.executeUpdate();
        return rowsaffected > 0;


    } catch (SQLException e) {
e.printStackTrace();
return false;

    }

}


public static boolean deleteBook (Connection connection, int id) {

    try {
        PreparedStatement delete = connection.prepareStatement("DELETE FROM books WHERE id = ?") ;
        delete.setInt(1, id);
        int rowsaffected = delete.executeUpdate() ;
        return rowsaffected > 0 ;

    } catch (SQLException e) {
        e.printStackTrace();
        return false ;
    }

}


    public static String displayBooks(Connection connection) {
        StringBuilder result = new StringBuilder();

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM books");

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String title = resultSet.getString("title");
                String author = resultSet.getString("author");
                String genre = resultSet.getString("genre");
                String status = resultSet.getString("status") ;
                String due_date = resultSet.getString("due_date") ;

                // Append book information to the result string
                result.append(" ").append(id).append(",        ").append(title)
                        .append(",        ").append(author).append(",        ").append(genre).append(",         ")
                        .append(status).append(",            ").append(due_date)
                        .append("\n");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result.toString();
    }

















}
