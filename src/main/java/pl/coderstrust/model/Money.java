package pl.coderstrust.model;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class Money {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;
  private BigDecimal amount = BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP);

  @Enumerated(EnumType.STRING)
  private Currency currency;

  public Money() {
  } // Used by JACKSON

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
  public String toString() {
    return "Money{"
        + "amount=" + amount
        + ", currency=" + currency
        + '}';
  }
}
