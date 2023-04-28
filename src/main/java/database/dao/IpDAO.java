package database.dao;

import database.GetConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class IpDAO {

    public ArrayList<String> getIps() throws SQLException {
        Connection connection = GetConnection.getConnection();
        Statement statement = connection.createStatement();
        ResultSet set = statement.executeQuery("select * from ip");
        ArrayList<String> result = new ArrayList<>();
        while (set.next()){
            result.add(set.getString(1));
        }
        return result;
    }

}
