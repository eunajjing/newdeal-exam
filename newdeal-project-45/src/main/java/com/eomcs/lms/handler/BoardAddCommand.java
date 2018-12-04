package com.eomcs.lms.handler;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Scanner;

public class BoardAddCommand implements Command {
  
  Scanner keyboard;
  
  public BoardAddCommand(Scanner keyboard) {
    this.keyboard = keyboard;
  }
  
  @Override
  public void execute() {
    
    Connection con = null;
    Statement stmt = null;
    
    try {
      
      System.out.print("내용? ");
      String contents = keyboard.nextLine();
      System.out.print("작성자 번호? ");
      String writerNo = keyboard.nextLine();
      System.out.print("수업번호? ");
      String lessonNo = keyboard.nextLine();
      
      con = DriverManager.getConnection("jdbc:mariadb://localhost:3306/studydb", "study", "1111");
      stmt = con.createStatement();
      stmt.executeUpdate("insert into board(cont, mno, lno) values('"+contents+"', "+writerNo+", "+lessonNo+")");

      System.out.println("저장하였습니다.");
    } catch (Exception e) {
      
    } finally {
      try {stmt.close();} catch(Exception e) {}
      try {con.close();} catch(Exception e) {}
    }
    

  }
}
