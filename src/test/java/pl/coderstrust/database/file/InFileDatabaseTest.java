package pl.coderstrust.database.file;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
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

  InFileDatabase fileDatabase;
  private Invoice givenInvoice;
  private Invoice givenInvoice3;
  private Invoice givenInvoice2;

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

    givenInvoice3 = new Invoice(new Seller(), new Buyer());
    givenInvoice = new Invoice(new MyCompanySell(), new Buyer());
    givenInvoice.addEntry(invoiceEntry1);
    givenInvoice.addEntry(invoiceEntry2);
    givenInvoice.addEntry(invoiceEntry3);

    final InvoiceEntry invoiceEntry4 = new InvoiceEntry("Telefon", 2,
        new Money(new BigDecimal(10).setScale(2, BigDecimal.ROUND_HALF_UP), Currency.PLN), 23);
    final InvoiceEntry invoiceEntry5 = new InvoiceEntry("Bateria", 2,
        new Money(new BigDecimal(10).setScale(2, BigDecimal.ROUND_HALF_UP), Currency.PLN), 23);
    final InvoiceEntry invoiceEntry6 = new InvoiceEntry("Karta SIM", 20,
        new Money(new BigDecimal(1.1).setScale(2, BigDecimal.ROUND_HALF_UP), Currency.PLN), 23);
    givenInvoice2 = new Invoice(new Seller(), new MyCompanyBuy());
    givenInvoice2.addEntry(invoiceEntry4);
    givenInvoice2.addEntry(invoiceEntry5);
    givenInvoice2.addEntry(invoiceEntry6);

    fileDatabase = new InFileDatabase();
    fileDatabase.setFilePath("src/test/resources/testFileOutputIB.txt");
  }

  @Override
  protected Database getDatabase() {
    return fileDatabase;
  }

  @Override
  public void shouldSaveInvoice() {
    //given
    File beforeTest = new File("src/test/resources/testFileOutputIB.txt");
    final Long lengthBeforeTest = beforeTest.length();
    //when
    fileDatabase.saveInvoice(givenInvoice);
    fileDatabase.saveInvoice(givenInvoice2);
    fileDatabase.saveInvoice(givenInvoice3);
    File afetrTest = new File("src/test/resources/testFileOutputIB.txt");
    final Long lengthAfterTest = afetrTest.length();
    //then
    Assert.assertNotNull(fileDatabase);
    Assert.assertNotEquals(lengthBeforeTest, lengthAfterTest);
  }


  @Override
  public void shouldGetInvoices() {
    //given
    List<Invoice> givenList = fileDatabase.getInvoices();
    for (Invoice invoice : givenList) {
      System.out.println(invoice.getDescription() + " " + invoice.getInvoiceId());
    }
    //then
    Assert.assertNotNull(givenList);
    Assert.assertEquals("default description", fileDatabase.getInvoices().get(0).getDescription());
  }

  @Test
  public void shouldDeleteInvoice() {
    //given
    int idToRemove = 0;
    //when
    fileDatabase.saveInvoice(new Invoice());
    boolean result = fileDatabase.removeInvoice(idToRemove);
    //then
    Assert.assertEquals(true, result);
  }

  @Test
  public void shouldReplaceInvoice() {
    //given
    int idToReplace = 0;
    //when
    fileDatabase.saveInvoice(new Invoice());
    boolean result = fileDatabase
        .replaceInvoice(idToReplace, new Invoice(new MyCompanySell(), new Buyer()));
    //then
    Assert.assertEquals(true, result);
  }

}
