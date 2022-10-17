package com.dbexercise;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Map;

public class UserDao {

public void add() throws SQLException, ClassNotFoundException {

    // 보안을 위한 환경변수 이용
    Map< String, String> env = System.getenv();
    String dbHost = env.get("DB_HOST");
    String dbUser = env.get("DB_USER");
    String dbPassword = env.get("DB_PASSWORD");

    Class.forName("com.mysql.cj.jdbc.Driver");

//    Connection conn = DriverManager.getConnection(
//    "jdbc:mysql://ec2-15-165-231-87.ap-northeast-2.compute.amazonaws.com/likelion", "root", "password");
   // DB와 연결
    Connection conn = DriverManager.getConnection(
            dbHost,dbUser,dbPassword);

    // sql구문 작성
    PreparedStatement ps = conn.prepareStatement(
            "INSERT INTO users(id, name, password) VALUES(?,?,?)"
    );
    ps.setString(1, "2");
    ps.setString(2,"soonmin");
    ps.setString(3, "1234");

    // sql 구문 실행
    ps.executeUpdate();
    
    // 다 쓴 뒤 닫기
    ps.close();
    conn.close();


}
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
    UserDao userDao = new UserDao();
    userDao.add();

    }
}
