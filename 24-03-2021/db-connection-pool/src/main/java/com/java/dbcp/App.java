package com.java.dbcp;

import org.apache.commons.dbcp2.*;
import org.apache.commons.pool2.ObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;


public class App {


    //region Main Class
    public static void main(String[] args) throws SQLException{
        System.out.println("==================Basic Data Source==============");
        showRecordWithBasicDataSource();
        System.out.println("\n==================Pooling Data Source==============");
        showRecordWithPoolingDataSource();
    }
    //endregion

    // with data source pooling
    //<editor-fold desc="Pooling Data Source">
    public static DataSource getPoolingDataSource(){
        Properties properties = new Properties();
        properties.setProperty("user", "root");
        properties.setProperty("password", "root");

        ConnectionFactory connectionFactory = new DriverManagerConnectionFactory("jdbc:mysql://localhost:3306/java_practice?useSSL=false",properties);

        PoolableConnectionFactory poolableConnectionFactory = new PoolableConnectionFactory(connectionFactory, null);

        GenericObjectPoolConfig<PoolableConnection> config = new GenericObjectPoolConfig<>();
        config.setMaxTotal(25);
        config.setMaxIdle(10);
        config.setMinIdle(5);

        ObjectPool<PoolableConnection> connectionPool = new GenericObjectPool<>(poolableConnectionFactory, config);
        poolableConnectionFactory.setPool(connectionPool);

        DataSource dataSource = new PoolingDataSource<>(connectionPool);
        return dataSource;
    }

    public static void showRecordWithPoolingDataSource() throws SQLException {
        DataSource dataSource = getPoolingDataSource();
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            connection = dataSource.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery("select * from author");
            while (resultSet.next()) {
                System.out.println("Id:" + resultSet.getInt("id"));
                System.out.println("Name:" + resultSet.getString("full_name"));
                System.out.println("Age:" + resultSet.getInt("age"));
            }
        }finally {
            resultSet.close();
            statement.close();
            connection.close();
        }
    }
    //</editor-fold>

    //with basic data source
    //<editor-fold desc="Basic Data Source">
    public static BasicDataSource getBasicDataSource(){
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setUrl("jdbc:mysql://localhost:3306/java_practice?useSSL=false");
        dataSource.setUsername("root");
        dataSource.setPassword("root");

        dataSource.setMinIdle(5);
        dataSource.setMaxIdle(10);
        dataSource.setMaxTotal(25);
        return dataSource;
    }

    public static void showRecordWithBasicDataSource() throws SQLException {
        BasicDataSource dataSource = getBasicDataSource();
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            connection = dataSource.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery("select * from author");
            while (resultSet.next()) {
                System.out.println("Id:" + resultSet.getInt("id"));
                System.out.println("Name:" + resultSet.getString("full_name"));
                System.out.println("Age:" + resultSet.getInt("age"));
            }
        }finally
        {
            resultSet.close();
            statement.close();
            connection.close();
        }
    }
    //</editor-fold>
}
