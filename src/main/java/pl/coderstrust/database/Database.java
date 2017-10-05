package pl.coderstrust.database;

import pl.coderstrust.model.Invoice;

import java.util.List;

public interface Database {

  void saveInvoice(Invoice invoice);

  void saveInvoice(String jsonString);

  List<Invoice> getInvoices();

  void removeInvoice(int invoiceId);
}
