package Dbs.PostgreSQL;

import java.sql.Connection;
import java.sql.DriverManager;

public class PostgreSingleton {
    private final String host = "localhost:5432";
    private final String dbName = "bench";
    private final String user = "bench";
    private final String password = "bench";

    private Connection connection = null;

    public Connection getConnection(){
        if(connection == null) {
            try {
                String connectionUrl = new StringBuilder().append("jdbc:postgresql://")
                        .append(host)
                        .append("/")
                        .append(dbName)
                        .toString();

                connection = DriverManager.getConnection(connectionUrl, user ,password);

                System.out.println("Connection to PostgreSQL established");
            } catch (Exception e) {
                System.out.println("Connection to PostgreSQL Failed! Check output console");
                e.printStackTrace();
            }
        }

        return connection;
    }
}


