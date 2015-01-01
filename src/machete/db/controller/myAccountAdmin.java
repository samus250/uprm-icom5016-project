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

public class myAccountAdmin extends HttpServlet {
  private static final long serialVersionUID = 1L;

  public myAccountAdmin() {
    super();
  }

  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    HttpSession session = request.getSession();
    HeaderFooterUtils.getHeaderFooterAttributes(request, response);
    String forwardURL = null;

    String adminId = HeaderFooterUtils.getAdminLoggedIn(request);

    if (adminId == null) {
      forwardURL = "/loginAdmin";
    } else {
      AdministratorService service = AdministratorService.newInstance();
      Administrator admin = service.getAdministratorById(adminId);

      if (admin == null) {
        forwardURL = "/loginAdmin";
      } else {
        // get customer info and throw it to jsp
        session.setAttribute("admin", admin);
        forwardURL = "/admin/myAccountAdmin.jsp";
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
