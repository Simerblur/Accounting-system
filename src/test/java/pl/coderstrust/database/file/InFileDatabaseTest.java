package pl.coderstrust.database.file;

import org.junit.Assert;
import org.junit.Before;
import pl.coderstrust.database.AbstractDatabaseTest;
import pl.coderstrust.database.Database;
import pl.coderstrust.model.Currency;
import pl.coderstrust.model.Invoice;
import pl.coderstrust.model.InvoiceEntry;
import pl.coderstrust.model.Money;
import pl.coderstrust.model.counterparts.Buyer;
import pl.coderstrust.model.counterparts.MyCompanyBuy;
import pl.coderstrust.model.counterparts.MyCompanySell;
import pl.coderstrust.model.counterparts.Seller;

import java.io.File;
import java.math.BigDecimal;
import java.util.List;

public class InFileDatabaseTest extends AbstractDatabaseTest {

  private Database fileDatabase;
  private Invoice invoice1;
  private Invoice invoice2;

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
  protected Database getFileDatabase() {
    return fileDatabase;
  }

  /**
   * Test sample Javadoc.
   */

  @Override
  public void shouldSaveInvoice() {
    //given
    File beforeTest = new File("src/test/resources/pl.coderstrust/testFileOutput.txt");
    fileDatabase = new InFileDatabase("src/test/resources/pl.coderstrust/testFileOutput.txt");
    Long lengthBeforeTest = beforeTest.length();
    //when
    fileDatabase.saveInvoice(invoice1);
    File afetrTest = new File("src/test/resources/pl.coderstrust/testFileOutput.txt");
    Long lengthAfterTest = afetrTest.length();
    //then
    Assert.assertNotNull(fileDatabase);
    Assert.assertNotEquals(lengthBeforeTest, lengthAfterTest);
  }

  /**
   * Test sample Javadoc.
   */

  @Override
  public void shouldGetInvoices() {
    //given
    fileDatabase = new InFileDatabase("src/test/resources/pl.coderstrust/InvoiceBook.txt");
    List<Invoice> givenList = fileDatabase.getInvoices();
    //then
    Assert.assertNotNull(givenList);
    Assert.assertEquals("First Inv", fileDatabase.getInvoices().get(0).getDescription());
    Assert.assertEquals("2017-08-22T23:59:01",
        fileDatabase.getInvoices().get(0).getIssueDate().toString());
  }
}
