package pl.coderstrust.database.model;


import java.math.BigDecimal;

public class Money {
  private BigDecimal amount;
  private Currency currency;

  public Money(BigDecimal amount, Currency currency) {
    this.amount = amount;
    this.currency = currency;
  }

  public BigDecimal getAmount() {
    return amount;
  }

  public void setAmount(BigDecimal amount) {
    this.amount = amount;
  }

  @Override
  public String toString() {
       return  amount +
        ", Currency=" + currency;
  }
}
