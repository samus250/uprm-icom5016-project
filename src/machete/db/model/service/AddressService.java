package machete.db.model.service;

import java.util.ArrayList;

import machete.db.model.dao.AddressDAO;
import machete.db.model.dto.Address;

public class AddressService {

  private AddressService() {

  }

  public static AddressService newInstance() {
    return new AddressService();
  }

  public static enum add_status {
    SUCCESS, USERNAME_TAKEN, PASSWORD_VERIFICATION_ERROR, OTHER_FAILURE
  }

  public Address getAddress(String addressId) {
    AddressDAO dao = AddressDAO.getInstance();
    long id = Long.parseLong(addressId);
    return dao.getAddress(id);
  }

  public Address getAddress(long addressId) {
    AddressDAO dao = AddressDAO.getInstance();
    return dao.getAddress(addressId);
  }

  public ArrayList<Address> getAddressesByUserId(String userId) {
    AddressDAO dao = AddressDAO.getInstance();
    long id = Long.parseLong(userId);
    return dao.getAddressesByUserId(id);
  }

  public add_status addAddress(String userId, String line1, String line2, String line3,
      String city, String state, String country, String zip, String alias) {
    AddressDAO dao = AddressDAO.getInstance();
    long uId = Long.parseLong(userId);
    boolean r = dao.insertAddress(uId, line1, line2, line3, city, state, country, zip, alias);
    if (!r)
      return add_status.OTHER_FAILURE;

    return add_status.SUCCESS;
  }

  public add_status updateAddress(String addressId, String line1, String line2, String line3,
      String city, String state, String country, String zip, String alias) {
    AddressDAO dao = AddressDAO.getInstance();
    long aId = Long.parseLong(addressId);
    boolean r = dao.updateAddress(aId, line1, line2, line3, city, state, country, zip, alias);
    if (!r)
      return add_status.OTHER_FAILURE;

    return add_status.SUCCESS;
  }
}
