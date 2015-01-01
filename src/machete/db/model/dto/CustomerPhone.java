package machete.db.model.dto;

public class CustomerPhone {
  private long userId;
  private String phoneNumber;

  public CustomerPhone() {

  }

  public CustomerPhone(long userId, String phoneNumber) {
    super();
    this.userId = userId;
    this.phoneNumber = phoneNumber;
  }

  public long getUserId() {
    return userId;
  }

  public void setUserId(long userId) {
    this.userId = userId;
  }

  public String getPhoneNumber() {
    return phoneNumber;
  }

  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }

}
