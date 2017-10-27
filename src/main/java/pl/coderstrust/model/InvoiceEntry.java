package pl.coderstrust.model;

import java.math.BigDecimal;

public class InvoiceEntry {

  private String entryName;
  private int entryId;
  private int entryQuantity;
  private Money entryNetPrice;
  private Money entryNetValue;
  private int entryVatRate;
  private Money entryVatValue;
  private Money entryGrossValue;

  public InvoiceEntry() {
  } //used by JACKSON

  /**
   * Test sample Javadoc.
   */
  public InvoiceEntry(String entryName, int entryQuantity, Money entryNetPrice, int entryVatRate) {
    this.entryName = entryName;
    this.entryId = 0;
    this.entryQuantity = entryQuantity;
    this.entryNetPrice = entryNetPrice;
    this.entryVatRate = entryVatRate;
    this.entryNetValue = new Money(
        this.entryNetPrice.getAmount().multiply(new BigDecimal(this.entryQuantity))
            .setScale(2, BigDecimal.ROUND_HALF_UP), this.entryNetPrice.getCurrency());
    this.entryVatValue = new Money(entryNetValue.getAmount().multiply(new BigDecimal(entryVatRate)
        .divide(new BigDecimal(100), 2, BigDecimal.ROUND_HALF_UP))
        .setScale(2, BigDecimal.ROUND_HALF_UP), entryNetValue.getCurrency());
    this.entryGrossValue = new Money(entryNetValue.getAmount().add(this.entryVatValue.getAmount())
        .setScale(2, BigDecimal.ROUND_HALF_UP), entryNetValue.getCurrency());
  }

  public String getEntryName() {
    return entryName;
  }

  public int getEntryQuantity() {
    return entryQuantity;
  }

  public Money getEntryNetPrice() {
    return entryNetPrice;
  }

  public Money getEntryNetValue() {
    return entryNetValue;
  }

  public int getEntryVatRate() {
    return entryVatRate;
  }

  public Money getEntryVatValue() {
    return entryVatValue;
  }

  public Money getEntryGrossValue() {
    return entryGrossValue;
  }

  public int getEntryId() {
    return entryId;
  }

  public void setEntryId(int entryId) {
    this.entryId = entryId;
  }
}
