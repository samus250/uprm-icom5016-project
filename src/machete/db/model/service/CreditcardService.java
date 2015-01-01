package machete.db.model.service;

import java.util.ArrayList;

import machete.db.model.dao.CreditcardDAO;
import machete.db.model.dto.Creditcard;

public class CreditcardService {

  private CreditcardService() {

  }

  public static CreditcardService newInstance() {
    return new CreditcardService();
  }

  public static enum add_status {
    SUCCESS, OTHER_FAILURE
  }

  public Creditcard getCreditCard(String creditcardId) {
    CreditcardDAO dao = CreditcardDAO.getInstance();
    long id = Long.parseLong(creditcardId);
    return dao.getCreditcard(id);
  }

  public ArrayList<Creditcard> getCreditCardsByUserId(String userId) {
    CreditcardDAO dao = CreditcardDAO.getInstance();
    long id = Long.parseLong(userId);
    return dao.getCreditCardsByUserId(id);
  }

  public add_status addCreditCard(String userId, String addressId, String number,
      String securityNumber, String month, String year, String alias) {
    CreditcardDAO dao = CreditcardDAO.getInstance();
    long uId = Long.parseLong(userId);
    long aId = Long.parseLong(addressId);
    boolean r = dao.insertCreditcard(uId, aId, number, securityNumber, month, year, alias);
    if (!r)
      return add_status.OTHER_FAILURE;

    return add_status.SUCCESS;
  }

  public add_status updateCreditCard(String creditCardId, String addressId, String number,
      String securityNumber, String month, String year, String alias) {
    CreditcardDAO dao = CreditcardDAO.getInstance();
    long cId = Long.parseLong(creditCardId);
    long aId = Long.parseLong(addressId);
    boolean r = dao.updateCreditcard(cId, aId, number, securityNumber, month, year, alias);
    if (!r)
      return add_status.OTHER_FAILURE;

    return add_status.SUCCESS;
  }
}
