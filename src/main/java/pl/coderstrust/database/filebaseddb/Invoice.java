package pl.coderstrust.database.filebaseddb;

import java.math.BigDecimal;

public class Invoice {

  private int id;
  private String description;
  private Money amount = new Money(BigDecimal.ZERO, Currency.PLN);

  /**
   * Test sample Javadoc.
   */

  public Invoice(int id, String description, Money amount) {
    this.id = id;
    this.description = description;
    this.amount = amount;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Money getAmount() {
    return amount;
  }

  public void setAmount(Money amount) {
    this.amount = amount;
  }
}
