package machete.db.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import machete.db.HeaderFooterUtils;
import machete.db.model.dto.Address;
import machete.db.model.service.AddressService;

public class editAddress extends HttpServlet {
  private static final long serialVersionUID = 1L;

  public editAddress() {
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
    String userId = HeaderFooterUtils.getUserLoggedIn(request);
    String forwardURL = null;

    String addressId = request.getParameter("addressId");
    String line1 = request.getParameter("addressLine1");
    String line2 = request.getParameter("addressLine2");
    String line3 = request.getParameter("addressLine3");
    String city = request.getParameter("addressCity");
    String state = request.getParameter("addressState");
    String zip = request.getParameter("addressZip");
    String country = request.getParameter("addressCountry");
    String alias = request.getParameter("addressAlias");

    if (userId == null) {
      forwardURL = "/login";
    } else {
      // the user is logged in, check clearance

      AddressService service = AddressService.newInstance();
      Address address = service.getAddress(addressId);
      if (address.getUserId() == Long.parseLong(userId) || HeaderFooterUtils.adminLoggedIn(request)) {
        // we have clearance, update

        AddressService.add_status status = service.updateAddress(addressId, line1, line2, line3,
            city, state, country, zip, alias);
        if (status == AddressService.add_status.SUCCESS) {
          forwardURL = "/my_account/editSuccessfull.jsp";
        } else {
          forwardURL = "/my_account/editError.jsp";
        }
      } else {
        forwardURL = "/login";
      }
    }

    RequestDispatcher dispatcher = request.getRequestDispatcher(forwardURL);
    dispatcher.forward(request, response);
  }

}
