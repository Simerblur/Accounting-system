package pl.coderstrust.model.counterparts;

public class Counterparts {
  private Buyer buyer;
  private Seller seller;

  public Counterparts() {
  } // used by JSON.

  public Counterparts(Buyer buyer, Seller seller) {
    this.buyer = buyer;
    this.seller = seller;
  }

  public Buyer getBuyer() {
    return buyer;
  }

  public Seller getSeller() {
    return seller;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Counterparts)) {
      return false;
    }

    Counterparts that = (Counterparts) o;

    if (buyer != null ? !buyer.equals(that.buyer) : that.buyer != null) {
      return false;
    }
    return seller != null ? seller.equals(that.seller) : that.seller == null;
  }

  @Override
  public int hashCode() {
    int result = buyer != null ? buyer.hashCode() : 0;
    result = 31 * result + (seller != null ? seller.hashCode() : 0);
    return result;
  }

  @Override
  public String toString() {
    return "Counterparts{" +
        "buyer=" + buyer +
        ", seller=" + seller +
        '}';
  }
}
