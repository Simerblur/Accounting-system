package pl.coderstrust.database;

import org.junit.Test;

public abstract class AbstractDatabaseTest {

  protected abstract Database getDatabase();

  /**
   * Abstract test for all types of database.
   */

  @Test
  public void shouldSaveInvoice() {
    // given
    Database db = getDatabase();
    System.out.println(db.toString());
  }

  @Test
  public void shouldGetInvoices() {
  }
}
