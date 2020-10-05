package service;

import dao.IUserDao;
import dao.UserDaoFactory;

import exception.DBException;
import model.User;

import java.sql.SQLException;
import java.util.List;

public class UserService {
    private static UserService userService;
    private IUserDao userDaoImpl;

    private UserService() throws DBException {
        this.userDaoImpl = UserDaoFactory.create();
    }

    public static UserService getInstance() throws DBException {
        if (userService == null) {
            userService = new UserService();
        }
        return userService;
    }


    public void addUser(User user) throws DBException, SQLException {
        userDaoImpl.addUser(user);
    }


    public List<User> getAllUsers() throws DBException, SQLException {
        return userDaoImpl.getAllUsers();
    }


    public void deleteUser(User user) throws DBException, SQLException {
        userDaoImpl.deleteUser(user);
    }


    public void updateUser(User user) throws DBException, SQLException {
        userDaoImpl.updateUser(user);
    }


    public User getUser(int id) throws DBException, SQLException {
        return userDaoImpl.getUserById(id);
    }

    public void cleanUp() {
        try {
            userDaoImpl.dropTable();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createTable() {
        try {
            userDaoImpl.createTable();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
