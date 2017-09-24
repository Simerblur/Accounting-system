package pl.coderstrust.database.file;

import org.junit.Assert;
import org.junit.Before;
import pl.coderstrust.database.AbstractDatabaseTest;
import pl.coderstrust.database.Database;
import pl.coderstrust.model.Currency;
import pl.coderstrust.model.Invoice;
import pl.coderstrust.model.InvoiceEntry;
import pl.coderstrust.model.Money;

import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class InFileDatabaseTest extends AbstractDatabaseTest {

  private Database db;
  private Invoice givenInvoice;
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
    givenInvoice = new Invoice(1, "First Inv", entries);
  }

  @Override
  protected Database getDatabase() {
    return db;
  }

  /**
   * Test sample Javadoc.
   */

  @Override
  public void shouldSaveInvoice() {
    //given
    File beforeTest = new File("src/test/resources/pl.coderstrust/testFileOutput.txt");
    db = new InFileDatabase("src/test/resources/pl.coderstrust/testFileOutput.txt");
    Long lengthBeforeTest = beforeTest.length();
    //when
    db.saveInvoice(givenInvoice);
    File afetrTest = new File("src/test/resources/pl.coderstrust/testFileOutput.txt");
    Long lengthAfterTest = afetrTest.length();
    //then
    Assert.assertNotNull(db);
    Assert.assertNotEquals(lengthBeforeTest, lengthAfterTest);
  }

  /**
   * Test sample Javadoc.
   */

  @Override
  public void shouldGetInvoices() {
    //given
    db = new InFileDatabase("src/test/resources/pl.coderstrust/InvoiceBook.txt");
    List<Invoice> givenList = db.getInvoices();
    //then
    Assert.assertNotNull(givenList);
    Assert.assertEquals("First Inv", db.getInvoices().get(0).getDescription());
    Assert.assertEquals("2017-08-22T23:59:01", db.getInvoices().get(0).getIssueDate().toString());
  }
}
