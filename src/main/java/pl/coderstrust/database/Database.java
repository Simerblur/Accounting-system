package pl.coderstrust.database;

import pl.coderstrust.model.Invoice;

import java.util.List;

public interface Database {

  void saveInvoice(Invoice invoice);

  List<Invoice> getInvoices();
}
