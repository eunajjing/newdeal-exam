package com.eomcs.lms.servlet;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.context.ApplicationContext;
import com.eomcs.lms.dao.BoardDao;
import com.eomcs.lms.domain.Board;
import com.eomcs.lms.domain.Member;

//Spring IoC 컨테이너가 이 클래스의 인스턴스를 자동 생성하도록 
//클래스에 표시해 둔다.
@WebServlet("/board/add")
public class BoardAddServlet extends HttpServlet {
  
BoardDao boardDao;
  
  @Override
  public void init() {
    ApplicationContext iocContainer = 
        (ApplicationContext) this.getServletContext()
                                 .getAttribute("iocContainer");
    this.boardDao = iocContainer.getBean(BoardDao.class);
  }
  
  @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException {
    // update?
    RequestDispatcher rd = req.getRequestDispatcher(
        "/board/form.jsp");
      rd.forward(req, resp);
    }
  
  @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
      // TODO Auto-generated method stub
      request.setCharacterEncoding("utf-8");
    try {
      Board board = new Board();

      board.setContents(request.getParameter("contents"));
      
      Member loginUser = (Member) request.getSession().getAttribute("loginUser");
      
      board.setWriterNo(loginUser.getNo());
      board.setLessonNo(Integer.parseInt(request.getParameter("lessonNo")));
      
      boardDao.insert(board);
      
      response.sendRedirect("list");
    
    } catch (Exception e) {
      e.printStackTrace();
      throw new ServletException();
    }
  }
}








