package pl.coderstrust.model.counterparts;

public class MyCompanySell extends Seller {

  /**
   * Defines my company as a seller.
   */

  public MyCompanySell() {
    super.setName("My Compnay name");
    super.setVatId("PL9988555333");
    super.setAddress1("Building, street");
    super.setAddress2("Street, City");
    super.setZip("00-333");
    super.setAccountNumber("12 0000 0000 0000 1111 1111");
  } //used by JACKSON
}
