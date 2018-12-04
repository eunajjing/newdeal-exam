package com.eomcs.lms.handler;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;
import org.mariadb.jdbc.Driver;

public class BoardListCommand implements Command {
  
  Scanner keyboard;
  
  public BoardListCommand(Scanner keyboard) {
    this.keyboard = keyboard;
  }
  
  @Override
  public void execute() {
    Connection con = null;
    Statement stmt = null;
    ResultSet rs = null;
    try {
      // mariadb jdbc 드라이버(java.sql.Driver 인터페이스를 구현한 것) 구현체 로딩
      DriverManager.registerDriver(new Driver());
      // dbms에 연결하기
      con = DriverManager.getConnection("jdbc:mariadb://localhost:3306/studydb", "study", "1111");
      // sql문 전송 담당할 객체 준비
      stmt = con.createStatement();
      // sql을 서버에 전송하면 서버에서 결과를 가져올 객체를 리턴 -> 서버에서 결과를 리턴하지 않는다
      rs = stmt.executeQuery("select bno, cont, cdt, view from board");
      while (rs.next()) {
        System.out.printf("%3d, %-20s, %s, %d\n", 
            rs.getInt("bno"), rs.getString("cont"), rs.getDate("cdt"), rs.getInt("view"));
      }
    }catch(Exception e) {
      e.printStackTrace();
    }finally {
      try {rs.close();} catch(Exception e) {}
      try {stmt.close();} catch(Exception e) {}
      try {con.close();} catch(Exception e) {}
    }
  }

}
