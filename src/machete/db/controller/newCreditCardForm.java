package machete.db.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import machete.db.HeaderFooterUtils;
import machete.db.model.dto.Address;
import machete.db.model.service.AddressService;

public class newCreditCardForm extends HttpServlet {
  private static final long serialVersionUID = 1L;

  public newCreditCardForm() {
    super();
  }

  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    // this form should get all the addresses from the connected user...
    HttpSession session = request.getSession();
    HeaderFooterUtils.getHeaderFooterAttributes(request, response);
    String userId = HeaderFooterUtils.getUserLoggedIn(request);
    String forwardURL = null;
    if (userId == null) {
      forwardURL = "/login";
    } else {
      // throw the user to the credit card form...
      // pass addresses that the user has.
      AddressService service = AddressService.newInstance();
      ArrayList<Address> addresses = service.getAddressesByUserId(userId);

      if (!addresses.isEmpty()) {
        session.setAttribute("addresses", addresses);
        forwardURL = "/my_account/newCreditCard.jsp";
      } else {
        // the user has no addresses, ask him to put an address first
        forwardURL = "/my_account/noAddresses.jsp";
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
