package pl.coderstrust.database;

import org.junit.Test;

public abstract class AbstractDatabaseTest {

  protected abstract Database getFileDatabase();

  /**
   * Abstract test for all types of database.
   */

  @Test
  public void shouldSaveInvoice() {

  }

  @Test
  public void shouldGetInvoices() {
  }
}
