package pl.coderstrust.model;

import pl.coderstrust.database.Database;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InvoiceBook {

  private final Database database;

  public InvoiceBook(Database database) {
    this.database = database;
  }

  public List<Invoice> getInvoices() {
    return database.getInvoices();
  }

  /**
   * Adds new invoice to the invoice book.
   */

  public void addInvoice(Invoice invoice) {
    generateInvoiceId(invoice);
    generateInvoiceName(invoice);
    database.saveInvoice(invoice);
  }

  private void generateInvoiceId(Invoice updatedInvoice) {
    int newId;
    if (getInvoices().size() == 0) {
      newId = 1;
    } else {
      newId = getInvoices().get(getInvoices().size() - 1).getInvoiceId() + 1;
    }
    updatedInvoice.setInvoiceId(newId);
  }

  private void generateInvoiceName(Invoice invoiceToRename) {
    String newName;
    String previousName;
    int current = 1;
    int currentIssueMonth = invoiceToRename.getIssueDate().getMonthValue();
    if (getInvoices().size() > 0) {
      previousName = getInvoices().get(getInvoices().size() - 1).getName();
      int previousIssueMonth = getInvoices().get(getInvoices().size() - 1).getIssueDate()
          .getMonthValue();
      currentIssueMonth = invoiceToRename.getIssueDate().getMonthValue();

      if (currentIssueMonth != previousIssueMonth) {
        newName =
            current + "/" + currentIssueMonth + "/" + invoiceToRename.getIssueDate().getYear();
        invoiceToRename.setName(newName);
      } else {
        Matcher matcher = Pattern.compile("[^0-9]*([0-9]+).*").matcher(previousName);
        if (matcher.matches()) {
          current = current + Integer.valueOf(matcher.group(1));
          newName =
              current + "/" + currentIssueMonth + "/" + invoiceToRename.getIssueDate().getYear();
          invoiceToRename.setName(newName);
        }
      }
    } else {
      newName =
          current + "/" + currentIssueMonth + "/" + invoiceToRename.getIssueDate().getYear();
      invoiceToRename.setName(newName);
    }
  }

  public void removeInvoice(Invoice invoice) {

  }
}

