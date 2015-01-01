package machete.db.model.dto;

public class Administrator {
  private long userId;
  private String userName;
  private String userPassword;
  private String userEmail;
  private String AdministratorLevel;

  public Administrator() {

  }

  public Administrator(long userId, String userName, String userPassword, String userEmail,
      String administratorLevel) {
    super();
    this.userId = userId;
    this.userName = userName;
    this.userPassword = userPassword;
    this.userEmail = userEmail;
    AdministratorLevel = administratorLevel;
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

  public String getAdministratorLevel() {
    return AdministratorLevel;
  }

  public void setAdministratorLevel(String administratorLevel) {
    AdministratorLevel = administratorLevel;
  }

}
