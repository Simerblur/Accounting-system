package pl.coderstrust.database.file;

import pl.coderstrust.database.Database;
import pl.coderstrust.database.model.Invoice;

import java.util.List;

public class InFileDatabase implements Database {
  private String filePath;

  public InFileDatabase(String filePath) {
    this.filePath = filePath;
  }

  public String getFilePath() {
    return filePath;
  }

  @Override
  public void saveInvoice(Invoice invoice) {

  }

  @Override
  public List<Invoice> getInvoices() {
    return null;
  }
}
