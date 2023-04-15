package database.dao;

import database.GetConnection;
import database.entity.Question;

import java.sql.*;

public class QuestionDAO {

    public void insert(Question question) throws SQLException {

        Connection connection = GetConnection.getConnection();
        PreparedStatement check_statement = connection.prepareStatement("select * from   question where id="+question.getId());
        boolean status = check_statement.executeQuery().next();
        PreparedStatement statement;
        if(!status) {
            statement = connection.prepareStatement("insert into question (id,level,statement) values (?,?,?)");
            statement.setInt(1, question.getId());
            statement.setString(2, question.getLevel());
            statement.setString(3, question.getStatement());
        }
        else{

            statement = connection.prepareStatement("update question set level=? , statement = ? where id=?");
            statement.setInt(3, question.getId());
            statement.setString(1, question.getLevel());
            statement.setString(2, question.getStatement());

        }
        statement.executeUpdate();

    }

    public Question get(int id) throws SQLException {
        Connection connection = GetConnection.getConnection();
        PreparedStatement statement = connection.prepareStatement("select * from question where id = ?");
        statement.setInt(1,id);
        ResultSet set = statement.executeQuery();
        if (!set.next()) return null;
        Question question = new Question();
        question.setId(set.getInt("id"));
        question.setLevel(set.getString("level"));
        question.setStatement(set.getString("statement"));
        return question;
    }

}
