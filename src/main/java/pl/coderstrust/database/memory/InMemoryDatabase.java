package pl.coderstrust.database.memory;

import pl.coderstrust.database.Database;
import pl.coderstrust.model.Invoice;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class InMemoryDatabase implements Database {

  private final List<Invoice> invoices = new ArrayList<>();

  @Override
  public void saveInvoice(Invoice invoice) {
    invoices.add(invoice);
  }

  @Override
  public List<Invoice> getInvoices() {
    return Collections.unmodifiableList(invoices);
  }

  @Override
  public void removeInvoice(int invoiceId) {
    int index = -1;
    try {
      for (Invoice invoice : invoices) {
        if (invoice.getInvoiceId() == invoiceId) {
          index = invoices.indexOf(invoice);
        }
      }
      invoices.remove(index);
    } catch (ArrayIndexOutOfBoundsException e) {
      System.out.println("Invoice not found!");
    }
  }
}
