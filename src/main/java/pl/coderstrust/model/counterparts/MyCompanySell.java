package pl.coderstrust.model.counterparts;

public class MyCompanySell extends Seller {

  private String myName;
  private String myAddress1;
  private String myAddress2;
  private String myZip;
  private String myVatId;
  private String myAccountNumber;

  /**
   * Defines my company as a seller.
   */

  public MyCompanySell() {
    this.myName = "My Compnay name";
    this.myAddress1 = "Building, street";
    this.myAddress2 = "Street, City";
    this.myZip = "00-000";
    this.myAccountNumber = "12 0000 0000 0000 1111 1111";
    this.myVatId = "PL9988555333";
  } //used by JACKSON

  /**
   * Test sample Javadoc.
   */

  public String getMyName() {
    return myName;
  }

  public void setMyName(String myName) {
    this.myName = myName;
  }

  public String getMyAddress1() {
    return myAddress1;
  }

  public void setMyAddress1(String myAddress1) {
    this.myAddress1 = myAddress1;
  }

  public String getMyAddress2() {
    return myAddress2;
  }

  public void setMyAddress2(String myAddress2) {
    this.myAddress2 = myAddress2;
  }

  public String getMyZip() {
    return myZip;
  }

  public void setMyZip(String myZip) {
    this.myZip = myZip;
  }

  public String getMyVatId() {
    return myVatId;
  }

  public void setMyVatId(String myVatId) {
    this.myVatId = myVatId;
  }

  public String getMyAccountNumber() {
    return myAccountNumber;
  }

  public void setMyAccountNumber(String myAccountNumber) {
    this.myAccountNumber = myAccountNumber;
  }
}
