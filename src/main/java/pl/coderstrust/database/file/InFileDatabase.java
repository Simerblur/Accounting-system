package pl.coderstrust.database.file;

import pl.coderstrust.database.Database;
import pl.coderstrust.database.filebaseddb.Invoice;

import java.util.List;

public class InFileDatabase implements Database {

  @Override
  public void saveInvoice(Invoice invoice) {

  }

  @Override
  public List<Invoice> getInvoices() {
    return null;
  }
}
