package com.dbexercise;

import com.dbexercise.domain.User;

import java.sql.*;
import java.util.Map;

public class UserDao {

    public void add() throws SQLException, ClassNotFoundException {

        // 보안을 위한 환경변수 이용
        Map<String, String> env = System.getenv();
        String dbHost = env.get("DB_HOST");
        String dbUser = env.get("DB_USER");
        String dbPassword = env.get("DB_PASSWORD");

        Class.forName("com.mysql.cj.jdbc.Driver");

//    Connection conn = DriverManager.getConnection(
//    "jdbc:mysql://ec2-15-165-231-87.ap-northeast-2.compute.amazonaws.com/likelion", "root", "password");
        // DB와 연결하기
        Connection conn = DriverManager.getConnection(
                dbHost, dbUser, dbPassword);

        // sql구문 작성
        PreparedStatement ps = conn.prepareStatement(
                "INSERT INTO users(id, name, password) VALUES(?,?,?)"
        );
        ps.setString(1, "2");
        ps.setString(2, "soonmin");
        ps.setString(3, "1234");

        // sql 구문 실행
        ps.executeUpdate();

        // 다 쓴 뒤 닫기
        ps.close();
        conn.close();
    }

    public User get(String id) throws ClassNotFoundException, SQLException {

        Map<String, String> env = System.getenv();
        String dbHost = env.get("DB_HOST");
        String dbUser = env.get("DB_USER");
        String dbPassword = env.get("DB_PASSWORD");

        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conn = DriverManager.getConnection(
                dbHost, dbUser, dbPassword);

        //select 구문 작성
        PreparedStatement ps = conn.prepareStatement(
                "SELECT id, name, password FROM users WHERE id = ?"
        );

        ps.setString(1, id);

        // select 구문 실행, 그 결과가 rs에 담김
        ResultSet rs = ps.executeQuery();
        rs.next();

        // User 객체에 정보 담기
        User user = new User(rs.getString("id"),rs.getString("name"), rs.getString("password"));
        // 다 쓰고 닫기
        rs.close();
        ps.close();
        conn.close();

        // 리턴
        return user;
    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        UserDao userDao = new UserDao();
//        userDao.add();
        User user = userDao.get("1");
        System.out.printf("id : %s\nname : %s\npassword : %s\n",user.getId(),user.getName(),user.getPassword());

    }
}
