package pl.coderstrust.database;

import org.junit.Test;

public abstract class AbstractDatabaseTest {

  protected abstract Database getDatabase();

  @Test
  public void saveInvoice() throws Exception {
    // given
    Database db = getDatabase();
  }

  @Test
  public void getInvoices() throws Exception {
  }

}
