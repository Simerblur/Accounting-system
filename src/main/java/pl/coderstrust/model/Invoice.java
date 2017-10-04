package pl.coderstrust.model;

import pl.coderstrust.model.counterparts.Buyer;
import pl.coderstrust.model.counterparts.Seller;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class Invoice {

  private Buyer buyer;
  private Seller seller;
  private int invoiceId;
  private String name;
  private String description;
  private List<InvoiceEntry> entries = new ArrayList<>();
  private Money netTotalAmount = new Money(BigDecimal.ZERO, Currency.PLN);
  private Money grossTotalAmount = new Money(BigDecimal.ZERO, Currency.PLN);
  private LocalDateTime issueDate;

  public Invoice() {
  } //used by JACKSON

  /**
   * Test sample Javadoc.
   */

  public Invoice(Seller seller, Buyer buyer) {
    this.issueDate = LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS);
    this.name = "Default Name";
    this.invoiceId = 0;
    this.description = "default description";
    this.buyer = buyer;
    this.seller = seller;
    this.netTotalAmount = calculateNetTotal(entries);
    this.grossTotalAmount = calculateGrossTotal(entries);
  }

  private Money calculateNetTotal(List<InvoiceEntry> entries) {
    Money netTotal = new Money();
    if (entries != null) {
      for (InvoiceEntry invoiceEntry : entries) {
        if (invoiceEntry.getNetPrice() != null) {
          netTotal = new Money((netTotal.getAmount().add(invoiceEntry.getNetValue()
              .getAmount())), invoiceEntry.getNetValue().getCurrency());
        } else {
          return netTotal;
        }
      }
      return netTotal;
    } else {
      return netTotal;
    }
  }

  private Money calculateGrossTotal(List<InvoiceEntry> entries) {
    Money grossTotal = new Money();
    if (entries != null) {
      for (InvoiceEntry invoiceEntry : entries) {
        if (invoiceEntry.getNetPrice() != null) {
          grossTotal = new Money((grossTotal.getAmount().add(invoiceEntry.getGrossValue()
              .getAmount())), invoiceEntry.getGrossValue().getCurrency());
        } else {
          return grossTotal;
        }
      }
      return grossTotal;
    } else {
      return grossTotal;
    }
  }

  public LocalDateTime getIssueDate() {
    return issueDate;
  }

  public void setIssueDate(int year, int month, int day, int hour, int minute, int seconds) {
    this.issueDate = LocalDateTime.of(year, month, day, hour, minute, seconds)
        .truncatedTo(ChronoUnit.SECONDS);
  }

  /**
   * Adds new invoice entry at the end of the list and recalculates Invoice net total amount, and
   * gross total amount.
   */
  public void addEntry(InvoiceEntry invoiceEntry) {
    invoiceEntry.setEntryId(entries.size() + 1);
    this.entries.add(invoiceEntry);
    this.netTotalAmount = calculateNetTotal(entries);
    this.grossTotalAmount = calculateGrossTotal(entries);
  }

  /**
   * Removes an existing invoice entry by entryId and recalculates Invoice net total amount, and
   * gross total amount.
   */
  public void removeEntry(int entryId) {
    this.entries.remove(entryId - 1);
    for (int i = 0; i < entries.size(); i++) {
      entries.get(i).setEntryId(i + 1);
    }
    this.netTotalAmount = calculateNetTotal(entries);
    this.grossTotalAmount = calculateGrossTotal(entries);
  }

  public int getInvoiceId() {
    return invoiceId;

  }

  public void setInvoiceId(int invoiceId) {
    this.invoiceId = invoiceId;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
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
