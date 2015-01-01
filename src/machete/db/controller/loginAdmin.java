package machete.db.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import machete.db.HeaderFooterUtils;

public class loginAdmin extends HttpServlet {
  private static final long serialVersionUID = 1L;

  public loginAdmin() {
    super();
  }

  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    HeaderFooterUtils.getHeaderFooterAttributes(request, response);
    String forwardURL = null;

    String adminId = HeaderFooterUtils.getAdminLoggedIn(request);

    if (adminId != null)
      forwardURL = "/admin/index.jsp";
    else
      forwardURL = "/admin/loginAdmin.jsp";
    RequestDispatcher dispatcher = request.getRequestDispatcher(forwardURL);
    dispatcher.forward(request, response);
  }

  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    doGet(request, response);
  }

}
