package com.mitsko.mrdb.dao.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;


public class ConnectionPool {
    private static final ConnectionPool instance = new ConnectionPool();
    private PropertiesManager manager;

    private String driverName;
    private String url;
    private String user;
    private String password;
    private int poolSize;

    private BlockingQueue<Connection> connectionsQueue;
    private BlockingQueue<Connection> givenAwayConQueue;

    private ConnectionPool() {
        manager = new PropertiesManager();

        driverName = manager.getValue(Parameter.DB_DRIVER);
        url = manager.getValue(Parameter.DB_URL);
        user = manager.getValue(Parameter.DB_USER);
        password = manager.getValue(Parameter.DB_PASSWORD);
        poolSize = Integer.parseInt(manager.getValue(Parameter.DB_POOL_SIZE));

        createPool();
    }

    public static ConnectionPool getInstance() {
        return instance;
    }

    private void createPool() {
        try {
            Class.forName(driverName);

            connectionsQueue = new ArrayBlockingQueue<>(poolSize);
            givenAwayConQueue = new ArrayBlockingQueue<>(poolSize);

            for(int i = 0; i < poolSize; i++) {
                Connection connection = DriverManager.getConnection(url, user, password);
                connectionsQueue.add(connection);
            }

        } catch(ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
        }
    }

    public Connection getConnection() {
        Connection connection = connectionsQueue.remove();
        givenAwayConQueue.add(connection);
        return connection;
    }

    public boolean releaseConnection(Connection connection) {
        connectionsQueue.add(connection);
        return givenAwayConQueue.remove(connection);
    }
}
