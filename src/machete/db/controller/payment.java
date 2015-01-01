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
import machete.db.model.dto.Product;
import machete.db.model.service.InvoiceService;

public class payment extends HttpServlet {
  private static final long serialVersionUID = 1L;

  public payment() {
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

    // create invoice and all order items
    // for this we need the chosen card, address, userid, and whole shopping
    // cart.
    // they should all be in the session (but beware, they may not!)
    String userid = HeaderFooterUtils.getUserLoggedIn(request);
    String forwardURL = null;

    if (userid != null) {
      // get chosen card, address and shopping cart
      Creditcard creditcard = (Creditcard) session.getAttribute("creditCard");
      Address address = (Address) session.getAttribute("address");
      ArrayList<Pair<Product, Long>> productQuantityPairs = (ArrayList<Pair<Product, Long>>) session
          .getAttribute("productQuantityPairs");

      long creditcardId = creditcard.getCreditcardId();
      long addressId = address.getAddressId();

      InvoiceService invoiceService = InvoiceService.newInstance();
      Pair<InvoiceService.add_status, Long> statusIdPair = invoiceService.addInvoice(userid,
          creditcardId, addressId, productQuantityPairs);
      InvoiceService.add_status status = statusIdPair.getFirst();
      Long newInvoiceId = statusIdPair.getSecond();
      session.setAttribute("invoiceId", newInvoiceId);
      if (status == InvoiceService.add_status.SUCCESS) {
        // the invoice was generated, now show it.
        forwardURL = "/showInvoice";
      } else {
        forwardURL = "/checkout/invoiceError.jsp";
      }
    } else {
      forwardURL = "/login";
    }

    RequestDispatcher dispatcher = request.getRequestDispatcher(forwardURL);
    dispatcher.forward(request, response);
  }

}
