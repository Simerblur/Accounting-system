package pl.coderstrust.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import pl.coderstrust.fileprocessor.JsonDateDeserializer;
import pl.coderstrust.fileprocessor.JsonDateSerializer;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class Invoice {

  private String id;
  private String description;
  private List<InvoiceEntry> entries = new ArrayList<>();
  private Money netTotalAmount = new Money(BigDecimal.ZERO, Currency.PLN);
  private Money grossTotalAmount = new Money(BigDecimal.ZERO, Currency.PLN);
  @JsonDeserialize(using = JsonDateDeserializer.class)
  @JsonSerialize(using = JsonDateSerializer.class)
  private LocalDateTime issueDate;

  public Invoice() {
  } //used by JASON

  /**
   * Test sample Javadoc.
   */

  public Invoice(String id, String description, List<InvoiceEntry> entries) {
    this.issueDate = LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS);
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

  public LocalDateTime getIssueDate() {
    return issueDate;
  }

  public void setIssueDate(int year, int month, int day, int hour, int minute, int seconds) {
    this.issueDate = LocalDateTime.of(year, month, day, hour, minute, seconds)
        .truncatedTo(ChronoUnit.SECONDS);
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
