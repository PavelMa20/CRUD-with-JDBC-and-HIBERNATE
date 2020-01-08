package dao;

import exception.DBException;
import model.User;

import java.sql.SQLException;
import java.util.List;

public interface IUserDao {
    int getUserIdByLogin(String login);

    void addUser(User user) throws SQLException, DBException;

    void updateUser(User user) throws SQLException, DBException;

    void deleteUser(User user) throws SQLException, DBException;

    User getUserById(int id) throws SQLException, DBException;

    List<User> getAllUsers() throws SQLException, DBException;

    void dropTable() throws SQLException;

    void createTable() throws SQLException;
}
