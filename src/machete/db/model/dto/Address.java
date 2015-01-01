package machete.db.model.dto;

public class Address {
  private long addressId;
  private long userId;
  private String addressLine1;
  private String addressLine2;
  private String addressLine3;
  private String addressCity;
  private String addressState;
  private String addressCountry;
  private String addressZip;
  private String addressAlias;

  public String getAddressAlias() {
    return addressAlias;
  }

  public void setAddressAlias(String addressAlias) {
    this.addressAlias = addressAlias;
  }

  public Address() {

  }

  public Address(long addressId, long userId, String addressLine1, String addressLine2,
      String addressLine3, String addressCity, String addressState, String addressCountry,
      String addressZip) {
    super();
    this.addressId = addressId;
    this.userId = userId;
    this.addressLine1 = addressLine1;
    this.addressLine2 = addressLine2;
    this.addressLine3 = addressLine3;
    this.addressCity = addressCity;
    this.addressState = addressState;
    this.addressCountry = addressCountry;
    this.addressZip = addressZip;
  }

  public long getAddressId() {
    return addressId;
  }

  public void setAddressId(long addressId) {
    this.addressId = addressId;
  }

  public long getUserId() {
    return userId;
  }

  public void setUserId(long userId) {
    this.userId = userId;
  }

  public String getAddressLine1() {
    return addressLine1;
  }

  public void setAddressLine1(String addressLine1) {
    this.addressLine1 = addressLine1;
  }

  public String getAddressLine2() {
    return addressLine2;
  }

  public void setAddressLine2(String addressLine2) {
    this.addressLine2 = addressLine2;
  }

  public String getAddressLine3() {
    return addressLine3;
  }

  public void setAddressLine3(String addressLine3) {
    this.addressLine3 = addressLine3;
  }

  public String getAddressCity() {
    return addressCity;
  }

  public void setAddressCity(String addressCity) {
    this.addressCity = addressCity;
  }

  public String getAddressState() {
    return addressState;
  }

  public void setAddressState(String addressState) {
    this.addressState = addressState;
  }

  public String getAddressCountry() {
    return addressCountry;
  }

  public void setAddressCountry(String addressCountry) {
    this.addressCountry = addressCountry;
  }

  public String getAddressZip() {
    return addressZip;
  }

  public void setAddressZip(String addressZip) {
    this.addressZip = addressZip;
  }

}
