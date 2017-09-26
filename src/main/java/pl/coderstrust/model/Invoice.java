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

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (!(obj instanceof Invoice)) {
      return false;
    }

    Invoice invoice = (Invoice) obj;

    if (invoiceId != invoice.invoiceId) {
      return false;
    }
    if (name != null ? !name.equals(invoice.name) : invoice.name != null) {
      return false;
    }
    if (description != null ? !description.equals(invoice.description)
        : invoice.description != null) {
      return false;
    }
    if (counterparts != null ? !counterparts.equals(invoice.counterparts)
        : invoice.counterparts != null) {
      return false;
    }
    if (entries != null ? !entries.equals(invoice.entries) : invoice.entries != null) {
      return false;
    }
    if (netTotalAmount != null ? !netTotalAmount.equals(invoice.netTotalAmount)
        : invoice.netTotalAmount != null) {
      return false;
    }
    if (grossTotalAmount != null ? !grossTotalAmount.equals(invoice.grossTotalAmount)
        : invoice.grossTotalAmount != null) {
      return false;
    }
    return issueDate != null ? issueDate.equals(invoice.issueDate) : invoice.issueDate == null;
  }

  @Override
  public int hashCode() {
    int result = invoiceId;
    result = 31 * result + (name != null ? name.hashCode() : 0);
    result = 31 * result + (description != null ? description.hashCode() : 0);
    result = 31 * result + (counterparts != null ? counterparts.hashCode() : 0);
    result = 31 * result + (entries != null ? entries.hashCode() : 0);
    result = 31 * result + (netTotalAmount != null ? netTotalAmount.hashCode() : 0);
    result = 31 * result + (grossTotalAmount != null ? grossTotalAmount.hashCode() : 0);
    result = 31 * result + (issueDate != null ? issueDate.hashCode() : 0);
    return result;
  }

  @Override
  public String toString() {
    return "Invoice{" + "invoiceId=" + invoiceId + ", name='" + name + '\''
        + ", description='" + description + '\'' + ", counterparts=" + counterparts
        + ", entries=" + entries + ", netTotalAmount=" + netTotalAmount
        + ", grossTotalAmount=" + grossTotalAmount + ", issueDate=" + issueDate + '}';
  }
}
