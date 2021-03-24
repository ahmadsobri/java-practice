package com.java.hikaricp;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class App {
    public static void main(String[] args) throws SQLException {
        HikariDataSource hikariCpConfig, hikariInternalProperties, hikariExternalProperties;

        hikariCpConfig = getHikariDataSource();

        hikariInternalProperties = getHikariDataSourceByProperties();

        HikariConfig config = new HikariConfig("src/main/resources/hikaricp.conf.properties");
        hikariExternalProperties = new HikariDataSource(config);


        printAuthor("hikariCpConfig", hikariCpConfig);
        printAuthor("hikariInternalProperties", hikariInternalProperties);
        printAuthor("hikariExternalProperties", hikariExternalProperties);
    }

    //region Default Using HikariCP Config
    public static HikariDataSource getHikariDataSource(){
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:mysql://localhost:3306/java_practice?useSSL=false");
        config.setUsername("root");
        config.setPassword("root");
        config.addDataSourceProperty("minimumIdle", "5");
        config.addDataSourceProperty("maximumPoolSize", "25");

        HikariDataSource dataSource = new HikariDataSource(config);
        return dataSource;
    }
    //endregion

    //region Config Using Properties
    public static HikariDataSource getHikariDataSourceByProperties(){
        Properties props = new Properties();
        //The MySQL DataSource is known to be broken with respect to network timeout support. Use jdbcUrl configuration instead.
        //props.setProperty("dataSourceClassName", "com.mysql.cj.jdbc.Driver");
        props.setProperty("jdbcUrl", "jdbc:mysql://localhost:3306/java_practice?useSSL=false");
        props.setProperty("dataSource.user", "root");
        props.setProperty("dataSource.password", "root");
        props.setProperty("dataSource.databaseName", "java_practice");

        HikariConfig config = new HikariConfig(props);
        HikariDataSource ds = new HikariDataSource(config);
        return ds;
    }
    //endregion

    public static void printAuthor(String configName, HikariDataSource dataSource) throws SQLException {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            connection = dataSource.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery("select * from author");
            System.out.println("\n============== "+configName+" ================");
            while (resultSet.next()) {
                System.out.println("Id:" + resultSet.getInt("id"));
                System.out.println("Name:" + resultSet.getString("full_name"));
                System.out.println("Age:" + resultSet.getInt("age"));
            }
        } finally {
            // in connection pool, the close mean (back to the pool, not closed)
            resultSet.close();
            statement.close();
            connection.close();
        }
    }

    //Note :
    //you can Config from file.properties, look at : ../resources/hikaricp.conf.properties
    //or you can use Properties Code, use java.util.Properties
}
