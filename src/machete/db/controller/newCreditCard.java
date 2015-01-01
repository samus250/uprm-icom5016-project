package machete.db.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import machete.db.HeaderFooterUtils;
import machete.db.model.service.CreditcardService;

public class newCreditCard extends HttpServlet {
  private static final long serialVersionUID = 1L;

  public newCreditCard() {
    super();
  }

  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    doPost(request, response);
  }

  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    HeaderFooterUtils.getHeaderFooterAttributes(request, response);
    String addressId = request.getParameter("address");
    String cardNumber = request.getParameter("cardNumber");
    String cardSecurityNumber = request.getParameter("cardSecurityNumber");
    String cardMonth = request.getParameter("cardMonth");
    String cardYear = request.getParameter("cardYear");
    String cardAlias = request.getParameter("cardAlias");

    // yeah, first check if the user is actually logged in
    String forwardURL = null;
    String userId = HeaderFooterUtils.getUserLoggedIn(request);

    if (userId == null) {
      forwardURL = "/login";
    } else {
      // the user is logged in... we will need to do a lot of stuff
      CreditcardService service = CreditcardService.newInstance();
      CreditcardService.add_status status = service.addCreditCard(userId, addressId, cardNumber,
          cardSecurityNumber, cardMonth, cardYear, cardAlias);

      if (status == CreditcardService.add_status.SUCCESS) {
        // we are good
        forwardURL = "/my_account/cardAdded.jsp";
      } else {
        forwardURL = "/my_account/cardNotAdded.jsp";
      }
    }

    // everything gets validated in the service, should be working
    RequestDispatcher dispatcher = request.getRequestDispatcher(forwardURL);
    dispatcher.forward(request, response);
  }

}
