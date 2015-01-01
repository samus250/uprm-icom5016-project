package machete.db.model.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import machete.db.model.dto.Invoice;
import machete.db.model.dto.OrderItem;

public class InvoiceDAO {
  private static InvoiceDAO singleton = null;
  private static final String INVOICE_BY_ID = "select invoice_id, user_id, creditcard_id, address_id, invoice_date, invoice_tprice "
      + "from invoice where invoice_id = ?;";

  private static final String INVOICES_BY_USER_ID = "select invoice_id, user_id, creditcard_id, address_id  invoice_date, invoice_tprice "
      + "from invoice where user_id = ?;";

  private static final String INSERT_INVOICE = "insert into invoice (user_id, creditcard_id, address_id, invoice_date, invoice_tprice) "
      + "values (?, ?, ?, ?, ?);";

  private static final String DELETE_INVOICE = "delete from invoice where invoice_id = ?;";

  private static final String INSERT_ORDER_ITEM = "insert into order_item (invoice_id, product_id, order_item_quantity, order_item_price, order_item_tprice) "
      + "values (?, ?, ?, ?, ?);";

  private InvoiceDAO() {

  }

  public static InvoiceDAO getInstance() {
    if (singleton == null) {
      singleton = new InvoiceDAO();
    }
    return singleton;
  }

  public Invoice getInvoice(long id) throws DataSourceException {
    Connection conn = null;
    Invoice result = null;
    try {
      conn = DataSource.getInstance().getJDBCConnection();
      PreparedStatement stmt = conn.prepareStatement(INVOICE_BY_ID);
      stmt.setLong(1, id);
      ResultSet rs = stmt.executeQuery();
      if (rs.next()) {
        result = new Invoice();
        result.setInvoiceId(rs.getLong(1));
        result.setUserId(rs.getLong(2));
        result.setCreditcardId(rs.getLong(3));
        result.setAddressId(rs.getLong(4));
        result.setInvoiceDate(rs.getDate(5));
        result.setInvoiceTprice(rs.getDouble(6));

      }
      return result;
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

  public ArrayList<Invoice> getInvoicesByUserId() throws DataSourceException {
    Connection conn = null;
    Invoice result = null;
    try {
      conn = DataSource.getInstance().getJDBCConnection();
      PreparedStatement stmt = conn.prepareStatement(INVOICES_BY_USER_ID);
      ResultSet rs = stmt.executeQuery();
      ArrayList<Invoice> results = new ArrayList<Invoice>();
      while (rs.next()) {
        result = new Invoice();
        result.setInvoiceId(rs.getLong(1));
        result.setUserId(rs.getLong(2));
        result.setCreditcardId(rs.getLong(3));
        result.setAddressId(rs.getLong(4));
        result.setInvoiceDate(rs.getDate(5));
        result.setInvoiceTprice(rs.getDouble(6));
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

  public long insertInvoice(long user_id, long creditcard_id, long address_id, Date date,
      double tprice, ArrayList<OrderItem> orderItems) throws DataSourceException {
    Connection conn = null;
    long invoiceId = -1;
    // this is where transactions go...
    // we will receive a list of order items to be added, invoice must be added
    // first, and
    // then the order items. If anything fails during the process, we must roll
    // back.
    try {
      conn = DataSource.getInstance().getJDBCConnection();
      conn.setAutoCommit(false);
      PreparedStatement stmt = conn.prepareStatement(INSERT_INVOICE,
          Statement.RETURN_GENERATED_KEYS);
      stmt.setLong(1, user_id);
      stmt.setLong(2, creditcard_id);
      stmt.setLong(3, address_id);
      stmt.setDate(4, date);
      stmt.setDouble(5, tprice);
      int result = stmt.executeUpdate();
      if (result == 0) {
        return -1;
      }
      // get the generated key
      ResultSet rs = stmt.getGeneratedKeys();
      if (rs.next()) {
        invoiceId = rs.getLong(1);

        // insert all order items
        for (OrderItem orderItem : orderItems) {
          stmt = conn.prepareStatement(INSERT_ORDER_ITEM);
          stmt.setLong(1, invoiceId);
          stmt.setLong(2, orderItem.getProductId());
          stmt.setLong(3, orderItem.getOrderItemQuantity());
          stmt.setDouble(4, orderItem.getOrderItemPrice());
          stmt.setDouble(5, orderItem.getOrderItemTprice());
          result = stmt.executeUpdate();

          if (result != 1) {
            // Something went wrong while adding order items
            // Roll back the transaction and set the auto-commit behavior back.
            try {
              conn.rollback();
              conn.setAutoCommit(true);
            } catch (SQLException e) {
            }
            throw new SQLException("Unable to get autogenered Keys");
          }
        }
        // if we could add all order items, then it went well.
        try {
          conn.commit();
        } catch (SQLException e) {
          // problem committing, rollback
          try {
            conn.rollback();
            conn.setAutoCommit(true);
          } catch (SQLException e2) {

          }
          throw new SQLException("Unable to commit transaction.", e);
        }
        // Set the auto-commit behavior back.
        try {
          conn.setAutoCommit(true);
        } catch (SQLException e) {

        }

        // updates done, return the invoice id
        return invoiceId;
      } else {
        // Something is wrong because we could not get the invoice id
        // Roll back the transaction and set the auto-commit behavior back.
        try {
          conn.rollback();
          conn.setAutoCommit(true);
        } catch (SQLException e) {
        }
        throw new SQLException("Cannot get autogenerated values.");
      }
    } catch (Exception e) {
      throw new DataSourceException("Unable to insert data from data source. ", e);
    } finally {
      if (conn != null) {
        try {
          conn.close();
        } catch (Exception e2) {

        }
      }
    }
  }

  public boolean deleteInvoice(long id) throws DataSourceException {
    Connection conn = null;

    try {
      conn = DataSource.getInstance().getJDBCConnection();
      PreparedStatement stmt = conn.prepareStatement(DELETE_INVOICE);
      stmt.setLong(1, id);
      int result = stmt.executeUpdate();
      if (result == 0) {
        return false;
      } else {
        return true;
      }
    } catch (Exception e) {
      throw new DataSourceException("Unable to delete data from data source. ", e);
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
