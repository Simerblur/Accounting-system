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
import pl.coderstrust.model.counterparts.MyCompanyBuy;
import pl.coderstrust.model.counterparts.MyCompanySell;
import pl.coderstrust.model.counterparts.Seller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class InMemoryDatabaseTest extends AbstractDatabaseTest {

  private Database memoryDatabase = new InMemoryDatabase();
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
    invoice1 = new Invoice(new MyCompanySell(), new Buyer());
    invoice1.addEntry(invoiceEntry1);
    invoice1.addEntry(invoiceEntry2);
    invoice1.addEntry(invoiceEntry3);

    final InvoiceEntry invoiceEntry4 = new InvoiceEntry("Telefon", 2,
        new Money(new BigDecimal(10).setScale(2, BigDecimal.ROUND_HALF_UP), Currency.PLN), 23);
    final InvoiceEntry invoiceEntry5 = new InvoiceEntry("Bateria", 2,
        new Money(new BigDecimal(10).setScale(2, BigDecimal.ROUND_HALF_UP), Currency.PLN), 23);
    final InvoiceEntry invoiceEntry6 = new InvoiceEntry("Karta SIM", 20,
        new Money(new BigDecimal(1.1).setScale(2, BigDecimal.ROUND_HALF_UP), Currency.PLN), 23);
    invoice2 = new Invoice(new Seller(), new MyCompanyBuy());
    invoice2.addEntry(invoiceEntry4);
    invoice2.addEntry(invoiceEntry5);
    invoice2.addEntry(invoiceEntry6);
  }

  @Override
  protected Database getDatabase() {
    return memoryDatabase;
  }

  @Override
  public void shouldSaveInvoice() {
    //given
    memoryDatabase = new InMemoryDatabase();
    //when
    memoryDatabase.saveInvoice(invoice1);
    memoryDatabase.saveInvoice(invoice2);
    InvoiceConverter converter = new InvoiceConverter();
    //then
    Assert.assertNotNull(memoryDatabase);
    System.out.println(converter.convertToJsonString(invoice1));
    System.out.println(converter.convertToJsonString(invoice2));
    System.out.println(invoice1.getIssueDate());
    System.out.println(invoice2.getBuyer().getVatId());
    Assert.assertEquals(0, memoryDatabase.getInvoices().get(0).getInvoiceId());
    Assert
        .assertEquals("default description", memoryDatabase.getInvoices().get(1).getDescription());
    Assert.assertEquals("PL9988555333",
        memoryDatabase.getInvoices().get(1).getBuyer().getVatId());
    Assert.assertEquals("PL9988555333", memoryDatabase.getInvoices().get(0).getSeller().getVatId());
  }

  @Override
  public void shouldGetInvoices() {
    //given
    memoryDatabase = new InMemoryDatabase();
    memoryDatabase.saveInvoice(invoice1);
    memoryDatabase.saveInvoice(invoice2);
    List<Invoice> givenList;
    List<Invoice> expectedList = new ArrayList<>(Arrays.asList(invoice1, invoice2));
    //when
    givenList = getDatabase().getInvoices();
    //then
    Assert.assertNotNull(givenList);
    Assert.assertEquals(expectedList.size(), givenList.size());
  }
}
