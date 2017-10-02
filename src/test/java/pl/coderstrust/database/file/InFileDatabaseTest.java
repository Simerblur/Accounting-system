package pl.coderstrust.database.file;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import pl.coderstrust.database.Database;
import pl.coderstrust.model.Currency;
import pl.coderstrust.model.Invoice;
import pl.coderstrust.model.InvoiceEntry;
import pl.coderstrust.model.Money;
import pl.coderstrust.model.counterparts.Buyer;
import pl.coderstrust.model.counterparts.Counterparts;
import pl.coderstrust.model.counterparts.Seller;

import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class InFileDatabaseTest {

  @Autowired
  private Database database;
  private Invoice givenInvoice;
  private Invoice givenInvoice2;
  private List<InvoiceEntry> entries = new ArrayList<>();


  /**
   * Test sample Javadoc.
   */

  @Before
  public void fillDb() {
    final InvoiceEntry invoiceEntry1 = new InvoiceEntry("Opona", 4,
        new Money(new BigDecimal(15.7).setScale(2, BigDecimal.ROUND_HALF_UP), Currency.PLN), 23);
    final InvoiceEntry invoiceEntry2 = new InvoiceEntry("Felga", 4,
        new Money(new BigDecimal(20).setScale(2, BigDecimal.ROUND_HALF_UP), Currency.PLN), 23);
    final InvoiceEntry invoiceEntry3 = new InvoiceEntry("Sruba", 20,
        new Money(new BigDecimal(5.3).setScale(2, BigDecimal.ROUND_HALF_UP), Currency.PLN), 23);
    entries.add(invoiceEntry1);
    entries.add(invoiceEntry2);
    entries.add(invoiceEntry3);
    givenInvoice = new Invoice(new Counterparts(new Buyer("Kupiec Jas", "PL12345678"),
        new Seller("Sprzedawca Jacek", "PL999888777")), "First Inv", entries);
    givenInvoice2 = new Invoice(new Counterparts(new Buyer("Kupiec Piotr", "PL222333444"),
        new Seller("Sprzedawca Tomek", "PL333555888")), "Second Inv", entries);
  }


  protected Database getDatabase() {
    return database;
  }

  /**
   * Test sample Javadoc.
   */

  @Test
  public void shouldSaveInvoice() {
    //given
    File beforeTest = new File("src/test/resources/pl.coderstrust/testFileOutput.txt");
    //when
    database.saveInvoice(givenInvoice);
    database.saveInvoice(givenInvoice2);
    File afetrTest = new File("src/test/resources/pl.coderstrust/testFileOutput.txt");
    //then
    Assert.assertNotNull(database);
    Assert.assertEquals(beforeTest, afetrTest);
  }


  /**
   * Test sample Javadoc.
   */

  @Test
  public void shouldGetInvoices() {
    //given
    List<Invoice> givenList = database.getInvoices();
    //then
    Assert.assertNotNull(givenList);
    Assert.assertEquals("First Inv", database.getInvoices().get(0).getDescription());
    Assert.assertEquals("2017-08-25T11:09:36",
        database.getInvoices().get(0).getIssueDate().toString());
  }
}
