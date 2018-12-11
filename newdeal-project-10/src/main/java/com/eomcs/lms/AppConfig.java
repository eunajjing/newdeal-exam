package com.eomcs.lms;

import java.util.Scanner;
import javax.sql.DataSource;
import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

// Spring IoC 컨테이너에게 패키지 이름을 알려주면 
// 그 패키지를 뒤져서 @Component가 붙은 클래스에 대해 
// 인스턴스를 자동으로 생성해준다.
@ComponentScan("com.eomcs.lms")

// spring ioc Container에게 프로퍼티 파일을 로딩할 것을 명령
@PropertySource("classpath:/com/eomcs/lms/conf/jdbc.properties")
// 프로퍼티가 있는 위치를 가리키되, 기술하는 방식을 지켜야 한다.
public class AppConfig {
  @Value("${jdbc.driver}")
  String jdbcDriver;
  @Value("${jdbc.url}")
  String jdbcUrl;
  @Value("${jdbc.username}")
  String jdbcUserName;
  @Value("${jdbc.password}")
  String jdbcPassword;
  
  //Spring IoC 컨테이너에게 이 메서드를 호출하여 리턴 값을 보관하라고
  //표시하기
  //=> 리턴 값을 값을 저장할 때 사용할 이름을 따로 지정하지 않으면,
  //   메서드 이름으로 저장한다.
  //=> 그래서 이런 메서드의 이름은 보통 동사로 시작하지 않고,
  //   객체의 이름인 명사 형태로 짓는다.
  @Bean
  public SqlSessionFactory sqlSessionFactory(DataSource dataSource, ApplicationContext iocContainer) throws Exception {
    
    SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
    
    // datasource 주입
    factoryBean.setDataSource(dataSource);
    factoryBean.setTypeAliasesPackage("com.eomcs.lms.domain");
    // 도메인 별명 지정
    
    factoryBean.setMapperLocations(iocContainer.getResources("classpath:/com/eomcs/lms/mapper/*Mapper.xml"));
    // sql mapper 로딩
    // sql 파일이 있는 위치를 파라미터로 보내야 하는 상황
    // sql 파일의 위치 정보를 resource 객체에 담아 넘겨야 함
    // resource 객체는 Spring IOC Container를 통해 만들 수 있다.
    // Spring IOC Container 객체를 얻는 방법 : 메서드의 파라미터로 받는다.
    // 이 경우 ioc container 생성은 실행 클래스에서 하기에
    // 실행 클래스에서 해당 객체가 넘어온다.
    
    return factoryBean.getObject();
  }
  
  @Bean
  public DataSource dataSource() {
    BasicDataSource dataSource = new BasicDataSource();
    dataSource.setDriverClassName(jdbcDriver);
    dataSource.setUrl(jdbcUrl);
    dataSource.setUsername(jdbcUserName);
    dataSource.setPassword(jdbcPassword);
    return dataSource;
  }
  
  public PlatformTransactionManager platformTransactionManager(DataSource dataSource) {
    return new DataSourceTransactionManager(dataSource);
  }
  
  @Bean
  public Scanner keyboard() {
    return new Scanner(System.in);
  }
}





