package machete.db.model.service;

import machete.db.model.dao.CustomerDAO;
import machete.db.model.dto.Customer;

public class CustomerService {

  private CustomerService() {

  }

  public static CustomerService newInstance() {
    return new CustomerService();
  }

  public static enum add_status {
    SUCCESS, USERNAME_TAKEN, PASSWORD_VERIFICATION_ERROR, OTHER_FAILURE
  }

  public static enum remove_status {
    SUCCESS, OTHER_FAILURE
  }

  public Customer getCustomer(String userName) {
    CustomerDAO dao = CustomerDAO.getInstance();
    return dao.getCustomerByUsername(userName);
  }

  public Customer getCustomerById(String userId) {
    CustomerDAO dao = CustomerDAO.getInstance();
    long id = Long.parseLong(userId);
    return dao.getCustomer(id);
  }

  public add_status addCustomer(String firstName, String middleName, String lastName,
      String userName, String email, String password, String verifyPassword) {

    // validate all parameters
    if (!password.equals(verifyPassword)) {
      return add_status.PASSWORD_VERIFICATION_ERROR;
    }

    // try to throw to DAO, error might indicate username is taken
    CustomerDAO dao = CustomerDAO.getInstance();
    if (dao.getCustomerByUsername(userName) != null) {
      return add_status.USERNAME_TAKEN;
    }

    boolean result = dao.insertCustomer(userName, password, email, firstName, middleName, lastName);

    if (!result) {
      return add_status.OTHER_FAILURE;
    } else {
      return add_status.SUCCESS;
    }
  }

  public remove_status removeCustomer(String customer_id) {
    CustomerDAO dao = CustomerDAO.getInstance();
    long id = Long.parseLong(customer_id);
    boolean success = dao.deleteCustomer(id);
    if (!success)
      return remove_status.OTHER_FAILURE;
    return remove_status.SUCCESS;
  }

  public add_status updateCustomer(String userId, String firstName, String middleName,
      String lastName, String email, String password, String verifyPassword) {

    // validate all parameters
    if (!password.equals(verifyPassword)) {
      return add_status.PASSWORD_VERIFICATION_ERROR;
    }

    // try to throw to DAO, error might indicate username is taken
    CustomerDAO dao = CustomerDAO.getInstance();
    long uid = Long.parseLong(userId);
    boolean result = dao.updateCustomer(uid, password, email, firstName, middleName, lastName);

    if (!result) {
      return add_status.OTHER_FAILURE;
    } else {
      return add_status.SUCCESS;
    }
  }
}
