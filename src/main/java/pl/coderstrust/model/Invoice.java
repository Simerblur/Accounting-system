package pl.coderstrust.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import pl.coderstrust.fileprocessor.JsonDateDeserializer;
import pl.coderstrust.fileprocessor.JsonDateSerializer;
import pl.coderstrust.model.counterparts.Counterparts;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class Invoice {

  private int invoiceId;
  private String name;
  private String description;
  private Counterparts counterparts;
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

  public Invoice(Counterparts counterparts, String description, List<InvoiceEntry> entries) {
    this.issueDate = LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS);
    this.name = "Default Name";
    this.invoiceId = 0;
    this.description = description;
    this.counterparts = counterparts;
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

  public Counterparts getCounterparts() {
    return counterparts;
  }
}
