package database.dao;

import database.GetConnection;
import database.entity.TeamMember;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TeamMemberDAO {

    public void insert(TeamMember teamMember) throws SQLException {

        Connection connection = GetConnection.getConnection();
        PreparedStatement statement = connection.prepareStatement("insert into team_member (team,name) values (?,?)");
        statement.setString(2, teamMember.getName());
        statement.setString(1, teamMember.getTeam());
        statement.executeUpdate();

    }

}
