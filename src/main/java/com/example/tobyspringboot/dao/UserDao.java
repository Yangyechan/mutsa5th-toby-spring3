package com.example.tobyspringboot.dao;
import com.example.tobyspringboot.domain.User;

import java.sql.*;
import java.util.Map;

import static java.lang.System.getenv;

public class UserDao {
    ConnectionMaker connectionMaker;
    public UserDao(ConnectionMaker connectionMaker) {
        this.connectionMaker = new DConnectionMaker();
    }
    //SimpleConnectionMaker connectionMaker = new SimpleConnectionMaker();

    public void add(User user) throws ClassNotFoundException, SQLException {

        Connection conn = connectionMaker.makeNewConnection();
        PreparedStatement pstmt = conn.prepareStatement("insert into users(id, name, password) values(?, ?, ?)");
        pstmt.setString(1, user.getId());
        pstmt.setString(2, user.getName());
        pstmt.setString(3, user.getPassword());

        pstmt.executeUpdate();

        pstmt.close();
        conn.close();
    }

    public User get(String id) throws ClassNotFoundException, SQLException {
        Connection conn = connectionMaker.makeNewConnection();

        PreparedStatement pstmt = conn.prepareStatement("select id, name, password from users where id = ?");
        pstmt.setString(1, id);
        ResultSet rs = pstmt.executeQuery();
        rs.next(); // ctrl + enter

        User user = new User();
        user.setId(rs.getString("id"));
        user.setName(rs.getString("name"));
        user.setPassword(rs.getString("password"));

        rs.close();
        pstmt.close();
        conn.close();

        return user;
    }


    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        /*
        UserDao userDao = new UserDao();
        User user = new User();
        user.setId("1");
        user.setName("kyeongrok");
        user.setPassword("1234");
        //userDao.add(user);
        User selectedUser = userDao.get("3");
        System.out.println(selectedUser.getId());
        System.out.println(selectedUser.getName());
        System.out.println(selectedUser.getPassword());
         */
        /*
        UserDao userDao = new NUserDao();
        User user = new User();
        user.setId("4");
        user.setName("kyeongrok");
        user.setPassword("1234");
        userDao.add(user);

        User selectedUser = userDao.get("4");
        System.out.println(selectedUser.getId());
        System.out.println(selectedUser.getName());
        System.out.println(selectedUser.getPassword());
         */
        /*
        UserDao userDao = new UserDao();
        User user = new User();
        user.setId("5");
        user.setName("kyeongrok");
        user.setPassword("1234");
        userDao.add(user);

        User selectedUser = userDao.get("5");
        System.out.println(selectedUser.getId());
        System.out.println(selectedUser.getName());
        System.out.println(selectedUser.getPassword());
         */
        /*
        UserDao userDao = new UserDao();
        User user = new User();
        user.setId("6");
        user.setName("yechan");
        user.setPassword("1234567810");
        userDao.add(user);

        User selectedUser = userDao.get("6");
        System.out.println(selectedUser.getId());
        System.out.println(selectedUser.getName());
        System.out.println(selectedUser.getPassword();
         */
        ConnectionMaker cm = new DConnectionMaker();
        UserDao userDao = new UserDao(cm);
    }
}
