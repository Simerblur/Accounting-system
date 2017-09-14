package pl.coderstrust.database.model;

import pl.coderstrust.database.Database;

import java.util.List;

public class InvoiceBook {

  private final Database database;

  public InvoiceBook(Database database) {
    this.database = database;
  }

  public List<Invoice> getInvoices() {
    return database.getInvoices();
  }

  public void addInvoice(Invoice invoice) {
    database.saveInvoice(invoice);
  }

 /* public void printInvoiceBook() {
    for (int i = 0; i < this.getInvoices().size(); i++) {
      System.out.println("Id = " + this.getInvoices().get(i).getId()
          + ", Description = " + this.getInvoices().get(i).getDescription()
          + ", Amount = " + this.getInvoices().get(i).getAmount().toString());
    }
  }*/
}
