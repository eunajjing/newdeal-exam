package com.eomcs.lms;

import java.io.InputStream;
import java.util.Scanner;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import com.eomcs.lms.dao.BoardDao;
import com.eomcs.lms.dao.impl.MariaDBBoardDao;
// ioc Container에게 패키지 이름을 알려준다
// 이름을 알려주면 그 패키지를 뒤져서 @conponent가 붙은 클래스에 대해
// 인스턴스를 자동으로 생성
@ComponentScan("com.eomcs.lms")
public class AppConfig {
  
  @Bean
  private Scanner keyboard() {
    return new Scanner(System.in);
  }
  
  @Bean // Spring IOC Container에게 이 메서드를 호출하여 리턴 값을 보관하라고 표시하는 어노테이션
  public SqlSessionFactory createSqlSessionFactory() throws Exception{
    String resource = "com/eomcs/lms/conf/mybatis-config.xml";
    InputStream inputStream = Resources.getResourceAsStream(resource);
    return new SqlSessionFactoryBuilder().build(inputStream);
    // 리턴 값을 저장할 때 사용할 이름을 따로 지정하지 않으면 메서드 이름으로 저장되기에
    // 이런 메서드의 이름은 동사가 아닌 객체의 이름인 명사 형태로 짓는다
  }
  public BoardDao boardDao(SqlSessionFactory sqlSessionFactory) {
    // 파라미터가 있으면 파라미터로 받아야 하는 객체를 먼저 생성한 뒤에 이를 다시 받아 실행
    return new MariaDBBoardDao(sqlSessionFactory);
  }
}
