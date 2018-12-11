package com.eomcs.lms;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App {

  static Scanner keyboard = new Scanner(System.in);
  static Stack<String> commandHistory = new Stack<>();
  static Queue<String> commandHistory2 = new LinkedList<>();
  
  public static void main(String[] args) throws Exception {
    
    Class<?> clazz = Class.forName("com.eomcs.lms.AppConfig");
    // AppConfig 클래스가 메모리에 로딩되어 있지 않다면 로딩 후 클래스 정보 리턴
    // 어떤 클래스라도 상관 없이 받기 위해 제네릭 <?>
    // class가 이미 사용되는 예약어라 clazz로 객체 이름 설정이 일반적
    // AppConfig.class와 같다.
    
    // Reflection 클래스 : JVM에 로딩되어 있는 있는 클래스와 메소드 정보를 읽어 올 수 있다
    
    Method[] methods = clazz.getMethods();
    // 클래스의 모든 메서드들이 들어온다

    Constructor[] constructors = clazz.getConstructors();
    // 생성자의 정보
    Class<?> returnType = methods[0].getReturnType();
    // 메서드의 리턴 타입
    Parameter[] params = methods[0].getParameters();
    // 메서드에 들어가는 파라미터
    
    ApplicationContext iocContainer = new AnnotationConfigApplicationContext(AppConfig.class);
    // 확장자(.class)가 아닌 클래스 변수명임을 기억할 것
    System.out.println(iocContainer.getBeanDefinitionCount());
    // 몇 개의 객체 생성을 했는지
    String[] names = iocContainer.getBeanDefinitionNames();
    // 생성된 객체들의 이름을 리턴
    
    for (String name : names) {
      System.out.printf("%s ===> %s\n", name, iocContainer.getBean(name).getClass().getName());
      // 객체 이름 ===> 경로 출력됨
      // 객체의 이름을 지정하지 않았다면 클래스명 맨 앞 대문자를 소문자로 변경해 사용
    }
    
    while (true) {
      String command = prompt();

      // 사용자가 입력한 명령을 스택에 보관한다.
      commandHistory.push(command);
      
      // 사용자가 입력한 명령을 큐에 보관한다.
      commandHistory2.offer(command);
      
      Command commandHandler = commandMap.get(command);
      
      if (commandHandler != null) {
        try {
          commandHandler.execute();
        } catch (Exception e) {
          System.out.println("명령어 처리 중 오류 발생!");
        }
      } else if (command.equals("quit")) {
        System.out.println("안녕!");
        break;
        
      } else if (command.equals("history")) {
        printCommandHistory();
        
      } else if (command.equals("history2")) {
        printCommandHistory2();
        
      } else {
        System.out.println("실행할 수 없는 명령입니다.");
      }
      
      System.out.println(); 
    }

    keyboard.close();
  }

  private static void printCommandHistory() {
    Iterator<String> iterator = commandHistory.iterator();
    
    while (iterator.hasNext()) {
      System.out.println(iterator.next());
    }
  }
  
  private static void printCommandHistory2() {
    Iterator<String> iterator = commandHistory2.iterator();
    
    while (iterator.hasNext()) {
      System.out.println(iterator.next());
    }
  }

  private static String prompt() {
    System.out.print("명령> ");
    return keyboard.nextLine().toLowerCase();
  }
}
