package pl.coderstrust.database.memory;

import org.junit.Assert;
import org.junit.Before;
import pl.coderstrust.database.AbstractDatabaseTest;
import pl.coderstrust.database.Database;
import pl.coderstrust.fileprocessor.InvoiceConverter;
import pl.coderstrust.model.Currency;
import pl.coderstrust.model.Invoice;
import pl.coderstrust.model.InvoiceEntry;
import pl.coderstrust.model.Money;
import pl.coderstrust.model.counterparts.Buyer;
import pl.coderstrust.model.counterparts.Counterparts;
import pl.coderstrust.model.counterparts.Seller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class InMemoryDatabaseTest extends AbstractDatabaseTest {

  private Database memDb = new InMemoryDatabase();
  private List<InvoiceEntry> entryList = new ArrayList<>();
  private Invoice invoice1;
  private Invoice invoice2;

  /**
   * Fills up the database with the entries and invoices respectfully.
   */

  @Before
  public void fillDb() {
    final InvoiceEntry invoiceEntry1 = new InvoiceEntry("Opona", 4,
        new Money(new BigDecimal(15.7).setScale(2, BigDecimal.ROUND_HALF_UP), Currency.PLN), 23);
    final InvoiceEntry invoiceEntry2 = new InvoiceEntry("Felga", 4,
        new Money(new BigDecimal(20).setScale(2, BigDecimal.ROUND_HALF_UP), Currency.PLN), 23);
    final InvoiceEntry invoiceEntry3 = new InvoiceEntry("Sruba", 20,
        new Money(new BigDecimal(5.3).setScale(2, BigDecimal.ROUND_HALF_UP), Currency.PLN), 23);
    entryList.add(invoiceEntry1);
    entryList.add(invoiceEntry2);
    entryList.add(invoiceEntry3);
    invoice1 = new Invoice(new Counterparts(), "First Inv", entryList);
    final InvoiceEntry invoiceEntry4 = new InvoiceEntry("Telefon", 2,
        new Money(new BigDecimal(10).setScale(2, BigDecimal.ROUND_HALF_UP), Currency.PLN), 23);
    final InvoiceEntry invoiceEntry5 = new InvoiceEntry("Bateria", 2,
        new Money(new BigDecimal(10).setScale(2, BigDecimal.ROUND_HALF_UP), Currency.PLN), 23);
    final InvoiceEntry invoiceEntry6 = new InvoiceEntry("Karta SIM", 20,
        new Money(new BigDecimal(1.1).setScale(2, BigDecimal.ROUND_HALF_UP), Currency.PLN), 23);
    entryList = new ArrayList<>();
    entryList.add(invoiceEntry4);
    entryList.add(invoiceEntry5);
    entryList.add(invoiceEntry6);
    invoice2 = new Invoice(new Counterparts(new Buyer("Super Kupiec", "PL12345678"), new Seller("Super Sprzedawca", "PL999888777")), "Second Inv", entryList);
  }

  @Override
  protected Database getDatabase() {
    return memDb;
  }

  @Override
  public void shouldSaveInvoice() {
    //given
    memDb = new InMemoryDatabase();
    //when
    memDb.saveInvoice(invoice1);
    memDb.saveInvoice(invoice2);
    InvoiceConverter converter = new InvoiceConverter();
    //then
    Assert.assertNotNull(memDb);
    System.out.println(converter.convertToJsonString(invoice1));
    System.out.println(converter.convertToJsonString(invoice2));
    System.out.println(invoice1.getIssueDate());
    Assert.assertEquals(0, memDb.getInvoices().get(0).getInvoiceId());
    Assert.assertEquals("Second Inv", memDb.getInvoices().get(1).getDescription());
    Assert.assertEquals("PL12345678", memDb.getInvoices().get(1).getCounterparts().getBuyer().getVatId());
  }

  @Override
  public void shouldGetInvoices() {
    //given
    memDb = new InMemoryDatabase();
    memDb.saveInvoice(invoice1);
    memDb.saveInvoice(invoice2);
    List<Invoice> givenList;
    List<Invoice> expectedList = new ArrayList<>(Arrays.asList(invoice1, invoice2));
    //when
    givenList = getDatabase().getInvoices();
    //then
    Assert.assertNotNull(givenList);
    Assert.assertEquals(expectedList.size(), givenList.size());
  }
}
