package database;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class GetConnection {


    static Connection connection = null;
    static Properties properties = new Properties();
    static private String host;
    static private String port;
    static private String database;
    static private String prefix;
    static private String username;
    static private String password;

    private static void init(){


        try {
            properties.load(GetConnection.class.getResourceAsStream("/resources/application.properties"));
            host = properties.getProperty("host");
            port = properties.getProperty("port");
            database = properties.getProperty("database");
            prefix = properties.getProperty("prefix");
            username = properties.getProperty("db_username");
            password = properties.getProperty("db_password");


        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    static public Connection getConnection() throws SQLException {

        if(connection != null && !connection.isClosed())  return connection;

        init();

        try {

            Class.forName("com.mysql.jdbc.Driver");
        }
        catch (Exception e){

        }
        connection = DriverManager.getConnection(
                prefix+host+":"+port+"/"+database,
                username,
                password
        );

        return connection;
    }

    public static Properties getProperties(){
        if (properties.size() == 0 ) {
            init();
        }
        return properties;
    }


}
