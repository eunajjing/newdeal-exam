package com.eomcs.lms.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import com.eomcs.lms.AppConfig;

@WebListener
public class ContextLoaderListener implements ServletContextListener{
  // 웹 어플리케이션이 시작되거나 종료될 때 호출되는 메서드를 정의한 것
  
  AnnotationConfigApplicationContext iocContainer;
  // 다른 메서드에서도 접근이 가능하도록 전역변수로 올린다.
  
  @Override
  public void contextDestroyed(ServletContextEvent sce) {
    // TODO Auto-generated method stub
    System.out.println("웹 어플리케이션이 종료될 때 자동 호출");
    // Spring IOC Container 자원을 해제
    iocContainer.close();
  }
  
  @Override
  public void contextInitialized(ServletContextEvent sce) {
    // TODO Auto-generated method stub
    System.out.println("웹 어플리케이션이 시작될 때 자동으로 호출");
    
    // AppConfig 클래스가 메모리에 로딩되어 있지 않다면,
    // Spring IoC 컨테이너 준비하기
    iocContainer = 
        new AnnotationConfigApplicationContext(AppConfig.class);
    
    System.out.println(iocContainer.getBeanDefinitionCount());
    String[] names = iocContainer.getBeanDefinitionNames();
    
    for (String name : names) {
      System.out.printf("%s ===> %s\n", name, 
          iocContainer.getBean(name).getClass().getName());
    }
    // Spring IOC Container를 servlet이 사용할 수 있도록
    // servletContext라는 보관소에 저장
    ServletContext sc = sce.getServletContext();
    // 파라미터로 들어온 ServletContextEvent를 이용해 IOC Container를 받을 준비
    sc.setAttribute("iocContainer", iocContainer);
  }
}
