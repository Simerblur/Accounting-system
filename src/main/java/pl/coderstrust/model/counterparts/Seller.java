package pl.coderstrust.model.counterparts;

public class Seller {

  private String name;
  private String address1;
  private String address2;
  private String zip;
  private String vatId;
  private String accountNumber;

  public Seller() {
  } //used by JACKSON

  public Seller(String sellerName, String sellerVatId) {
    this.name = sellerName;
    this.vatId = sellerVatId;
  }

  /**
   * Test sample Javadoc.
   */

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getAddress1() {
    return address1;
  }

  public void setAddress1(String address1) {
    this.address1 = address1;
  }

  public String getAddress2() {
    return address2;
  }

  public void setAddress2(String address2) {
    this.address2 = address2;
  }

  public String getZip() {
    return zip;
  }

  public void setZip(String zip) {
    this.zip = zip;
  }

  public String getVatId() {
    return vatId;
  }

  public void setVatId(String vatId) {
    this.vatId = vatId;
  }

  public String getAccountNumber() {
    return accountNumber;
  }

  public void setAccountNumber(String accountNumber) {
    this.accountNumber = accountNumber;
  }
}
