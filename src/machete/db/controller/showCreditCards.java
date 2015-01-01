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
import machete.db.Pair;
import machete.db.model.dto.Address;
import machete.db.model.dto.Creditcard;
import machete.db.model.service.AddressService;
import machete.db.model.service.CreditcardService;

public class showCreditCards extends HttpServlet {
  private static final long serialVersionUID = 1L;

  public showCreditCards() {
    super();
  }

  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    HttpSession session = request.getSession();
    HeaderFooterUtils.getHeaderFooterAttributes(request, response);
    String forwardURL = null;

    String userId = HeaderFooterUtils.getUserLoggedIn(request);

    if (userId == null) {
      forwardURL = "/login";
    } else {
      // user logged in, get addresses
      CreditcardService service = CreditcardService.newInstance();
      ArrayList<Creditcard> creditCards = service.getCreditCardsByUserId(userId);
      ArrayList<Pair<Creditcard, Address>> creditCardAddressPairs = new ArrayList<Pair<Creditcard, Address>>();
      // iterate through each credit card and get its address
      for (Creditcard card : creditCards) {
        long addressId = card.getAddressId();
        AddressService addressService = AddressService.newInstance();
        Address address = addressService.getAddress(addressId);
        creditCardAddressPairs.add(new Pair<Creditcard, Address>(card, address));
      }
      session.setAttribute("creditCardAddressPairs", creditCardAddressPairs);
      forwardURL = "/my_account/showCreditCards.jsp";
    }

    RequestDispatcher dispatcher = request.getRequestDispatcher(forwardURL);
    dispatcher.forward(request, response);
  }

  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    doGet(request, response);
  }

}
