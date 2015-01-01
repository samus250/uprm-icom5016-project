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

public class editCreditCardForm extends HttpServlet {
  private static final long serialVersionUID = 1L;

  public editCreditCardForm() {
    super();
    // TODO Auto-generated constructor stub
  }

  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    // the form receives creditcardId param
    HttpSession session = request.getSession();
    HeaderFooterUtils.getHeaderFooterAttributes(request, response);
    String userId = HeaderFooterUtils.getUserLoggedIn(request);
    String forwardURL = null;

    String creditcardId = request.getParameter("creditcardId");

    // check the user is logged in
    if (userId != null) {
      // logged in, get the address and check he wants to modify his address
      CreditcardService creditcardService = CreditcardService.newInstance();
      Creditcard creditCard = creditcardService.getCreditCard(creditcardId);
      if (creditCard.getUserId() == Long.parseLong(userId)
          || HeaderFooterUtils.adminLoggedIn(request)) {

        // we have clearance... pass to jsp
        session.setAttribute("creditcard", creditCard);

        // we also need to pass addresses
        AddressService service = AddressService.newInstance();
        ArrayList<Address> addresses = service.getAddressesByUserId(userId);
        session.setAttribute("addresses", addresses);
        if (!addresses.isEmpty())
          forwardURL = "/my_account/editCreditCard.jsp";
        else
          forwardURL = "/my_account/noAddresses.jsp";
      } else {
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
