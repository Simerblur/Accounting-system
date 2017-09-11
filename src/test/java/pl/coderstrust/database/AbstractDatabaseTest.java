package pl.coderstrust.database;

import org.junit.Test;

public abstract class AbstractDatabaseTest {

  protected abstract Database getDatabase();
  /**
   * Test sample Javadoc.
   */

  @Test
  public void saveInvoice() throws Exception {
    // given
    Database db = getDatabase();
    System.out.println(db.toString());
  }

  @Test
  public void getInvoices() throws Exception {
  }

}
