package database.dao;

import database.GetConnection;
import database.entity.Assigned;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AssignedDAO {

    public void insert(Assigned assigned) throws SQLException {

        Connection connection = GetConnection.getConnection();
        PreparedStatement statement = connection.prepareStatement("insert into assigned (team,id,filename) values (?,?,?)");
        statement.setString(1,assigned.getTeam());
        statement.setInt(2,assigned.getId());
        statement.setString(3,assigned.getFilename());
        statement.executeUpdate();

    }

    public Assigned get(int id) throws SQLException {
        Connection connection = GetConnection.getConnection();
        PreparedStatement statement = connection.prepareStatement("select * from assigned where id = ?");
        statement.setInt(1,id);
        ResultSet set = statement.executeQuery();
        if (!set.next()) return null;
        Assigned assigned = new Assigned();
        assigned.setFilename(set.getString("filename"));
        assigned.setId(set.getInt("id"));
        assigned.setTeam(set.getString("team"));
        return assigned;
    }

    public void update(Assigned assigned) throws SQLException {

        Connection connection = GetConnection.getConnection();
        PreparedStatement statement = connection.prepareStatement("update assigned set team=? ,filename=? where id = ? ");
        statement.setString(1, assigned.getTeam());
        statement.setString(2,assigned.getFilename());
        statement.setInt(3,assigned.getId());
        statement.executeUpdate();

    }

    public List<Assigned> allAssigned(String team) throws SQLException {
        Connection connection = GetConnection.getConnection();
        PreparedStatement statement = connection.prepareStatement("select * from assigned where team= ? ");
        statement.setString(1,team);
        ResultSet set = statement.executeQuery();
        List<Assigned> result = new ArrayList<>();
        while(set.next()){
            Assigned assigned = new Assigned();
            assigned.setTeam(set.getString("team"));
            assigned.setId(set.getInt("id"));
            assigned.setFilename(set.getString("filename"));
            result.add(assigned);
        }
        return result;
    }

}
