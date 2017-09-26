package pl.coderstrust.model.counterparts;

public class Seller {

  private String name;
  private String address1;
  private String address2;
  private String zip;
  private String vatId;
  private String accountNumber;

  public Seller() {
  } //used by Jason

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

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (!(obj instanceof Seller)) {
      return false;
    }

    Seller seller = (Seller) obj;

    if (name != null ? !name.equals(seller.name) : seller.name != null) {
      return false;
    }
    if (address1 != null ? !address1.equals(seller.address1) : seller.address1 != null) {
      return false;
    }
    if (address2 != null ? !address2.equals(seller.address2) : seller.address2 != null) {
      return false;
    }
    if (zip != null ? !zip.equals(seller.zip) : seller.zip != null) {
      return false;
    }
    if (vatId != null ? !vatId.equals(seller.vatId) : seller.vatId != null) {
      return false;
    }
    return accountNumber != null ? accountNumber.equals(seller.accountNumber)
        : seller.accountNumber == null;
  }

  @Override
  public int hashCode() {
    int result = name != null ? name.hashCode() : 0;
    result = 31 * result + (address1 != null ? address1.hashCode() : 0);
    result = 31 * result + (address2 != null ? address2.hashCode() : 0);
    result = 31 * result + (zip != null ? zip.hashCode() : 0);
    result = 31 * result + (vatId != null ? vatId.hashCode() : 0);
    result = 31 * result + (accountNumber != null ? accountNumber.hashCode() : 0);
    return result;
  }

  @Override
  public String toString() {
    return "Seller{" + "name='" + name + '\'' + ", address1='" + address1 + '\'' + ", address2='"
        + address2 + '\'' + ", zip='" + zip + '\'' + ", vatId='" + vatId + '\''
        + ", accountNumber='" + accountNumber + '\'' + '}';
  }
}
