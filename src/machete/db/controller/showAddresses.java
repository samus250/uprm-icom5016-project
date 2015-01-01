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

public class showAddresses extends HttpServlet {
  private static final long serialVersionUID = 1L;

  public showAddresses() {
    super();
  }

  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    HttpSession session = request.getSession();
    HeaderFooterUtils.getHeaderFooterAttributes(request, response);
    String forwardURL = null;

    // get addresses for logged in user
    String userId = HeaderFooterUtils.getUserLoggedIn(request);

    if (userId == null) {
      forwardURL = "/login";
    } else {
      // user logged in, get addresses
      AddressService service = AddressService.newInstance();
      ArrayList<Address> addresses = service.getAddressesByUserId(userId);
      session.setAttribute("addresses", addresses);
      forwardURL = "/my_account/showAddresses.jsp";
    }

    RequestDispatcher dispatcher = request.getRequestDispatcher(forwardURL);
    dispatcher.forward(request, response);
  }

  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    doGet(request, response);
  }

}
