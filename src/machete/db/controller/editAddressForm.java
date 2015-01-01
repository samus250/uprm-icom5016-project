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

public class editAddressForm extends HttpServlet {
  private static final long serialVersionUID = 1L;

  public editAddressForm() {
    super();

  }

  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    HttpSession session = request.getSession();
    HeaderFooterUtils.getHeaderFooterAttributes(request, response);
    String userId = HeaderFooterUtils.getUserLoggedIn(request);
    String forwardURL = null;

    String addressId = request.getParameter("addressId");

    // check the user is logged in
    if (userId != null) {
      // logged in, get the address and check he wants to modify his address
      AddressService service = AddressService.newInstance();
      Address address = service.getAddress(addressId);
      if (address.getUserId() == Long.parseLong(userId) || HeaderFooterUtils.adminLoggedIn(request)) {
        // we have clearance, give the address to the jsp
        session.setAttribute("address", address);
        forwardURL = "/my_account/editAddress.jsp";
      } else {
        // no clearance, login
        forwardURL = "/login";
      }
    } else {
      forwardURL = "/login";
    }

    RequestDispatcher dispatcher = request.getRequestDispatcher(forwardURL);
    dispatcher.forward(request, response);
  }

  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    doGet(request, response);
  }

}
