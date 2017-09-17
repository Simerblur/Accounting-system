package pl.coderstrust.database.model;

import pl.coderstrust.database.Database;
import pl.coderstrust.database.file.InFileDatabase;
import pl.coderstrust.database.fileprocessor.InvoiceConverter;
import pl.coderstrust.database.memory.InMemoryDatabase;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

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
        .setScale(2,BigDecimal.ROUND_HALF_UP),this.netPrice.getCurrency());
    this.vatValue = new Money(netValue.getAmount().multiply(new BigDecimal(vatRate)
        .divide(new BigDecimal(100),2,BigDecimal.ROUND_HALF_UP))
        .setScale(2,BigDecimal.ROUND_HALF_UP), netValue.getCurrency());
    this.grossValue = new Money(netValue.getAmount().add(this.vatValue.getAmount())
        .setScale(2,BigDecimal.ROUND_HALF_UP), netValue.getCurrency());
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

  public static void main(String[] args) {
    List<InvoiceEntry> entryList = new ArrayList<>();

    InvoiceEntry invoiceEntry1 = new InvoiceEntry("Opona", 4,
        new Money(new BigDecimal(15.7).setScale(2,BigDecimal.ROUND_HALF_UP), Currency.PLN), 23);
    InvoiceEntry invoiceEntry2 = new InvoiceEntry("Felga", 4,
        new Money(new BigDecimal(20).setScale(2,BigDecimal.ROUND_HALF_UP), Currency.PLN), 23);
    InvoiceEntry invoiceEntry3 = new InvoiceEntry("Sruba", 20,
        new Money(new BigDecimal(5.3).setScale(2,BigDecimal.ROUND_HALF_UP), Currency.PLN), 23);

    InvoiceEntry invoiceEntry4 = new InvoiceEntry("Telefon", 2,
        new Money(new BigDecimal(10).setScale(2,BigDecimal.ROUND_HALF_UP), Currency.PLN), 23);
    InvoiceEntry invoiceEntry5 = new InvoiceEntry("Bateria", 2,
        new Money(new BigDecimal(10).setScale(2,BigDecimal.ROUND_HALF_UP), Currency.PLN), 23);
    InvoiceEntry invoiceEntry6 = new InvoiceEntry("Karta SIM", 20,
        new Money(new BigDecimal(1.1).setScale(2,BigDecimal.ROUND_HALF_UP), Currency.PLN), 23);

    entryList.add(invoiceEntry1);
    entryList.add(invoiceEntry2);
    entryList.add(invoiceEntry3);

    InvoiceConverter converter = new InvoiceConverter();
    Invoice invoice = new Invoice("1", "FirstEntryTest", entryList);
    System.out.println(converter.convertToJsonString(invoice));
    invoice.setIssueDate(2017, 2,11);
    System.out.println(converter.convertToJsonString(invoice));
    System.out.println(invoice.getIssueDate());
    Invoice invoice2 = new Invoice("2", "SecondEntryTest", entryList);

    invoice2.setIssueDate(2017, 3,13);
    System.out.println(converter.convertToJsonString(invoice2));
    System.out.println(invoice2.getIssueDate());

    Database db = new InMemoryDatabase();
    //"src/main/resources/pl.coderstrust/InvoiceBook.txt"
    InvoiceBook ib = new InvoiceBook(db);
    entryList = new ArrayList<>();
    entryList.add(invoiceEntry4);
    entryList.add(invoiceEntry5);
    entryList.add(invoiceEntry6);
    Invoice invoice3 = new Invoice("3", "ThirdEntryTest", entryList);
    invoice3.setIssueDate(2017, 8,28);
    Invoice invoice4 = new Invoice("4", "FourthEntryTest", entryList);
    ib.addInvoice(invoice);
    ib.addInvoice(invoice2);
    ib.addInvoice(invoice3);
    ib.addInvoice(invoice4);

    System.out.println(ib.toString());
 /*   for (int i = 0; i < ib.getInvoices().size(); i++) {
      System.out.println(converter.convertToJsonString(ib.getInvoices().get(i)));
    }*/
    System.out.println("===============");
  }
}
