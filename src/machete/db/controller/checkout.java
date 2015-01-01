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
import machete.db.model.dto.Creditcard;
import machete.db.model.service.AddressService;
import machete.db.model.service.CreditcardService;

public class checkout extends HttpServlet {
  private static final long serialVersionUID = 1L;

  public checkout() {
    super();
  }

  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    HttpSession session = request.getSession();
    HeaderFooterUtils.getHeaderFooterAttributes(request, response);
    String forwardURL = null;

    String userId = HeaderFooterUtils.getUserLoggedIn(request);

    if (userId != null) {
      // throw the user's credit cards and addresses to the jsp
      CreditcardService creditcardService = CreditcardService.newInstance();
      ArrayList<Creditcard> creditCards = creditcardService.getCreditCardsByUserId(userId);

      AddressService addressService = AddressService.newInstance();
      ArrayList<Address> addresses = addressService.getAddressesByUserId(userId);

      session.setAttribute("creditCards", creditCards);
      session.setAttribute("addresses", addresses);

      forwardURL = "/checkout/checkout.jsp";
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
