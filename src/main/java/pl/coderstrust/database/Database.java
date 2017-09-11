package pl.coderstrust.database;

import pl.coderstrust.database.fileBasedDatabase.Invoice;

import java.util.List;

public interface Database {

  void saveInvoice(Invoice invoice);

  List<Invoice> getInvoices();

}
