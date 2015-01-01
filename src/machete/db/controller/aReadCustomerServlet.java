package machete.db.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import machete.db.HeaderFooterUtils;
import machete.db.model.dto.Administrator;
import machete.db.model.service.AdministratorService;

public class aReadCustomerServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  public aReadCustomerServlet() {
    super();
  }

  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    doPost(request, response);
  }

  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    HttpSession session = request.getSession();
    HeaderFooterUtils.getHeaderFooterAttributes(request, response);
    String forwardURL = null;

    String userName = request.getParameter("userName");
    String password = request.getParameter("password");

    AdministratorService service = AdministratorService.newInstance();
    Administrator administrator = service.getAdministrator(userName);

    if (administrator == null) {
      forwardURL = "/loginAdmin";
    } else {
      // check password
      if (administrator.getUserPassword().equals(password)) {
        Cookie cookie = new Cookie("adminid", Long.toString(administrator.getUserId()));
        response.addCookie(cookie);
        session.setAttribute("administrator", administrator);
        forwardURL = "/admin/index.jsp";
      } else {
        forwardURL = "/loginAdmin";
      }
    }
    RequestDispatcher dispatcher = request.getRequestDispatcher(forwardURL);
    dispatcher.forward(request, response);
  }

}
