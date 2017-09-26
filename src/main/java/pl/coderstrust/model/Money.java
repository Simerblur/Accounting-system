package pl.coderstrust.model;

import java.math.BigDecimal;

public class Money {

  private BigDecimal amount = BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP);
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
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (!(obj instanceof Money)) {
      return false;
    }

    Money money = (Money) obj;

    if (amount != null ? !amount.equals(money.amount) : money.amount != null) {
      return false;
    }
    return currency == money.currency;
  }

  @Override
  public int hashCode() {
    int result = amount != null ? amount.hashCode() : 0;
    result = 31 * result + (currency != null ? currency.hashCode() : 0);
    return result;
  }

  @Override
  public String toString() {
    return "Money{"
        + "amount=" + amount
        + ", currency="
        + currency + '}';
  }
}
