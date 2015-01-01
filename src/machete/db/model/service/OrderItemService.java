package machete.db.model.service;

import java.util.ArrayList;

import machete.db.model.dao.OrderItemDAO;
import machete.db.model.dto.OrderItem;

public class OrderItemService {
  public static enum add_status {
    SUCCESS, OTHER_FAILURE
  }

  public static enum remove_status {
    SUCCESS, OTHER_FAILURE
  }

  private OrderItemService() {

  }

  public static OrderItemService newInstance() {
    return new OrderItemService();
  }

  public ArrayList<OrderItem> getOrderItemsByInvoiceId(String invoiceId) {
    ArrayList<OrderItem> orderItems = null;
    OrderItemDAO dao = OrderItemDAO.getInstance();
    long id = Long.parseLong(invoiceId);
    orderItems = dao.getOrderItemsByInvoiceId(id);
    return orderItems;
  }
}
