package machete.db.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import machete.db.HeaderFooterUtils;
import machete.db.model.service.AdministratorService;

public class removeAdmin extends HttpServlet {
  private static final long serialVersionUID = 1L;

  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    String forwardURL = "/admin/notLoggedIn.jsp";

    if (HeaderFooterUtils.adminLoggedIn(request)) {
      // remove it
      String adminId = request.getParameter("adminId");
      AdministratorService service = AdministratorService.newInstance();
      AdministratorService.remove_status status = service.removeAdministrator(adminId);
      if (status == AdministratorService.remove_status.SUCCESS)
        forwardURL = "/admin/customers/adminDeleted.jsp";
      else
        forwardURL = "/admin/customers/adminNotDeleted.jsp";

    }

    RequestDispatcher dispatcher = request.getRequestDispatcher(forwardURL);
    dispatcher.forward(request, response);
  }

  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    doGet(request, response);
  }

}
