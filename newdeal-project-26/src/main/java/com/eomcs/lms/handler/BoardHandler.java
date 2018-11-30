package com.eomcs.lms.handler;
import java.sql.Date;
import java.util.Scanner;
import com.eomcs.lms.domain.Board;

public class BoardHandler {
  
  Scanner keyboard;
  ArrayList list;
  
  public BoardHandler(Scanner keyboard) {
    this.keyboard = keyboard;
    this.list = new ArrayList(20);
  }
  
  public void listBoard() {
    Object[] boards = list.toArray();
    // 여기서 강제 형변환을 할 수 없음
    
    for (Object obj : boards) {
      Board board = (Board) obj;
      // 여기서는 가능함
      // 실제로 object 배열이기 때문에 board 배열로 바꾸는 건 안되는 것이고,
      // 배열에 들어있는 data는 실제로 board기 때문에-원래 자료형이 board이기 때문에-
      // 형 변환이 여기서는 가능한 것
      System.out.printf("%3d, %-20s, %s, %d\n", 
          board.getNo(), board.getContents(), 
          board.getCreatedDate(), board.getViewCount());
    }
  }

  public void addBoard() {
    Board board = new Board();
    
    System.out.print("번호? ");
    board.setNo(Integer.parseInt(keyboard.nextLine()));
    
    System.out.print("내용? ");
    board.setContents(keyboard.nextLine());
    
    board.setCreatedDate(new Date(System.currentTimeMillis())); 
    
    board.setViewCount(0);
    
    list.add(board);
    
    System.out.println("저장하였습니다.");
  }

}
