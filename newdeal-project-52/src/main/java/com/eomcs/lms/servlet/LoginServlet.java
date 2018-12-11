package com.eomcs.lms.servlet;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.context.ApplicationContext;
import com.eomcs.lms.dao.MemberDao;
import com.eomcs.lms.domain.Member;

// Spring IoC 컨테이너가 이 클래스의 인스턴스를 자동 생성하도록
// 클래스에 표시해 둔다.
@WebServlet("/auth/login")
public class LoginServlet extends HttpServlet {

  MemberDao memberDao;


  @Override
  public void init() {
    ApplicationContext iocContainer =
        (ApplicationContext) this.getServletContext().getAttribute("iocContainer");
    this.memberDao = iocContainer.getBean(MemberDao.class);
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    RequestDispatcher rd = req.getRequestDispatcher("/auth/form.jsp");
    rd.forward(req, resp);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    try {
      String email = req.getParameter("email");
      String password = req.getParameter("password");
      
      Member member = memberDao.findByEmailPassword(email, password);
      
      HttpSession session = req.getSession();
      
      if (member != null) {
        session.setAttribute("loginUser", member);
        resp.sendRedirect("../board/list");
      } else {
        session.invalidate();
        resp.sendRedirect("login");
      }

    } catch (Exception e) {
      e.printStackTrace();
      throw new ServletException();
    }
  }
  
}


