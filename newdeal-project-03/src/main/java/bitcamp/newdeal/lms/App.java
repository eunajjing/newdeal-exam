package bitcamp.newdeal.lms;

import java.sql.Date;
import java.util.Scanner;

public class App {
  public static void main(String[] args) {
    final int NUMBER = 20;
    int[] no = new int[NUMBER];
    // 4byte 크기 메모리 100개를 heap(인스턴스들이 저장)
    // 배열은 모두 ref 변수(주소 변수)
    // 메서드 에리어
    // stack (메인 쓰레드 것과... 쓰레드 각각 1개씩 만들어진다)
    String[] title = new String[NUMBER];
    String[] contents = new String[NUMBER];
    Date[] startDate = new Date[NUMBER];
    Date[] endDate = new Date[NUMBER];
    int[] totalHours = new int[NUMBER];
    int[] dayHours = new int[NUMBER];

    int len = 0;

    Scanner scan = new Scanner(System.in);

    for (int i = 0; i < NUMBER ; i++) {
      System.out.println("번호?");
      no[i] = Integer.parseInt(scan.nextLine());
      System.out.println("수업명?");
      title[i] = scan.nextLine();
      System.out.println("수업내용?");
      contents[i] = scan.nextLine();
      System.out.println("시작일?");
      startDate[i] = Date.valueOf(scan.nextLine());
      System.out.println("종료일?");
      endDate[i] = Date.valueOf(scan.nextLine());
      System.out.println("총수업시간?");
      totalHours[i] = Integer.parseInt(scan.nextLine());
      System.out.println("일수업시간?");
      dayHours[i] = Integer.parseInt(scan.nextLine());
      len++;
      System.out.print("계속하시겠습니까?(Y/n)");
      String input = scan.nextLine();
      if (input.equalsIgnoreCase("n")) {
        // 대소문자 가리지 않고
        break;
      } else {
        continue;
      }
    }
    scan.close();



    for (int j = 0; j < len ; j++) {
      System.out.println(no[j]+","+title[j]+","+contents[j]+","
                         +startDate[j]+"~"+endDate[j]+","+totalHours[j]+","+dayHours[j]);
    }

  }
}
