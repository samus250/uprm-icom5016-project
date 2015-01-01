package machete.db.model.dto;

public class OrderItem {
  private long invoiceId;
  private long productId;
  private long orderItemQuantity;
  private double orderItemPrice;
  private double orderItemTprice;

  public OrderItem() {

  }

  public OrderItem(long invoiceId, long productId, long orderItemQuantity, double orderItemPrice,
      double orderItemTprice) {
    super();
    this.invoiceId = invoiceId;
    this.productId = productId;
    this.orderItemQuantity = orderItemQuantity;
    this.orderItemPrice = orderItemPrice;
    this.orderItemTprice = orderItemTprice;
  }

  public long getInvoiceId() {
    return invoiceId;
  }

  public void setInvoiceId(long invoiceId) {
    this.invoiceId = invoiceId;
  }

  public long getProductId() {
    return productId;
  }

  public void setProductId(long productId) {
    this.productId = productId;
  }

  public long getOrderItemQuantity() {
    return orderItemQuantity;
  }

  public void setOrderItemQuantity(long orderItemQuantity) {
    this.orderItemQuantity = orderItemQuantity;
  }

  public double getOrderItemPrice() {
    return orderItemPrice;
  }

  public void setOrderItemPrice(double orderItemPrice) {
    this.orderItemPrice = orderItemPrice;
  }

  public double getOrderItemTprice() {
    return orderItemTprice;
  }

  public void setOrderItemTprice(double orderItemTprice) {
    this.orderItemTprice = orderItemTprice;
  }

}
