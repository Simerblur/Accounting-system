package pl.coderstrust.logic;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import pl.coderstrust.database.Database;
import pl.coderstrust.database.memory.InMemoryDatabase;
import pl.coderstrust.model.Currency;
import pl.coderstrust.model.Invoice;
import pl.coderstrust.model.InvoiceEntry;
import pl.coderstrust.model.Money;
import pl.coderstrust.model.counterparts.Buyer;
import pl.coderstrust.model.counterparts.MyCompanyBuy;
import pl.coderstrust.model.counterparts.MyCompanySell;
import pl.coderstrust.model.counterparts.Seller;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class TaxHelperTest {

  private Invoice givenInvoice;
  private Invoice givenInvoice2;
  private Invoice givenInvoice3;

  private Database db;
  int currentMonth = LocalDateTime.now().getMonthValue();

  @Before
  public void fillDb() {
    final InvoiceEntry invoiceEntry1 = new InvoiceEntry("Opona", 4,
        new Money(new BigDecimal(15.7).setScale(2, BigDecimal.ROUND_HALF_UP), Currency.PLN), 23);
    final InvoiceEntry invoiceEntry2 = new InvoiceEntry("Felga", 4,
        new Money(new BigDecimal(20).setScale(2, BigDecimal.ROUND_HALF_UP), Currency.PLN), 23);
    final InvoiceEntry invoiceEntry3 = new InvoiceEntry("Sruba", 20,
        new Money(new BigDecimal(5.3).setScale(2, BigDecimal.ROUND_HALF_UP), Currency.PLN), 23);
    givenInvoice = new Invoice(new Seller("Kasia", "PL155567777"), new MyCompanyBuy());
    givenInvoice.addEntry(invoiceEntry1);
    givenInvoice.addEntry(invoiceEntry2);
    givenInvoice.addEntry(invoiceEntry3);
    //   givenInvoice.setCounterparts()
    givenInvoice2 = new Invoice(new MyCompanySell(),
        new Buyer("Jacek", "PL33333333"));
    givenInvoice2.addEntry(invoiceEntry1);
    givenInvoice2.addEntry(invoiceEntry2);
    givenInvoice2.addEntry(invoiceEntry3);
    givenInvoice3 = new Invoice(new Seller("Ania", "PL155567777"),
        new Buyer("Wacek", "PL888811111"));
    givenInvoice3.addEntry(invoiceEntry1);
    givenInvoice3.addEntry(invoiceEntry2);
    givenInvoice3.addEntry(invoiceEntry3);
  }

  @Test
  public void shouldCalculateVat() {
    //given
    db = new InMemoryDatabase();
    final InvoiceBook invoiceBook = new InvoiceBook(db);
    givenInvoice2.addEntry(new InvoiceEntry("estowy wpis", 12,
        new Money(new BigDecimal(25.5).setScale(2, BigDecimal.ROUND_HALF_UP), Currency.PLN), 23));
    System.out.println(
        " Net total 1 " + givenInvoice2.getNetTotalAmount().getAmount() + " Gross total "
            + givenInvoice2.getGrossTotalAmount().getAmount());
    System.out.println(
        " Net total 2 " + givenInvoice.getNetTotalAmount().getAmount() + " Gross total "
            + givenInvoice.getGrossTotalAmount().getAmount());
    System.out.println(
        " Net total 3 " + givenInvoice3.getNetTotalAmount().getAmount() + " Gross total "
            + givenInvoice3.getGrossTotalAmount().getAmount());
    System.out.println("=============================");
    System.out
        .println("VAT to pay = " + invoiceBook.calculateVatAmountForGivenMonth(10).toString());
    //when
    invoiceBook.addInvoice(givenInvoice2);
    invoiceBook.addInvoice(givenInvoice);
    invoiceBook.addInvoice(givenInvoice3);
    //then
    Assert.assertEquals(
        new Money(new BigDecimal(70.38).setScale(2, BigDecimal.ROUND_HALF_UP), Currency.PLN),
        invoiceBook.calculateVatAmountForGivenMonth(currentMonth));
  }

  @Test
  public void shouldCalculateIncomeTax() {
    //given
    db = new InMemoryDatabase();
    InvoiceBook invoiceBook = new InvoiceBook(db);
    givenInvoice2.addEntry(new InvoiceEntry("estowy wpis", 12,
        new Money(new BigDecimal(25.5).setScale(2, BigDecimal.ROUND_HALF_UP), Currency.PLN), 23));
    //when
    invoiceBook.addInvoice(givenInvoice2);
    invoiceBook.addInvoice(givenInvoice);
    invoiceBook.addInvoice(givenInvoice3);
    for (int i = 0; i < invoiceBook.getInvoices().size(); i++) {
      System.out.println(
          " Net total " + i + " " + invoiceBook
              .getInvoices().get(i).getNetTotalAmount().getAmount()
              + " Gross total "
              + invoiceBook.getInvoices().get(i).getGrossTotalAmount().getAmount());
    }
    System.out.println("=============================");
    System.out.println(
        "Income tax to pay = " + invoiceBook.calculateIncomeTaxForGivenMonth(10).toString());
    //then
    Assert.assertEquals(
        new Money(new BigDecimal(58.14).setScale(2, BigDecimal.ROUND_HALF_UP), Currency.PLN),
        invoiceBook.calculateIncomeTaxForGivenMonth(currentMonth));
  }
}
