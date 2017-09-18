package pl.coderstrust.database.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Invoice {

  private String id;
  private String description;
  private List<InvoiceEntry> entries = new ArrayList<>();
  private Money netTotalAmount = new Money(BigDecimal.ZERO, Currency.PLN);
  private Money grossTotalAmount = new Money(BigDecimal.ZERO, Currency.PLN);
  private LocalDate issueDate;

  public Invoice() {
  } //used by JASON

  /**
   * Test sample Javadoc.
   */

  public Invoice(String id, String description, List<InvoiceEntry> entries) {
    this.issueDate = LocalDate.now();
    this.id = id;
    this.description = description;
    this.entries = entries;
    this.netTotalAmount = calculateNetTotal(entries);
    this.grossTotalAmount = calculateGrossTotal(entries);
  }

  private Money calculateNetTotal(List<InvoiceEntry> entries) {
    Money netTotal = new Money();
    for (InvoiceEntry invoiceEntry : entries) {
      netTotal = new Money((netTotal.getAmount().add(invoiceEntry.getNetValue()
          .getAmount())), invoiceEntry.getNetValue().getCurrency());
    }
    return netTotal;
  }

  private Money calculateGrossTotal(List<InvoiceEntry> entries) {
    Money grossTotal = new Money();
    for (InvoiceEntry invoiceEntry : entries) {
      grossTotal = new Money((grossTotal.getAmount().add(invoiceEntry.getGrossValue()
          .getAmount())), invoiceEntry.getGrossValue().getCurrency());
    }
    return grossTotal;
  }

  public LocalDate getIssueDate() {
    return issueDate;
  }

  public void setIssueDate(int year, int month, int day) {
    this.issueDate = LocalDate.of(year, month, day);
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getDescription() {
    return description;
  }

  public Money getNetTotalAmount() {
    return netTotalAmount;
  }

  public Money getGrossTotalAmount() {
    return grossTotalAmount;
  }

  public List<InvoiceEntry> getEntries() {
    return entries;
  }



}
