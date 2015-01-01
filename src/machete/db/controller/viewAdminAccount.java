package machete.db.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import machete.db.HeaderFooterUtils;
import machete.db.model.dto.Administrator;
import machete.db.model.service.AdministratorService;

public class viewAdminAccount extends HttpServlet {
  private static final long serialVersionUID = 1L;

  public viewAdminAccount() {
    super();
  }

  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    HttpSession session = request.getSession();
    HeaderFooterUtils.getHeaderFooterAttributes(request, response);
    String forwardURL = "/admin/notLoggedIn.jsp";
    String adminId = request.getParameter("adminid");

    AdministratorService service = AdministratorService.newInstance();
    Administrator admin = service.getAdministratorById(adminId);
    if (HeaderFooterUtils.adminLoggedIn(request)) {
      if (admin == null) {
        forwardURL = "./admin/customers/adminAccountNotFound.jsp";
      } else {
        // get customer info and throw it to jsp
        session.setAttribute("admin", admin);
        forwardURL = "./admin/customers/adminAccount.jsp";
      }
    }
    RequestDispatcher dispatcher = request.getRequestDispatcher(forwardURL);
    dispatcher.forward(request, response);
  }

  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    doGet(request, response);
  }

}
