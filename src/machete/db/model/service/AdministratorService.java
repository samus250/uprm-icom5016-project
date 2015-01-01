package machete.db.model.service;

import machete.db.model.dao.AdministratorDAO;
import machete.db.model.dto.Administrator;

public class AdministratorService {

  public static enum add_status {
    SUCCESS, USERNAME_TAKEN, PASSWORD_VERIFICATION_ERROR, OTHER_FAILURE
  }

  public static enum remove_status {
    SUCCESS, OTHER_FAILURE
  }

  private AdministratorService() {

  }

  public static AdministratorService newInstance() {
    return new AdministratorService();
  }

  public Administrator getAdministrator(String userName) {
    AdministratorDAO dao = AdministratorDAO.getInstance();
    return dao.getAdministratorByUsername(userName);
  }

  public Administrator getAdministratorById(String userId) {
    AdministratorDAO dao = AdministratorDAO.getInstance();
    long id = Long.parseLong(userId);
    return dao.getAdministrator(id);
  }

  public add_status addAdministrator(String userName, String email, String password,
      String verifyPassword) {

    // validate all parameters
    if (!password.equals(verifyPassword)) {
      return add_status.PASSWORD_VERIFICATION_ERROR;
    }

    // try to throw to DAO, error might indicate username is taken
    AdministratorDAO dao = AdministratorDAO.getInstance();
    if (dao.getAdministratorByUsername(userName) != null) {
      return add_status.USERNAME_TAKEN;
    }

    boolean result = dao.insertAdministrator(userName, password, email);

    if (!result) {
      return add_status.OTHER_FAILURE;
    } else {
      return add_status.SUCCESS;
    }
  }

  public remove_status removeAdministrator(String admin_id) {
    AdministratorDAO dao = AdministratorDAO.getInstance();
    long id = Long.parseLong(admin_id);
    boolean success = dao.deleteAdministrator(id);
    if (!success)
      return remove_status.OTHER_FAILURE;
    return remove_status.SUCCESS;
  }

}
