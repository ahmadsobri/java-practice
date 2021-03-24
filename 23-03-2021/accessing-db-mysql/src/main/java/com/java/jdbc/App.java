package com.java.jdbc;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class App {
    static String currentDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime());

    public static void main(String[] args) {
        addArticle();
        //updateArticle("Tips Menamam Sayur Bayam", "menanam bayam untuk pemula");
        getAllArticle();
        //deleteArticle(5);
    }

    public static Connection getConnection(){
        String dbURL = "jdbc:mysql://localhost:3306/java_practice?useSSL=false";
        String username = "root";
        String password = "root";
        Connection conn = null;

        try {
            conn = DriverManager.getConnection(dbURL, username, password);

            if (conn != null) {
                System.out.println("Connected");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return conn;
    }

    public static int addAuthor() {
        int last_inserted_id = 0;
        try (Connection conn = getConnection()) {
            String sql = "INSERT INTO author (id, full_name, age) VALUES (default, ?, ?)";

            PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, "ahmad sobri");
            statement.setInt(2, 17);

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("A new data was inserted successfully!");
                ResultSet rs = statement.getGeneratedKeys();
                if(rs.next())
                {
                    last_inserted_id = rs.getInt(1);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return last_inserted_id;
    }

    public static void addArticle(){
        try (Connection conn = getConnection()) {
            String sql = "INSERT INTO article (id, title, description, created, author_id) VALUES (default, ?, ?, ?, ?)";
            String sql_author = "SELECT id FROM author ORDER BY id DESC LIMIT 1";

            Statement stm = conn.createStatement();
            ResultSet result = stm.executeQuery(sql_author);
            int author_id = result.next() ? result.getInt("id") : addAuthor();

            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, "Tips Menanam Cabai Rawit");
            statement.setString(2, "sekumpulan tips untuk pemula menanam cabai rawit");
            statement.setString(3, currentDate);
            statement.setInt(4, author_id);

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("A new data was inserted successfully!");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public static void getAllArticle(){
        try (Connection conn = getConnection()) {
            String sql = "SELECT A.*, B.full_name FROM article " +
                            "A INNER JOIN author B ON A.author_id = B.id";

            Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery(sql);

            int count = 0;
            while (result.next()){
                int id = result.getInt("id");
                String title = result.getString("title");
                String description = result.getString("description");
                Date created = result.getDate("created");
                String full_name = result.getString("full_name");

                String output = "%d: %s - %s - %s - %s - %s";
                System.out.println(String.format(output, ++count, id, title, description, created, full_name));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public static void updateArticle(String title, String description){
        try (Connection conn = getConnection()) {
            String sql = "UPDATE article SET title=?, description=?";

            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, title);
            statement.setString(2, description);

            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("An existing data was updated successfully!");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public static void deleteArticle(int id){
        try (Connection conn = getConnection()) {
            String sql = "DELETE FROM article WHERE id=?";

            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, id);

            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("A data was deleted successfully!");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}