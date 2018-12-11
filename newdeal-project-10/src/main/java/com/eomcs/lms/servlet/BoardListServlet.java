package com.eomcs.lms.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.context.ApplicationContext;
import com.eomcs.lms.dao.BoardDao;
import com.eomcs.lms.domain.Board;

// 어노테이션을 이용해 톰캣 서버에 서블릿이 있음을 알리고
// 루트 디렉토리부터 쓴 url을 매핑해준다.
@WebServlet("/board/list")
public class BoardListServlet extends HttpServlet{

  /**
   * 
   */
  private static final long serialVersionUID = 1L;
  
  ApplicationContext iocContainer;
  BoardDao boardDao;
  
  @Override
  public void init() throws ServletException{
    // 서블릿 인터페이스에 정의된 init(ServletConfig)가 먼저 호출되고
    // init(ServletConfig)가 이 init()를 호출하는 것
    // 톰캣이 바로 호출하는 게 아님
    // boardDao 객체를 꺼내기 위해 먼저 IOC Container를 꺼낸다.
    ServletContext sc = this.getServletContext();
    // ServletContext는 웹 어플리케이션 당 한 개 뿐이다.
    // ContextLoaderListener에서 꺼낸 것과 같은 객체가 온다.
     iocContainer = (ApplicationContext) sc.getAttribute("iocContainer");
    // 오브젝트 자료형이 리턴되어 applicationcontext 인터페이스로 받는다
    
     try {
       boardDao = iocContainer.getBean(BoardDao.class);
     }catch (Exception e) {
       e.printStackTrace();
     }
  }
  
  @Override
  public void service(HttpServletRequest req, HttpServletResponse res)
      throws ServletException, IOException {

    res.setContentType("text/palin;charset=utf-8");
    PrintWriter out = res.getWriter();
    // 클라이언트 쪽에 출력할 때 필요한 객체준비
    out.println("게시물 목록");
    
    try {
      List<Board> list = boardDao.findAll();
      
      for (Board board : list) {
        out.printf("%3d, %-20s, %s, %d\n", 
            board.getNo(), 
            board.getContents(), 
            board.getCreatedDate(), 
            board.getViewCount());
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    
  }
}
