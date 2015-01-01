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
import machete.db.model.service.ReportService;
import machete.db.model.dto.Report;

public class showReport extends HttpServlet {
  private static final long serialVersionUID = 1L;

  public showReport() {
    super();
  }

  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    HttpSession session = request.getSession();
    HeaderFooterUtils.getHeaderFooterAttributes(request, response);
    String forwardURL = "/admin/notLoggedIn.jsp";
    String report = request.getParameter("report");
    String report_type = request.getParameter("report_type");

    if (HeaderFooterUtils.adminLoggedIn(request)) {

      // validate the strings
      String[] possibleReportValues = { "returns", "total_revenue", "total_sales",
        "total_revenue_by_product", "total_sales_by_product" };
      String[] possibleReportTypeValues = { "day", "month", "year" };
      boolean validate1, validate2;
      validate1 = validate2 = false;
      for (String possibleReportValue : possibleReportValues) {
        if (possibleReportValue.equalsIgnoreCase(report)) {
          validate1 = true;
        }
      }
      for (String possibleReportTypeValue : possibleReportTypeValues) {
        if (possibleReportTypeValue.equalsIgnoreCase(report_type)) {
          validate2 = true;
        }
      }
      if (!validate1 || !validate2) {
        // validation failed, throw to error page
        forwardURL = "/admin/reports/wrongReport.jsp";
      } else {
        // meat
        // we should design a report data structure, it would include a lot of
        // fancy things
        // how should a report be managed?
        // for now it would just include text, tables, etc. I think
        // but later on it might even contain pictures and graphs!

        ReportService service = ReportService.newInstance();

        ArrayList<Report> reports;

        if (report == null) {
          forwardURL = "./admin/reports/wrongReport.jsp";
        } else {

          session.setAttribute("report", report);
          session.setAttribute("report_type", report_type);

          if (report.contains("product")) {
            int product_id = Integer.parseInt(request.getParameter("product_id"));
            session.setAttribute("product_id", product_id);
            if (report.contains("sales")) {
              reports = service.getProductTotalSalesReport(report_type, product_id);
            } else {
              reports = service.getProductTotalRevenueReport(report_type, product_id);
            }
          } else {
            session.setAttribute("product_id", 0);

            if (report.contains("sales"))
              reports = service.getTotalSalesReport(report_type);

            else {
              reports = service.getTotalRevenueReport(report_type);
            }
          }

          session.setAttribute("reports", reports);

          forwardURL = "./admin/reports/showReport.jsp";
        }

      }

    }

    RequestDispatcher dispatcher = request.getRequestDispatcher(forwardURL);
    dispatcher.forward(request, response);
  }

  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    doGet(request, response);
  }

}
