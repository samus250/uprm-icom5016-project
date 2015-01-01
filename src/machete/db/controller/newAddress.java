package machete.db.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import machete.db.HeaderFooterUtils;
import machete.db.model.service.AddressService;

public class newAddress extends HttpServlet {
  private static final long serialVersionUID = 1L;

  public newAddress() {
    super();
  }

  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    doPost(request, response);
  }

  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    HeaderFooterUtils.getHeaderFooterAttributes(request, response);
    String userId = HeaderFooterUtils.getUserLoggedIn(request);
    String forwardURL = null;
    if (userId == null) {
      forwardURL = "/login";
    } else {
      // the user is logged in, get the address information and add it...
      String line1 = request.getParameter("addressLine1");
      String line2 = request.getParameter("addressLine2");
      String line3 = request.getParameter("addressLine3");
      String city = request.getParameter("addressCity");
      String state = request.getParameter("addressState");
      String zip = request.getParameter("addressZip");
      String country = request.getParameter("addressCountry");
      String alias = request.getParameter("addressAlias");

      AddressService service = AddressService.newInstance();
      AddressService.add_status status = service.addAddress(userId, line1, line2, line3, city,
          state, country, zip, alias);
      if (status == AddressService.add_status.SUCCESS) {
        forwardURL = "/my_account/addressAdded.jsp";
      } else {
        forwardURL = "/my_account/addressNotAdded.jsp";
      }
    }

    RequestDispatcher dispatcher = request.getRequestDispatcher(forwardURL);
    dispatcher.forward(request, response);
  }

}
