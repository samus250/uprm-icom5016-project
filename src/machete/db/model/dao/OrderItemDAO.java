package machete.db.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import machete.db.model.dto.OrderItem;

public class OrderItemDAO {
  private static OrderItemDAO singleton = null;

  private static final String ORDERITEMS_BY_INVOICE_ID = "select invoice_id, product_id, order_item_quantity, order_item_price, order_item_tprice "
      + "from order_item where invoice_id = ?;";

  private OrderItemDAO() {

  }

  public static OrderItemDAO getInstance() {
    if (singleton == null) {
      singleton = new OrderItemDAO();
    }
    return singleton;
  }

  public ArrayList<OrderItem> getOrderItemsByInvoiceId(long invoiceId) throws DataSourceException {
    Connection conn = null;
    OrderItem result = null;
    try {
      conn = DataSource.getInstance().getJDBCConnection();
      PreparedStatement stmt = conn.prepareStatement(ORDERITEMS_BY_INVOICE_ID);
      stmt.setLong(1, invoiceId);
      ResultSet rs = stmt.executeQuery();
      ArrayList<OrderItem> results = new ArrayList<OrderItem>();
      while (rs.next()) {
        result = new OrderItem();
        result.setInvoiceId(rs.getLong(1));
        result.setProductId(rs.getLong(2));
        result.setOrderItemQuantity(rs.getLong(3));
        result.setOrderItemPrice(rs.getDouble(4));
        result.setOrderItemTprice(rs.getDouble(5));
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
