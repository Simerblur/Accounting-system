package pl.coderstrust.model;

import java.math.BigDecimal;

public class Money {

  private BigDecimal amount;
  private Currency currency;

  public Money() {
  } // Used by JSON

  public Money(BigDecimal amount, Currency currency) {
    this.amount = amount;
    this.currency = currency;
  }

  public Currency getCurrency() {
    return currency;
  }

  public BigDecimal getAmount() {
    return amount;
  }

  @Override
  public String toString() {
    return amount
        + ", Currency=" + currency;
  }
}