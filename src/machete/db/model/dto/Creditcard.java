package machete.db.model.dto;

public class Creditcard {
  private long creditcardId;
  private long userId;
  private long addressId;
  private String creditcardNumber;
  private String creditcardSecuritynumber;
  private String creditcardMonth;
  private String creditcardAlias;

  public String getCreditcardAlias() {
    return creditcardAlias;
  }

  public void setCreditcardAlias(String creditcardAlias) {
    this.creditcardAlias = creditcardAlias;
  }

  public String getCreditcardMonth() {
    return creditcardMonth;
  }

  public void setCreditcardMonth(String creditcardMonth) {
    this.creditcardMonth = creditcardMonth;
  }

  public String getCreditcardYear() {
    return creditcardYear;
  }

  public void setCreditcardYear(String creditcardYear) {
    this.creditcardYear = creditcardYear;
  }

  private String creditcardYear;

  public Creditcard() {

  }

  public Creditcard(long creditcardId, long userId, long addressId, String creditcardNumber,
      String creditcardSecuritynumber) {
    super();
    this.creditcardId = creditcardId;
    this.userId = userId;
    this.addressId = addressId;
    this.creditcardNumber = creditcardNumber;
    this.creditcardSecuritynumber = creditcardSecuritynumber;
  }

  public long getCreditcardId() {
    return creditcardId;
  }

  public void setCreditcardId(long creditcardId) {
    this.creditcardId = creditcardId;
  }

  public long getUserId() {
    return userId;
  }

  public void setUserId(long userId) {
    this.userId = userId;
  }

  public long getAddressId() {
    return addressId;
  }

  public void setAddressId(long addressId) {
    this.addressId = addressId;
  }

  public String getCreditcardNumber() {
    return creditcardNumber;
  }

  public void setCreditcardNumber(String creditcardNumber) {
    this.creditcardNumber = creditcardNumber;
  }

  public String getCreditcardSecuritynumber() {
    return creditcardSecuritynumber;
  }

  public void setCreditcardSecuritynumber(String creditcardSecuritynumber) {
    this.creditcardSecuritynumber = creditcardSecuritynumber;
  }

}
