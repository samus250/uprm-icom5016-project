package machete.db.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import machete.db.HeaderFooterUtils;
import machete.db.model.service.AdministratorService;

public class addAdministrator extends HttpServlet {
  private static final long serialVersionUID = 1L;

  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    doPost(request, response);
  }

  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    HttpSession session = request.getSession();
    String forwardURL = "/admin/notLoggedIn.jsp";

    if (HeaderFooterUtils.adminLoggedIn(request)) {
      // add the category
      String userName = request.getParameter("username");
      String email = request.getParameter("email");
      String password = request.getParameter("password");
      String verify = request.getParameter("verify");

      AdministratorService service = AdministratorService.newInstance();
      AdministratorService.add_status status = service.addAdministrator(userName, email, password,
          verify);

      if (status == AdministratorService.add_status.SUCCESS) {
        forwardURL = "./admin/customers/adminAdded.jsp";
      } else {
        // what was the error?
        String errorString;
        if (status == AdministratorService.add_status.USERNAME_TAKEN)
          errorString = "Username Already Taken";
        else if (status == AdministratorService.add_status.PASSWORD_VERIFICATION_ERROR)
          errorString = "Password Verification Failed.";
        else
          errorString = "Other Failure. System Error";

        session.setAttribute("errorString", errorString);
        forwardURL = "./admin/customers/adminNotAdded.jsp";
      }
    }

    RequestDispatcher dispatcher = request.getRequestDispatcher(forwardURL);
    dispatcher.forward(request, response);
  }

}
