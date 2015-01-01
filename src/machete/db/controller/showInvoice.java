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
import machete.db.model.dto.Invoice;
import machete.db.model.dto.OrderItem;
import machete.db.model.service.InvoiceService;
import machete.db.model.service.OrderItemService;

public class showInvoice extends HttpServlet {
  private static final long serialVersionUID = 1L;

  public showInvoice() {
    super();
  }

  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    HeaderFooterUtils.getHeaderFooterAttributes(request, response);
    HttpSession session = request.getSession();
    String forwardURL = "/login";

    String userId = HeaderFooterUtils.getUserLoggedIn(request);
    if (userId != null) {
      // try to get from request first, if nothing, then get session
      String invoiceId = request.getParameter("invoiceId");
      if (invoiceId == null)
        invoiceId = ((Long) session.getAttribute("invoiceId")).toString();

      // get invoice and all order items
      InvoiceService invoiceService = InvoiceService.newInstance();
      Invoice invoice = invoiceService.getInvoice(invoiceId);

      // check if the invoice is a user's invoice
      if (invoice.getUserId() == Long.parseLong(userId) || HeaderFooterUtils.adminLoggedIn(request)) {
        session.setAttribute("invoice", invoice);

        OrderItemService orderItemService = OrderItemService.newInstance();
        ArrayList<OrderItem> orderItems = orderItemService.getOrderItemsByInvoiceId(invoiceId);

        session.setAttribute("orderItems", orderItems);
        forwardURL = "/payment/showInvoice.jsp";
      } else {
        // the requested invoice is not of this user
        forwardURL = "/payment/invoiceAccessForbidden.jsp";
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
