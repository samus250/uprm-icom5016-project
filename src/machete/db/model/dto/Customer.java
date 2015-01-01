package machete.db.model.dto;

public class Customer {
  private long userId;
  private String userName;
  private String userPassword;
  private String userEmail;
  private String customerFirstname;
  private String customerMiddlename;
  private String customerLastname;

  public Customer() {

  }

  public Customer(long userId, String userName, String userPassword, String userEmail,
      String customerFirstname, String customerMiddlename, String customerLastname) {
    super();
    this.userId = userId;
    this.userName = userName;
    this.userPassword = userPassword;
    this.userEmail = userEmail;
    this.customerFirstname = customerFirstname;
    this.customerMiddlename = customerMiddlename;
    this.customerLastname = customerLastname;
  }

  public long getUserId() {
    return userId;
  }

  public void setUserId(long userId) {
    this.userId = userId;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public String getUserPassword() {
    return userPassword;
  }

  public void setUserPassword(String userPassword) {
    this.userPassword = userPassword;
  }

  public String getUserEmail() {
    return userEmail;
  }

  public void setUserEmail(String userEmail) {
    this.userEmail = userEmail;
  }

  public String getCustomerFirstname() {
    return customerFirstname;
  }

  public void setCustomerFirstname(String customerFirstname) {
    this.customerFirstname = customerFirstname;
  }

  public String getCustomerMiddlename() {
    return customerMiddlename;
  }

  public void setCustomerMiddlename(String customerMiddlename) {
    this.customerMiddlename = customerMiddlename;
  }

  public String getCustomerLastname() {
    return customerLastname;
  }

  public void setCustomerLastname(String customerLastname) {
    this.customerLastname = customerLastname;
  }

}
