package machete.db.model.service;

import java.util.ArrayList;

import machete.db.Pair;
import machete.db.model.dao.InvoiceDAO;
import machete.db.model.dto.Invoice;
import machete.db.model.dto.OrderItem;
import machete.db.model.dto.Product;

public class InvoiceService {
  public static enum add_status {
    SUCCESS, OTHER_FAILURE
  }

  public static enum remove_status {
    SUCCESS, OTHER_FAILURE
  }

  private InvoiceService() {

  }

  public static InvoiceService newInstance() {
    return new InvoiceService();
  }

  public Invoice getInvoice(String invoice_id) {
    InvoiceDAO dao = InvoiceDAO.getInstance();
    // validate stuff
    long id = Long.parseLong(invoice_id);
    return dao.getInvoice(id);
  }

  public ArrayList<Invoice> getInvoicesByUserId() {
    InvoiceDAO dao = InvoiceDAO.getInstance();
    return dao.getInvoicesByUserId();
  }

  public Pair<add_status, Long> addInvoice(String userId, long creditcardId, long addressId,
      ArrayList<Pair<Product, Long>> productQuantityPairs) {
    InvoiceDAO dao = InvoiceDAO.getInstance();
    long uid = Long.parseLong(userId);
    long cid = creditcardId;
    long aid = addressId;
    double tp = 0;

    // get today's date
    java.util.Date today = new java.util.Date();
    java.sql.Date date = new java.sql.Date(today.getTime());

    // get all the order items
    ArrayList<OrderItem> orderItems = new ArrayList<OrderItem>();
    for (Pair<Product, Long> p : productQuantityPairs) {
      Product product = p.getFirst();
      Long quantity = p.getSecond();
      double orderItemPrice = product.getProductPrice() * quantity;
      OrderItem orderItem = new OrderItem(0, product.getProductId(), quantity,
          product.getProductPrice(), orderItemPrice);
      orderItems.add(orderItem);
      tp += orderItemPrice;
    }

    long invoiceId = dao.insertInvoice(uid, cid, aid, date, tp, orderItems);
    if (invoiceId == -1)
      return new Pair<add_status, Long>(add_status.OTHER_FAILURE, invoiceId);

    return new Pair<add_status, Long>(add_status.SUCCESS, invoiceId);
  }

  public remove_status removeInvoice(String invoice_id) {
    InvoiceDAO dao = InvoiceDAO.getInstance();
    long id = Long.parseLong(invoice_id);
    boolean success = dao.deleteInvoice(id);
    if (!success) {
      return remove_status.OTHER_FAILURE;
    }
    return remove_status.SUCCESS;
  }
}
