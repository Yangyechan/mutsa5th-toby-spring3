package com.example.tobyspringboot.dao;
import com.example.tobyspringboot.domain.User;

import java.sql.*;
import java.util.Map;

import static java.lang.System.getenv;

public abstract class UserDao {
    public abstract Connection getConnection() throws ClassNotFoundException, SQLException;
    /*{
        Map<String, String> env = getenv();
        String dbHost = env.get("DB_HOST"); //DB_HOST=jdbc:mysql://localhost:3306/spring-db
        String dbUser = env.get("DB_USER");
        String dbPassword = env.get("DB_PASSWORD");

        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conn = DriverManager.getConnection(
                dbHost, dbUser, dbPassword
        );
        return conn;
    }*/
    public void add(User user) throws ClassNotFoundException, SQLException {
        Map<String, String> env = getenv();
        String dbHost = env.get("DB_HOST");
        String dbUser = env.get("DB_USER");
        String dbPassword = env.get("DB_PASSWORD");

        Class.forName("com.mysql.cj.jdbc.Driver"); // JDBC 드라이버 로드
        Connection conn = DriverManager.getConnection(
                dbHost, dbUser, dbPassword
        );
        PreparedStatement pstmt = conn.prepareStatement("insert into users(id, name, password) values(?, ?, ?)");
        pstmt.setString(1, user.getId());
        pstmt.setString(2, user.getName());
        pstmt.setString(3, user.getPassword());

        pstmt.executeUpdate(); // 번개
        pstmt.close(); // 2
        conn.close(); // 1

    }

    public User get(String id) throws ClassNotFoundException, SQLException {
        Map<String, String> env = getenv();
        String dbHost = env.get("DB_HOST"); //DB_HOST=jdbc:mysql://localhost:3306/spring-db
        String dbUser = env.get("DB_USER");
        String dbPassword = env.get("DB_PASSWORD");

        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conn = DriverManager.getConnection(
                dbHost, dbUser, dbPassword
        );
        // prepareStatement는 SQL 문을 미리 컴파일하고, 매개변수에 대한 값이 설정된 후에 실행됩니다. 이를 통해 성능과 보안 면에서 이점을 제공합니다.
        // Statement는 외부 입력값을 문자열로 직접 추가하므로, 보안에 취약할 수 있습니다.
        PreparedStatement pstmt = conn.prepareStatement("select id, name, password from users where id = ?");
        pstmt.setString(1, id);
        ResultSet rs = pstmt.executeQuery();
        rs.next(); // ctrl + enter // 데이터베이스에서 쿼리를 실행한 결과를 저장하기 위한 코드입니다.

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
    }
}
