import java.sql.Date;
import java.util.Scanner;

public class App {

  public static void main(String[] args) {

    Scanner keyboard = new Scanner(System.in);

    final int LENGTH = 10;

    Lesson[] lessons = new Lesson[LENGTH];

    int i = 0;
    while (true) {
      System.out.println("명령> ");
      String command = keyboard.nextLine();
      if (command.equals("/lesson/add")) {
        while (i < LENGTH) {
          Lesson lesson = new Lesson();
          System.out.print("번호? ");
          lesson.no = Integer.parseInt(keyboard.nextLine());

          System.out.print("수업명? ");
          lesson.title = keyboard.nextLine();

          System.out.print("설명? ");
          lesson.contents = keyboard.nextLine();

          System.out.print("시작일? ");
          lesson.startDate = Date.valueOf(keyboard.nextLine());

          System.out.print("종료일? ");
          lesson.endDate = Date.valueOf(keyboard.nextLine());

          System.out.print("총수업시간? ");
          lesson.totalHours = Integer.parseInt(keyboard.nextLine());

          System.out.print("일수업시간? ");
          lesson.dayHours = Integer.parseInt(keyboard.nextLine());

          lessons[i] = lesson;
          i++;
          System.out.println();
          break;
        }
      } else if (command.equals("/lesson/list")) {
        if (i > 0) {
          for (int j = 0; j < i; j++) {
            System.out.printf("%3d, %-15s, %10s ~ %10s, %4d\n", lessons[j].no, lessons[j].title,
                lessons[j].startDate, lessons[j].endDate, lessons[j].totalHours);
          }
        } else {
          System.out.println("저장된 수업이 없습니다.");
        }
      } else if (command.equals("quit")) {
        System.out.println("안녕!");
        break;
      } else {
        System.out.println("실행할 수 없는 명령입니다.");
      }
    }
    keyboard.close();
  }
}
