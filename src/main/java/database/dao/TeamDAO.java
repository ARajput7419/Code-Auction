package database.dao;

import database.GetConnection;
import database.entity.Team;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TeamDAO {

    public void insert(Team team) throws SQLException {

        Connection connection = GetConnection.getConnection();
        PreparedStatement statement = connection.prepareStatement("insert into team (name,email,points,secret,enable,done) values (?,?,?,?,?,?)");
        statement.setString(1,team.getName());
        statement.setString(2,team.getEmail());
        statement.setInt(3,team.getPoints());
        statement.setString(4,team.getSecret());
        statement.setBoolean(5,team.isEnable());
        statement.setInt(6,team.getDone());
        statement.executeUpdate();

    }

    public Team getByEmail(String email) throws SQLException {
        Connection connection = GetConnection.getConnection();
        PreparedStatement statement = connection.prepareStatement("select * from team where email = ?");
        statement.setString(1,email);
        ResultSet set = statement.executeQuery();
        if (!set.next()) return null;
        Team team = new Team();
        team.setName(set.getString("name"));
        team.setPoints(set.getInt("points"));
        team.setEnable(set.getBoolean("enable"));
        team.setSecret(set.getString("secret"));
        team.setEmail(set.getString("email"));
        team.setDone(set.getInt("done"));
        return team;
    }

    public List<Team> getAllTeams() throws SQLException {
        Connection connection = GetConnection.getConnection();
        Statement statement = connection.createStatement();
        ResultSet set = statement.executeQuery("select * from team");
        List<Team> results = new ArrayList<>();
        while(set.next()){
            Team team = new Team();
            team.setSecret(set.getString("secret"));
            team.setEnable(set.getBoolean("enable"));
            team.setEmail(set.getString("email"));
            team.setName(set.getString("name"));
            team.setPoints(set.getInt("points"));
            team.setDone(set.getInt("done"));
            results.add(team);
        }
        return results;
    }


    public Team get(String name) throws SQLException {
        Connection connection = GetConnection.getConnection();
        PreparedStatement statement = connection.prepareStatement("select * from team where name = ?");
        statement.setString(1,name);
        ResultSet set = statement.executeQuery();
        if (!set.next()) return null;
        Team team = new Team();
        team.setName(set.getString("name"));
        team.setPoints(set.getInt("points"));
        team.setEnable(set.getBoolean("enable"));
        team.setSecret(set.getString("secret"));
        team.setEmail(set.getString("email"));
        team.setDone(set.getInt("done"));
        return team;
    }

    public void enable(int count) throws SQLException {
        Connection connection = GetConnection.getConnection();
        Statement statement = connection.createStatement();
        statement.executeUpdate("update team set enable = 1 where done >="+count);

    }

    public void update(Team team) throws SQLException {

        Connection connection = GetConnection.getConnection();
        PreparedStatement statement = connection.prepareStatement("update team set email=? ,points=?,secret=?,enable=?,done=? where name = ? ");
        statement.setString(6,team.getName());
        statement.setString(1,team.getEmail());
        statement.setInt(2,team.getPoints());
        statement.setString(3,team.getSecret());
        statement.setBoolean(4,team.isEnable());
        statement.setInt(5,team.getDone());
        statement.executeUpdate();

    }

}
