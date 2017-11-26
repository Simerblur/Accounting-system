package pl.coderstrust.model;

import io.swagger.annotations.ApiModelProperty;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import pl.coderstrust.model.counterparts.Buyer;
import pl.coderstrust.model.counterparts.Seller;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;


@Entity
@NamedQueries(value = {
    @NamedQuery(name = "Invoices.getAll", query = "SELECT i FROM Invoice i")
})
public class Invoice {

  @Id
  private int invoiceId;

  @NotNull
  private String name;

  @ApiModelProperty(value = "Text annotation on the invoice in the separate field")
  private String description;

  @NotNull
  @ManyToOne(cascade = {CascadeType.ALL})
  private Buyer buyer;

  @NotNull
  @ManyToOne(cascade = {CascadeType.ALL})
  private Seller seller;

  @OneToMany(cascade = {CascadeType.ALL}, orphanRemoval = true)
  private List<InvoiceEntry> entries = new ArrayList<>();


  @ManyToOne(cascade = {CascadeType.ALL})
  private Money netTotalAmount;

  @ManyToOne(cascade = {CascadeType.ALL})
  private Money grossTotalAmount;

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
    this.netTotalAmount = getNetTotalAmount();
    this.grossTotalAmount = getGrossTotalAmount();
  }

  private Money calculateTotal(List<InvoiceEntry> entries, Function<InvoiceEntry, Money> method) {
    Money total = new Money();
    if (entries != null) {
      for (InvoiceEntry invoiceEntry : entries) {
        if (invoiceEntry.getEntryNetPrice() != null) {
          total = new Money(
              (total.getAmount().add(method.apply(invoiceEntry).getAmount())),
              method.apply(invoiceEntry).getCurrency());
        } else {
          return total;
        }
      }
      return total;
    } else {
      return total;
    }
  }

  public Money getNetTotalAmount() {
    return calculateTotal(entries, InvoiceEntry::getEntryNetValue);
  }

  public Money getGrossTotalAmount() {
    return calculateTotal(entries, InvoiceEntry::getEntryGrossValue);
  }

  public LocalDateTime getIssueDate() {
    return issueDate;
  }

  public void setIssueDate(int year, int month, int day, int hour, int minute, int seconds) {
    this.issueDate = LocalDateTime.of(year, month, day, hour, minute, seconds)
        .truncatedTo(ChronoUnit.SECONDS);
  }

  /**
   * Adds new invoice entry at the end of the list.
   */
  public void addEntry(InvoiceEntry invoiceEntry) {
    invoiceEntry.setEntryId(entries.size() + 1);
    this.entries.add(invoiceEntry);
  }

  /**
   * Removes an existing invoice entry by entryId.
   */
  public void removeEntry(int entryId) {
    this.entries.remove(entryId - 1);
    for (int i = 0; i < entries.size(); i++) {
      entries.get(i).setEntryId(i + 1);
    }
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

  public List<InvoiceEntry> getEntries() {
    return entries;
  }

  public Buyer getBuyer() {
    return buyer;
  }

  public Seller getSeller() {
    return seller;
  }
}
