package pl.coderstrust.model;

import java.math.BigDecimal;

public class InvoiceEntry {

  private String name;
  private int quantity;
  private Money netPrice;
  private Money netValue;
  private int vatRate;
  private Money vatValue;
  private Money grossValue;

  public InvoiceEntry() {
  } //used by JASON

  /**
   * Test sample Javadoc.
   */

  public InvoiceEntry(String name, int quantity, Money netPrice, int vatRate) {
    this.name = name;
    this.quantity = quantity;
    this.netPrice = netPrice;
    this.vatRate = vatRate;
    this.netValue = new Money(this.netPrice.getAmount().multiply(new BigDecimal(this.quantity))
        .setScale(2, BigDecimal.ROUND_HALF_UP), this.netPrice.getCurrency());
    this.vatValue = new Money(netValue.getAmount().multiply(new BigDecimal(vatRate)
        .divide(new BigDecimal(100), 2, BigDecimal.ROUND_HALF_UP))
        .setScale(2, BigDecimal.ROUND_HALF_UP), netValue.getCurrency());
    this.grossValue = new Money(netValue.getAmount().add(this.vatValue.getAmount())
        .setScale(2, BigDecimal.ROUND_HALF_UP), netValue.getCurrency());
  }

  public String getName() {
    return name;
  }

  public int getQuantity() {
    return quantity;
  }

  public Money getNetPrice() {
    return netPrice;
  }

  public Money getNetValue() {
    return netValue;
  }

  public int getVatRate() {
    return vatRate;
  }

  public Money getVatValue() {
    return vatValue;
  }

  public Money getGrossValue() {
    return grossValue;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof InvoiceEntry)) {
      return false;
    }

    InvoiceEntry that = (InvoiceEntry) o;

    if (quantity != that.quantity) {
      return false;
    }
    if (vatRate != that.vatRate) {
      return false;
    }
    if (name != null ? !name.equals(that.name) : that.name != null) {
      return false;
    }
    if (netPrice != null ? !netPrice.equals(that.netPrice) : that.netPrice != null) {
      return false;
    }
    if (netValue != null ? !netValue.equals(that.netValue) : that.netValue != null) {
      return false;
    }
    if (vatValue != null ? !vatValue.equals(that.vatValue) : that.vatValue != null) {
      return false;
    }
    return grossValue != null ? grossValue.equals(that.grossValue) : that.grossValue == null;
  }

  @Override
  public int hashCode() {
    int result = name != null ? name.hashCode() : 0;
    result = 31 * result + quantity;
    result = 31 * result + (netPrice != null ? netPrice.hashCode() : 0);
    result = 31 * result + (netValue != null ? netValue.hashCode() : 0);
    result = 31 * result + vatRate;
    result = 31 * result + (vatValue != null ? vatValue.hashCode() : 0);
    result = 31 * result + (grossValue != null ? grossValue.hashCode() : 0);
    return result;
  }

  @Override
  public String toString() {
    return "InvoiceEntry{" +
        "name='" + name + '\'' +
        ", quantity=" + quantity +
        ", netPrice=" + netPrice +
        ", netValue=" + netValue +
        ", vatRate=" + vatRate +
        ", vatValue=" + vatValue +
        ", grossValue=" + grossValue +
        '}';
  }
}
