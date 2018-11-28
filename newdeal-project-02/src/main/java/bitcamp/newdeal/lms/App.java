package bitcamp.newdeal.lms;

import java.sql.Date;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
      Scanner scan = new Scanner(System.in);
      System.out.println("번호?");
      int num = Integer.parseInt(scan.nextLine());
      System.out.println("수업명?");
      String classTitle = scan.nextLine();
      System.out.println("수업내용?");
      String classContent = scan.nextLine();
      System.out.println("시작일?");
      Date classStartDate = Date.valueOf(scan.nextLine());
      // sql date 클래스를 이용했다.
      // Date.valueOf()는 String으로 들어온 값을 date 객체로 만들어주는 것
      System.out.println("종료일?");
      Date classEndDate = Date.valueOf(scan.nextLine());
      // int month = classEndDate.getMonth();
      // 이렇게도 된다.
      System.out.println("총수업시간?");
      int totalClassTime = Integer.parseInt(scan.nextLine());
      System.out.println("일수업시간?");
      int dayClassTime = Integer.parseInt(scan.nextLine());
      
      System.out.println("번호 : "+num);
      // System.out.printf("번호: %s\n", args);
      // 이스케이프 명령어 : 문자열 안에 삽입되는 명령어
      System.out.println("수업명 : "+classTitle);
      System.out.println("수업내용 : "+classContent);
      System.out.println("기간 : "+classStartDate+" ~ "+classEndDate);
      System.out.println("총수업시간 : "+totalClassTime+" 시간");
      // System.out.printf("총수업시간 : %d\n 시간", args);
      System.out.println("일수업시간 : "+dayClassTime+" 시간");
      
      scan.close();
    }
}
