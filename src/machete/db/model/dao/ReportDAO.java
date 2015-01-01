package machete.db.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import machete.db.model.dto.Report;

public class ReportDAO {

  private static ReportDAO singleton = null;

  private static final String TOTAL_SALES_REPORT_BY_DAY = "select invoice_date as day, count(invoice_date) as total "
      + "from invoice natural inner join order_item " + "group by day " + "order by day;";

  private static final String TOTAL_SALES_REPORT_BY_YEAR = "select DATE_PART('year', invoice_date) as year, count(DATE_PART('year',invoice_date)) as total "
      + "from invoice natural inner join order_item " + "group by year " + "order by year;";

  private static final String TOTAL_SALES_REPORT_BY_MONTH = "select DATE_PART('month', invoice_date) as month, date_part('year', invoice_date) as year, count(DATE_PART('month',invoice_date)) as total "
      + "from invoice natural inner join order_item "
      + "group by year, month "
      + "order by year, month;";

  private static final String TOTAL_REVENUE_REPORT_BY_DAY = "select invoice_date as day, sum(order_item_price) as revenue "
      + "from invoice natural inner join order_item " + "group by day " + "order by day;";
  private static final String TOTAL_REVENUE_REPORT_BY_YEAR = "select DATE_PART('year',invoice_date) as year, sum(order_item_price) as revenue "
      + "from invoice natural inner join order_item " + "group by year " + "order by year;";
  private static final String TOTAL_REVENUE_REPORT_BY_MONTH = "select DATE_PART('month',invoice_date) as month, date_part('year', invoice_date) as year, sum(order_item_price) as revenue "
      + "from invoice natural inner join order_item "
      + "group by year, month "
      + "order by year, month;";

  private static final String PRODUCT_TOTAL_SALES_REPORT_BY_DAY = "select invoice_date as day, count(invoice_date) as total "
      + "from invoice natural inner join order_item "
      + "where product_id = ? "
      + "group by day "
      + "order by day;";

  private static final String PRODUCT_TOTAL_SALES_REPORT_BY_YEAR = "select DATE_PART('year', invoice_date) as year, count(DATE_PART('year',invoice_date)) as total "
      + "from invoice natural inner join order_item "
      + "where product_id = ? "
      + "group by year "
      + "order by year;";

  private static final String PRODUCT_TOTAL_SALES_REPORT_BY_MONTH = "select DATE_PART('month', invoice_date) as month, date_part('year', invoice_date) as year, count(DATE_PART('month',invoice_date)) as total "
      + "from invoice natural inner join order_item "
      + "where product_id = ? "
      + "group by year, month " + "order by year, month;";

  private static final String PRODUCT_TOTAL_REVENUE_REPORT_BY_DAY = "select invoice_date as day, sum(order_item_price) as revenue "
      + "from invoice natural inner join order_item "
      + "where product_id = ? "
      + "group by day "
      + "order by day;";;
  private static final String PRODUCT_TOTAL_REVENUE_REPORT_BY_YEAR = "select DATE_PART('year',invoice_date) as year, sum(order_item_price) as revenue "
      + "from invoice natural inner join order_item "
      + "where product_id = ? "
      + "group by year "
      + "order by year;";

  private static final String PRODUCT_TOTAL_REVENUE_REPORT_BY_MONTH = "select DATE_PART('month',invoice_date) as month, date_part('year', invoice_date) as year, sum(order_item_price) as revenue "
      + "from invoice natural inner join order_item "
      + "where product_id = ? "
      + "group by year, month " + "order by year, month;";

  private ReportDAO() {

  }

  public static ReportDAO getInstance() {
    if (singleton == null) {
      singleton = new ReportDAO();
    }
    return singleton;
  }

  public ArrayList<Report> getTotalSalesReportByDay() throws DataSourceException {
    Connection conn = null;
    Report result = null;
    ArrayList<Report> results = new ArrayList<Report>();
    try {
      conn = DataSource.getInstance().getJDBCConnection();
      PreparedStatement stmt = conn.prepareStatement(TOTAL_SALES_REPORT_BY_DAY);
      ResultSet rs = stmt.executeQuery();
      while (rs.next()) {
        result = new Report(rs.getDate(1), rs.getLong(2));
        results.add(result);
      }
      return results;
    } catch (Exception e) {
      throw new DataSourceException("Unable to read data from data source. ", e);
    } finally {
      if (conn != null) {
        try {
          conn.close();
        } catch (Exception e2) {

        }
      }
    }
  }

  public ArrayList<Report> getTotalSalesReportByYear() {
    // TODO Auto-generated method stub
    Connection conn = null;
    Report result = null;
    ArrayList<Report> results = new ArrayList<Report>();
    try {
      conn = DataSource.getInstance().getJDBCConnection();
      PreparedStatement stmt = conn.prepareStatement(TOTAL_SALES_REPORT_BY_YEAR);
      ResultSet rs = stmt.executeQuery();
      while (rs.next()) {
        result = new Report(rs.getLong(1), rs.getLong(2));
        results.add(result);
      }
      return results;
    } catch (Exception e) {
      throw new DataSourceException("Unable to read data from data source. ", e);
    } finally {
      if (conn != null) {
        try {
          conn.close();
        } catch (Exception e2) {

        }
      }
    }
  }

  public ArrayList<Report> getTotalSalesReportByMonth() {
    // TODO Auto-generated method stub
    Connection conn = null;
    Report result = null;
    ArrayList<Report> results = new ArrayList<Report>();
    try {
      conn = DataSource.getInstance().getJDBCConnection();
      PreparedStatement stmt = conn.prepareStatement(TOTAL_SALES_REPORT_BY_MONTH);
      ResultSet rs = stmt.executeQuery();
      while (rs.next()) {
        result = new Report(rs.getLong(1), rs.getLong(2), rs.getLong(3));
        results.add(result);
      }
      return results;
    } catch (Exception e) {
      throw new DataSourceException("Unable to read data from data source. ", e);
    } finally {
      if (conn != null) {
        try {
          conn.close();
        } catch (Exception e2) {

        }
      }
    }
  }

  public ArrayList<Report> getTotalRevenueReportByYear() {
    // TODO Auto-generated method stub
    Connection conn = null;
    Report result = null;
    ArrayList<Report> results = new ArrayList<Report>();
    try {
      conn = DataSource.getInstance().getJDBCConnection();
      PreparedStatement stmt = conn.prepareStatement(TOTAL_REVENUE_REPORT_BY_YEAR);
      ResultSet rs = stmt.executeQuery();
      while (rs.next()) {
        result = new Report(rs.getLong(1), rs.getDouble(2));
        results.add(result);
      }
      return results;
    } catch (Exception e) {
      throw new DataSourceException("Unable to read data from data source. ", e);
    } finally {
      if (conn != null) {
        try {
          conn.close();
        } catch (Exception e2) {

        }
      }
    }
  }

  public ArrayList<Report> getTotalRevenueReportByMonth() {
    // TODO Auto-generated method stub
    Connection conn = null;
    Report result = null;
    ArrayList<Report> results = new ArrayList<Report>();
    try {
      conn = DataSource.getInstance().getJDBCConnection();
      PreparedStatement stmt = conn.prepareStatement(TOTAL_REVENUE_REPORT_BY_MONTH);
      ResultSet rs = stmt.executeQuery();
      while (rs.next()) {
        result = new Report(rs.getLong(1), rs.getLong(2), rs.getDouble(3));
        results.add(result);
      }
      return results;
    } catch (Exception e) {
      throw new DataSourceException("Unable to read data from data source. ", e);
    } finally {
      if (conn != null) {
        try {
          conn.close();
        } catch (Exception e2) {

        }
      }
    }
  }

  public ArrayList<Report> getTotalRevenueReportByDay() {
    // TODO Auto-generated method stub
    Connection conn = null;
    Report result = null;
    ArrayList<Report> results = new ArrayList<Report>();
    try {
      conn = DataSource.getInstance().getJDBCConnection();
      PreparedStatement stmt = conn.prepareStatement(TOTAL_REVENUE_REPORT_BY_DAY);
      ResultSet rs = stmt.executeQuery();
      while (rs.next()) {
        result = new Report(rs.getDate(1), rs.getDouble(2));
        results.add(result);
      }
      return results;
    } catch (Exception e) {
      throw new DataSourceException("Unable to read data from data source. ", e);
    } finally {
      if (conn != null) {
        try {
          conn.close();
        } catch (Exception e2) {

        }
      }
    }
  }

  public ArrayList<Report> getProductTotalSalesReportByDay(long product_id)
      throws DataSourceException {
    Connection conn = null;
    Report result = null;
    ArrayList<Report> results = new ArrayList<Report>();
    try {
      conn = DataSource.getInstance().getJDBCConnection();
      PreparedStatement stmt = conn.prepareStatement(PRODUCT_TOTAL_SALES_REPORT_BY_DAY);
      stmt.setLong(1, product_id);
      ResultSet rs = stmt.executeQuery();
      while (rs.next()) {
        result = new Report(rs.getDate(1), rs.getLong(2));
        results.add(result);
      }
      return results;
    } catch (Exception e) {
      throw new DataSourceException("Unable to read data from data source. ", e);
    } finally {
      if (conn != null) {
        try {
          conn.close();
        } catch (Exception e2) {

        }
      }
    }
  }

  public ArrayList<Report> getProductTotalSalesReportByYear(long product_id) {
    // TODO Auto-generated method stub
    Connection conn = null;
    Report result = null;
    ArrayList<Report> results = new ArrayList<Report>();
    try {
      conn = DataSource.getInstance().getJDBCConnection();
      PreparedStatement stmt = conn.prepareStatement(PRODUCT_TOTAL_SALES_REPORT_BY_YEAR);
      stmt.setLong(1, product_id);
      ResultSet rs = stmt.executeQuery();
      while (rs.next()) {
        result = new Report(rs.getLong(1), rs.getLong(2));
        results.add(result);
      }
      return results;
    } catch (Exception e) {
      throw new DataSourceException("Unable to read data from data source. ", e);
    } finally {
      if (conn != null) {
        try {
          conn.close();
        } catch (Exception e2) {

        }
      }
    }
  }

  public ArrayList<Report> getProductTotalSalesReportByMonth(long product_id) {
    // TODO Auto-generated method stub
    Connection conn = null;
    Report result = null;
    ArrayList<Report> results = new ArrayList<Report>();
    try {
      conn = DataSource.getInstance().getJDBCConnection();
      PreparedStatement stmt = conn.prepareStatement(PRODUCT_TOTAL_SALES_REPORT_BY_MONTH);
      stmt.setLong(1, product_id);
      ResultSet rs = stmt.executeQuery();
      while (rs.next()) {
        result = new Report(rs.getLong(1), rs.getLong(2), rs.getLong(3));
        results.add(result);
      }
      return results;
    } catch (Exception e) {
      throw new DataSourceException("Unable to read data from data source. ", e);
    } finally {
      if (conn != null) {
        try {
          conn.close();
        } catch (Exception e2) {

        }
      }
    }
  }

  public ArrayList<Report> getProductTotalRevenueReportByYear(long product_id) {
    // TODO Auto-generated method stub
    Connection conn = null;
    Report result = null;
    ArrayList<Report> results = new ArrayList<Report>();
    try {
      conn = DataSource.getInstance().getJDBCConnection();
      PreparedStatement stmt = conn.prepareStatement(PRODUCT_TOTAL_REVENUE_REPORT_BY_YEAR);
      stmt.setLong(1, product_id);
      ResultSet rs = stmt.executeQuery();
      while (rs.next()) {
        result = new Report(rs.getLong(1), rs.getDouble(2));
        results.add(result);
      }
      return results;
    } catch (Exception e) {
      throw new DataSourceException("Unable to read data from data source. ", e);
    } finally {
      if (conn != null) {
        try {
          conn.close();
        } catch (Exception e2) {

        }
      }
    }
  }

  public ArrayList<Report> getProductTotalRevenueReportByMonth(long product_id) {
    // TODO Auto-generated method stub
    Connection conn = null;
    Report result = null;
    ArrayList<Report> results = new ArrayList<Report>();
    try {
      conn = DataSource.getInstance().getJDBCConnection();
      PreparedStatement stmt = conn.prepareStatement(PRODUCT_TOTAL_REVENUE_REPORT_BY_MONTH);
      stmt.setLong(1, product_id);
      ResultSet rs = stmt.executeQuery();
      while (rs.next()) {
        result = new Report(rs.getLong(1), rs.getLong(2), rs.getDouble(3));
        results.add(result);
      }
      return results;
    } catch (Exception e) {
      throw new DataSourceException("Unable to read data from data source. ", e);
    } finally {
      if (conn != null) {
        try {
          conn.close();
        } catch (Exception e2) {

        }
      }
    }
  }

  public ArrayList<Report> getProductTotalRevenueReportByDay(long product_id) {
    // TODO Auto-generated method stub
    Connection conn = null;
    Report result = null;
    ArrayList<Report> results = new ArrayList<Report>();
    try {
      conn = DataSource.getInstance().getJDBCConnection();
      PreparedStatement stmt = conn.prepareStatement(PRODUCT_TOTAL_REVENUE_REPORT_BY_DAY);
      stmt.setLong(1, product_id);
      ResultSet rs = stmt.executeQuery();
      while (rs.next()) {
        result = new Report(rs.getDate(1), rs.getDouble(2));
        results.add(result);
      }
      return results;
    } catch (Exception e) {
      throw new DataSourceException("Unable to read data from data source. ", e);
    } finally {
      if (conn != null) {
        try {
          conn.close();
        } catch (Exception e2) {

        }
      }
    }
  }

}
