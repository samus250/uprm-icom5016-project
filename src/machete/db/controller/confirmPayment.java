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
import machete.db.model.dto.Creditcard;
import machete.db.model.service.AddressService;
import machete.db.model.service.CreditcardService;

public class confirmPayment extends HttpServlet {
  private static final long serialVersionUID = 1L;

  public confirmPayment() {
    super();
  }

  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    doPost(request, response);
  }

  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    HeaderFooterUtils.getHeaderFooterAttributes(request, response);
    HttpSession session = request.getSession();
    String forwardURL = null;

    if (HeaderFooterUtils.userLoggedIn(request)) {
      // get all parameters
      String creditcardId = request.getParameter("creditcard");
      String addressId = request.getParameter("address");

      if (creditcardId != null && addressId != null) {
        CreditcardService creditcardService = CreditcardService.newInstance();
        Creditcard creditCard = creditcardService.getCreditCard(creditcardId);
        AddressService addressService = AddressService.newInstance();
        Address address = addressService.getAddress(addressId);

        session.setAttribute("creditCard", creditCard);
        session.setAttribute("address", address);

        // the cart is still in the session.

        forwardURL = "/checkout/confirmPayment.jsp";
      } else {
        // now credit card or address chosen...
        // ask user to choose
        forwardURL = "/my_account/noCreditCardOrAddress.jsp";
      }
    } else {
      forwardURL = "/login";
    }

    RequestDispatcher dispatcher = request.getRequestDispatcher(forwardURL);
    dispatcher.forward(request, response);
  }

}
