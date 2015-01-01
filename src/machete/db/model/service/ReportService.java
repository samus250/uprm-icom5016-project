package machete.db.model.service;

import java.util.ArrayList;

import machete.db.model.dao.ReportDAO;
import machete.db.model.dto.Report;

public class ReportService {
  public static enum add_status {
    SUCCESS, OTHER_FAILURE
  }

  public static enum remove_status {
    SUCCESS, OTHER_FAILURE
  }

  private ReportService() {

  }

  public static ReportService newInstance() {
    return new ReportService();
  }

  public ArrayList<Report> getTotalSalesReport(String type) {
    ReportDAO dao = ReportDAO.getInstance();
    // validate stuff
    if (type.equals("year"))
      return dao.getTotalSalesReportByYear();
    else if (type.equals("month"))
      return dao.getTotalSalesReportByMonth();

    return dao.getTotalSalesReportByDay();

  }

  public ArrayList<Report> getTotalRevenueReport(String type) {
    ReportDAO dao = ReportDAO.getInstance();
    // validate stuff
    if (type.equals("year"))
      return dao.getTotalRevenueReportByYear();
    else if (type.equals("month"))
      return dao.getTotalRevenueReportByMonth();

    return dao.getTotalRevenueReportByDay();

  }

  public ArrayList<Report> getProductTotalSalesReport(String type, long product_id) {
    ReportDAO dao = ReportDAO.getInstance();
    // validate stuff
    if (type.equals("year"))
      return dao.getProductTotalSalesReportByYear(product_id);
    else if (type.equals("month"))
      return dao.getProductTotalSalesReportByMonth(product_id);

    return dao.getProductTotalSalesReportByDay(product_id);

  }

  public ArrayList<Report> getProductTotalRevenueReport(String type, long product_id) {
    ReportDAO dao = ReportDAO.getInstance();
    // validate stuff
    if (type.equals("year"))
      return dao.getProductTotalRevenueReportByYear(product_id);
    else if (type.equals("month"))
      return dao.getProductTotalRevenueReportByMonth(product_id);

    return dao.getProductTotalRevenueReportByDay(product_id);

  }

}
