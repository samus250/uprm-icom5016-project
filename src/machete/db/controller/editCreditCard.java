package machete.db.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import machete.db.HeaderFooterUtils;
import machete.db.model.dto.Creditcard;
import machete.db.model.service.CreditcardService;

public class editCreditCard extends HttpServlet {
  private static final long serialVersionUID = 1L;

  public editCreditCard() {
    super();
  }

  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    doPost(request, response);
  }

  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    HeaderFooterUtils.getHeaderFooterAttributes(request, response);
    String forwardURL = null;

    String creditcardId = request.getParameter("creditcardId");
    String addressId = request.getParameter("address");
    String cardNumber = request.getParameter("cardNumber");
    String cardSecurityNumber = request.getParameter("cardSecurityNumber");
    String cardMonth = request.getParameter("cardMonth");
    String cardYear = request.getParameter("cardYear");
    String cardAlias = request.getParameter("cardAlias");

    // update card with this info, only if person is logged in
    String userId = HeaderFooterUtils.getUserLoggedIn(request);
    if (userId != null) {
      // get card, if the card is from the user, then we can keep going
      CreditcardService creditcardService = CreditcardService.newInstance();
      Creditcard creditCard = creditcardService.getCreditCard(creditcardId);
      if (creditCard.getUserId() == Long.parseLong(userId)
          || HeaderFooterUtils.adminLoggedIn(request)) {
        // we have clearance for updating
        CreditcardService.add_status status = creditcardService.updateCreditCard(creditcardId,
            addressId, cardNumber, cardSecurityNumber, cardMonth, cardYear, cardAlias);
        if (status == CreditcardService.add_status.SUCCESS) {
          forwardURL = "/my_account/editSuccessfull.jsp";
        } else {
          forwardURL = "/my_account/editError.jsp";
        }
      } else {
        forwardURL = "/login";
      }

    } else {
      forwardURL = "/login";
    }

    RequestDispatcher dispatcher = request.getRequestDispatcher(forwardURL);
    dispatcher.forward(request, response);

  }

}
