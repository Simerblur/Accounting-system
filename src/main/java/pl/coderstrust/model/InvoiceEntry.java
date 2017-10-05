package pl.coderstrust.model;

import java.math.BigDecimal;

public class InvoiceEntry {

  private String name;
  private int entryId;
  private int quantity;
  private Money netPrice;
  private Money netValue;
  private int vatRate;
  private Money vatValue;
  private Money grossValue;

  public InvoiceEntry() {
  } //used by JACKSON

  /**
   * Test sample Javadoc.
   */

  public InvoiceEntry(String name, int quantity, Money netPrice, int vatRate) {
    this.name = name;
    this.entryId = 0;
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

  public int getEntryId() {
    return entryId;
  }

  public void setEntryId(int entryId) {
    this.entryId = entryId;
  }
}
