package pl.coderstrust.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import pl.coderstrust.database.Database;
import pl.coderstrust.database.file.InFileDatabase;
import pl.coderstrust.database.memory.InMemoryDatabase;
import pl.coderstrust.fileprocessor.InvoiceConverter;
import pl.coderstrust.model.counterparts.Buyer;
import pl.coderstrust.model.counterparts.Counterparts;
import pl.coderstrust.model.counterparts.Seller;

import java.io.File;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


@RunWith(MockitoJUnitRunner.class)
public class InvoiceBookTest {

  private Invoice givenInvoice;
  private Invoice givenInvoice2;
  private Invoice givenInvoice3;
  private LocalDateTime now;

  @Mock
  private Database db;

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
    givenInvoice = new Invoice(
        new Counterparts(new Buyer("Kasia", "PL12345678"), new Seller("Zosia", "PL9999999")),
        "First Inv");
    givenInvoice.addEntry(invoiceEntry1);
    givenInvoice.addEntry(invoiceEntry2);
    givenInvoice.addEntry(invoiceEntry3);
    givenInvoice2 = new Invoice(
        new Counterparts(new Buyer("Gosia", "PL222333444"), new Seller("Jacek", "PL33333333")),
        "Second Inv");
    givenInvoice2.addEntry(invoiceEntry1);
    givenInvoice2.addEntry(invoiceEntry2);
    givenInvoice2.addEntry(invoiceEntry3);
    givenInvoice3 = new Invoice(
        new Counterparts(new Buyer("Ania", "PL1555677777"), new Seller("Wacek", "PL8888811111")),
        "Third Inv");
    givenInvoice3.addEntry(invoiceEntry1);
    givenInvoice3.addEntry(invoiceEntry2);
    givenInvoice3.addEntry(invoiceEntry3);

  }

  /**
   * Test sample Javadoc.
   */

  @Test
  public void shouldReturnEmptyListOfInvoicesIfNothingAdded() {
    // given
    InvoiceBook invoiceBook = new InvoiceBook(db);

    // when
    List<Invoice> invoices = invoiceBook.getInvoices();

    //then
    assertNotNull("Invoices should not be null", invoices);
    assertEquals(0, invoices.size());
  }

  /**
   * Test sample Javadoc.
   */

  @Test
  public void shouldReturnSingleInvoiceWhenItWasAddedToBook() {
    // given
    InvoiceBook invoiceBook = new InvoiceBook(db);
    when(db.getInvoices()).thenReturn(Collections.singletonList(givenInvoice));

    // when
    invoiceBook.addInvoice(givenInvoice);
    List<Invoice> invoices = invoiceBook.getInvoices();

    //then
    assertNotNull("Invoices should not be null", invoices);
    assertEquals(1, invoices.size());
    assertEquals(givenInvoice, invoices.get(0));
  }

  /**
   * Test sample Javadoc.
   */

  @Test
  public void shouldTestEmptyInvoiceConstructor() {
    //given
    Invoice testJson = new Invoice();
    //then
    Assert.assertNotNull(testJson);
  }

  /**
   * Test sample Javadoc.
   */

  @Test
  public void shouldTestEmptyInvoiceEntryConstructor() {
    //given
    InvoiceEntry testJson = new InvoiceEntry();
    //then
    Assert.assertNotNull(testJson);
  }

  /**
   * Test getters and setters. Tests DI and Invoice name generation.
   */

  @Test
  public void shouldTestGettersAndSetters() {
    Database memDb = new InMemoryDatabase();
    InvoiceBook ib = new InvoiceBook(memDb);
    InvoiceConverter converter = new InvoiceConverter();
    Invoice testInvoice = converter.convertJsonToInvoice(
        "{\"invoiceId\":1,\"name\":\"1/8/2017\",\"description\":\"First Inv\","
            + "\"counterparts\":null,\"entries\":[{\"name\":\"Opona\",\"entryId\":1,\"quantity"
            + "\":4,\"netPrice\":{\"amount\":15.70,\"currency\":\"PLN\"},\"netValue\":{\"amount"
            + "\":62.80,\"currency\":\"PLN\"},\"vatRate\":23,\"vatValue\":{\"amount\":14.44,"
            + "\"currency\":\"PLN\"},\"grossValue\":{\"amount\":77.24,\"currency\":\"PLN\"}},{"
            + "\"name\":\"Felga\",\"entryId\":2,\"quantity\":4,\"netPrice\":{\"amount\":20.00,"
            + "\"currency\":\"PLN\"},\"netValue\":{\"amount\":80.00,\"currency\":\"PLN\"},\""
            + "vatRate\":23,\"vatValue\":{\"amount\":18.40,\"currency\":\"PLN\"},\"grossValue"
            + "\":{\"amount\":98.40,\"currency\":\"PLN\"}},{\"name\":\"Sruba\",\"entryId\":3,"
            + "\"quantity\":20,\"netPrice\":{\"amount\":5.30,\"currency\":\"PLN\"},\"netValue\":{"
            + "\"amount\":106.00,\"currency\":\"PLN\"},\"vatRate\":23,\"vatValue\":{\"amount"
            + "\":24.38,\"currency\":\"PLN\"},\"grossValue\":{\"amount\":130.38,\"currency\":\""
            + "PLN\"}}],\"netTotalAmount\":{\"amount\":248.80,\"currency\":\"PLN\"},"
            + "\"grossTotalAmount\":{\"amount\":306.02,\"currency\":\"PLN\"},\"issueDate\":"
            + "\"2017-08-22 23:59:01\"}");
    ib.addInvoice(testInvoice);
    System.out.println(converter.convertToJsonString(ib.getInvoices().get(0)));
    ib.addInvoice(testInvoice);
    System.out.println(converter.convertToJsonString(ib.getInvoices().get(0)));
    System.out.println(converter.convertToJsonString(ib.getInvoices().get(1)));
    System.out.println(ib.getInvoices().size());
    ib.addInvoice(testInvoice);
    System.out.println(ib.getInvoices().size());
    System.out.println(converter.convertToJsonString(ib.getInvoices().get(2)));
    System.out.println(converter.convertToJsonString(ib.getInvoices().get(0)));
    ib.addInvoice(new Invoice(new Counterparts(), "TestOnNewInvoice"));

    System.out.println(ib.getInvoices().size());
    ib.addInvoice(new Invoice(new Counterparts(), "TestOnNewInvoice"));

    System.out.println(ib.getInvoices().size());
    ib.addInvoice(new Invoice(new Counterparts(), "TestOnNewInvoice"));

    System.out.println(ib.getInvoices().size());
    ib.addInvoice(new Invoice(new Counterparts(), "TestOnNewInvoice"));

    System.out.println(ib.getInvoices().size());
    ib.addInvoice(new Invoice(new Counterparts(), "TestOnNewInvoice"));
    System.out.println(ib.getInvoices().size());
    ib.addInvoice(new Invoice(new Counterparts(), "TestOnNewSecondInvoice"));
    System.out.println(ib.getInvoices().size());
    System.out.println(ib.getInvoices().get(5).getEntries());

    System.out.println("tessss" + ib.getInvoices().get(0).getInvoiceId());
    for (int i = 0; i < ib.getInvoices().size(); i++) {
      System.out.println(
          ib.getInvoices().get(i).getInvoiceId() + " " + ib.getInvoices().get(i).getName());
    }
  }

  /**
   * Test sample Javadoc.
   */
  @Test
  public void shouldIfReturnInvoicesFromRange() {

    Database database = new InFileDatabase("src/main/resources/pl.coderstrust/InvoiceBook.txt");
    InvoiceBook invoiceBook = new InvoiceBook(database);
    List<Invoice> test = invoiceBook
        .getInvoicesByDateRange(LocalDateTime.of(2017, 5, 25, 11, 9, 36),
            LocalDateTime.of(2017, 8, 25, 11, 9, 37));
    System.out.println(test.get(0).getInvoiceId());
    for (Invoice iterator : test) {
      System.out.println(iterator.getInvoiceId() + " " + iterator.getIssueDate());
    }
  }

  /**
   * Should add new invoice.
   */
  @Test
  public void shouldAddInvoice() {
    //given
    now = LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS);
    db = new InMemoryDatabase();
    InvoiceBook invoiceBook = new InvoiceBook(db);
    //when
    invoiceBook.addInvoice(givenInvoice);
    invoiceBook.addInvoice(givenInvoice2);
    invoiceBook.addInvoice(givenInvoice3);
    List<Invoice> testedRange = invoiceBook
        .getInvoicesByDateRange(LocalDateTime.of(now.getYear(), now.getMonth(), 1, 0, 0, 0),
            LocalDateTime
                .of(now.getYear(), now.getMonth(), now.getMonth().maxLength(), 23, 59, 59));
    givenInvoice2.addEntry(new InvoiceEntry("estowy wpis", 12,
        new Money(new BigDecimal(25.5).setScale(2, BigDecimal.ROUND_HALF_UP), Currency.PLN), 23));
    for (InvoiceEntry invoiceEntry : givenInvoice2.getEntries()) {
      System.out.println("entry = " + invoiceEntry.getEntryId());
    }
    //then
    Assert.assertEquals("1/10/2017", testedRange.get(0).getName());
    Assert.assertEquals("2/10/2017", testedRange.get(1).getName());
    Assert.assertEquals("3/10/2017", testedRange.get(2).getName());
    Assert.assertEquals(4, testedRange.get(1).getEntries().get(3).getEntryId());
    givenInvoice2.removeEntry(2);
    Assert.assertEquals(3, testedRange.get(1).getEntries().get(2).getEntryId());
    System.out.println("after entry modification size = " + testedRange.get(1).getEntries().size());
    System.out.println("and last id = " + testedRange.get(1).getEntries().get(2).getEntryId());
  }
}
