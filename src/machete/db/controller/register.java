package machete.db.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import machete.db.HeaderFooterUtils;

public class register extends HttpServlet {
  private static final long serialVersionUID = 1L;

  public register() {
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

    String userId = HeaderFooterUtils.getUserLoggedIn(request);

    if (userId == null)
      forwardURL = "/register/register.jsp";
    else
      forwardURL = "/login/alreadyLoggedIn.jsp";
    RequestDispatcher dispatcher = request.getRequestDispatcher(forwardURL);
    dispatcher.forward(request, response);
  }

}
