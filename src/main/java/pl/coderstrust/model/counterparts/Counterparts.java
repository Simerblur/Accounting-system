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
}
