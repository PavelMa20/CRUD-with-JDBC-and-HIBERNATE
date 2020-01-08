package dao;


import exception.DBException;
import model.User;
import util.DBHelper;
import util.JDBCHelper;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDImpl implements IUserDao {

    private Connection connection;

    public UserDaoJDImpl() throws  DBException {

        connection = DBHelper.getConnection();
    }


    @Override
    public int getUserIdByLogin(String login) {
        String query = "select * from clients where login=?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, login);
            try (ResultSet result = stmt.executeQuery()) {
                if (result.next()) {
                    return result.getInt(1);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

    @Override
    public void addUser(User user) throws SQLException, DBException {
        String query = "insert into clients (name,password,login) values(?,?,?)";
        PreparedStatement preparedStatement = null;
        try {
            if (getUserIdByLogin(user.getLogin()) != -1) {
                throw new DBException("alredy exist");
            }
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getLogin());
            int rows = preparedStatement.executeUpdate();
            connection.setAutoCommit(false);
            if (rows != 1) {
                throw new SQLException("no insert");
            }
            connection.commit();
        } catch (SQLException ex) {
            ex.getMessage();
            connection.rollback();
            ex.printStackTrace();
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
                connection.setAutoCommit(true);

            }
        }
    }

    @Override
    public void updateUser(User user) throws SQLException {
        String query = "update clients set name=?,password=?,login=? where id =?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getLogin());
            preparedStatement.setInt(4, user.getId());
            connection.setAutoCommit(false);
            int rows = preparedStatement.executeUpdate();
            if (rows != 1) {
                throw new SQLException("no update");
            }
            connection.commit();
        } catch (Exception e) {
            connection.rollback();
            e.printStackTrace();
            throw new SQLException();
        } finally {
            connection.setAutoCommit(true);
        }
    }

    @Override
    public void deleteUser(User user) throws SQLException {
        String query = "delete from clients where id=?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, user.getId());
            connection.setAutoCommit(false);
            preparedStatement.execute();
            connection.commit();
        } catch (SQLException ex) {
            connection.rollback();
            throw new SQLException();
        } finally {
            connection.setAutoCommit(true);
        }
    }

    @Override
    public User getUserById(int id) throws SQLException {
        String query = "select * from clients where id=?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            connection.setAutoCommit(false);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return new User(resultSet.getInt("id"), resultSet.getString("name"), resultSet.getString("password"), resultSet.getString("login"));
                }
            } catch (Exception ex) {
                ex.getMessage();
            }
            connection.commit();
        } catch (SQLException e) {
            connection.rollback();
            e.getMessage();
        } finally {
            connection.setAutoCommit(true);
        }
        return null;
    }

    @Override
    public List<User> getAllUsers() throws SQLException {
        List<User> userList = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            connection.setAutoCommit(false);
            try (ResultSet resultSet = statement.executeQuery("select * from clients")) {
                while (resultSet.next()) {
                    userList.add(new User(resultSet.getInt("id"), resultSet.getString("name"), resultSet.getString("password"), resultSet.getString("login")));
                }
                connection.commit();
            }
        } catch (Exception e) {
            connection.rollback();
            e.printStackTrace();
        } finally {
            connection.setAutoCommit(true);
        }
        return userList;
    }

    public void createTable() throws SQLException {
        connection.setAutoCommit(false);
        try (Statement stmt = connection.createStatement()) {
            stmt.execute("create table if not exists clients (id int auto_increment, name varchar(45), password varchar(45), login varchar(45), primary key (id))");
            connection.commit();
        } catch (Exception e) {
            connection.rollback();
        } finally {
            connection.setAutoCommit(true);
        }
    }

    public void dropTable() throws SQLException {
        connection.setAutoCommit(false);
        try (Statement stmt = connection.createStatement()) {
            stmt.executeUpdate("DROP TABLE IF EXISTS clients");
            connection.commit();
        } catch (Exception e) {
            connection.rollback();
        } finally {
            connection.setAutoCommit(true);
        }
    }
}




