package pl.coderstrust.model;

import java.math.BigDecimal;

public class Money {

  private BigDecimal amount = BigDecimal.ZERO.setScale(2,BigDecimal.ROUND_HALF_UP);
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
}
