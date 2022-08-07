package database;

import org.apache.commons.configuration.PropertiesConfiguration;

import java.sql.*;

public class ConnectionManager {

    private PropertiesConfiguration databaseProperties = new PropertiesConfiguration();
    private Connection connection;

    public ConnectionManager(){
        try{
            databaseProperties.load("database.properties");
            this.setupConnection();
        } catch (Exception exception) {
            System.out.println(exception.getClass() + ": " + exception.getMessage());
        }
    }

    private void setupConnection(){
        String connectionUrl = databaseProperties.getString("database.host") + ":"
                + databaseProperties.getString("database.port") + "/"
                + databaseProperties.getString("database.databaseName");

        try {
            this.connection = DriverManager.getConnection(
                    connectionUrl,
                    databaseProperties.getString("database.username"),
                    databaseProperties.getString("database.password")
            );
        } catch (SQLException exception) {
            System.out.println(exception.getClass() + ": " + exception.getMessage());
        }
    }

    public Connection getConnection(){
        try{
            if (connection == null || this.connection.isClosed()) this.setupConnection();
        } catch (SQLException exception){
            System.out.println(exception.getClass() + ": " + exception.getMessage());
        }
        return connection;
    }

    public void closeOpenConnections(Connection connection, PreparedStatement statement, ResultSet resultSet) {
        try {
            if (connection != null) connection.close();
            if (connection != null) statement.close();
            if (connection != null) resultSet.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}