package machete.db.model.dto;

import java.sql.Date;

public class Invoice {
  private long invoiceId;
  private long userId;
  private long creditcardId;
  private Date invoiceDate;
  private double invoiceTprice;
  private long addressId;

  public long getAddressId() {
    return addressId;
  }

  public void setAddressId(long addressId) {
    this.addressId = addressId;
  }

  public Invoice() {

  }

  public Invoice(long invoiceId, long userId, long creditcardId, Date invoiceDate,
      double invoiceTprice) {
    super();
    this.invoiceId = invoiceId;
    this.userId = userId;
    this.creditcardId = creditcardId;
    this.invoiceDate = invoiceDate;
    this.invoiceTprice = invoiceTprice;
  }

  public long getInvoiceId() {
    return invoiceId;
  }

  public void setInvoiceId(long invoiceId) {
    this.invoiceId = invoiceId;
  }

  public long getUserId() {
    return userId;
  }

  public void setUserId(long userId) {
    this.userId = userId;
  }

  public long getCreditcardId() {
    return creditcardId;
  }

  public void setCreditcardId(long creditcardId) {
    this.creditcardId = creditcardId;
  }

  public Date getInvoiceDate() {
    return invoiceDate;
  }

  public void setInvoiceDate(Date invoiceDate) {
    this.invoiceDate = invoiceDate;
  }

  public double getInvoiceTprice() {
    return invoiceTprice;
  }

  public void setInvoiceTprice(double invoiceTprice) {
    this.invoiceTprice = invoiceTprice;
  }

}
