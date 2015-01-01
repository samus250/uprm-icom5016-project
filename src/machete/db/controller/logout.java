package machete.db.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import machete.db.HeaderFooterUtils;

public class logout extends HttpServlet {
  private static final long serialVersionUID = 1L;

  public logout() {
    super();
  }

  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    HeaderFooterUtils.getHeaderFooterAttributes(request, response);
    String forwardURL = null;

    Cookie logInCookie = null;
    Cookie[] cookies = request.getCookies();
    for (Cookie cookie : cookies) {
      if (cookie.getName().equals("userid")) {
        logInCookie = cookie;
      }
    }

    if (logInCookie == null) {
      forwardURL = "/login/notLoggedIn.jsp";
    } else {
      // delete cookie from header or something
      logInCookie.setMaxAge(0);
      response.addCookie(logInCookie);
      forwardURL = "/login/loggedOut.jsp";
    }

    RequestDispatcher dispatcher = request.getRequestDispatcher(forwardURL);
    dispatcher.forward(request, response);
  }

  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    doGet(request, response);
  }
}
