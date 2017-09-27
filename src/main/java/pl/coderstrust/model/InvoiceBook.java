package pl.coderstrust.model;

import pl.coderstrust.database.Database;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
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
    List<Invoice> listOfAllInvoices = getInvoices();
    if (listOfAllInvoices.size() == 0) {
      newId = 1;
    } else {
      newId = listOfAllInvoices.get(listOfAllInvoices.size() - 1).getInvoiceId() + 1;
    }
    updatedInvoice.setInvoiceId(newId);
  }

  private void generateInvoiceName(Invoice invoiceToRename) {
    String newName;
    String previousName;
    int current = 1;
    int currentIssueMonth = invoiceToRename.getIssueDate().getMonthValue();
    int currentIssueYear = invoiceToRename.getIssueDate().getYear();
    if (getInvoices().size() > 0) {
      int lastDayOfMonth = invoiceToRename.getIssueDate().getMonth().maxLength();
      List<Invoice> currentMonthInvoices = getInvoicesByDateRange(
          LocalDateTime.of(currentIssueYear, currentIssueMonth, 1, 0, 0, 0),
          LocalDateTime.of(currentIssueYear, currentIssueMonth, lastDayOfMonth, 23, 59, 59));
      List<String> currentMonthNames = new ArrayList<>();
      if (currentMonthInvoices.size() > 0) {
        for (Invoice invoice : currentMonthInvoices) {
          currentMonthNames.add(invoice.getName());
        }
        Collections.sort(currentMonthNames);
        previousName = currentMonthNames.get(currentMonthNames.size() - 1);
        Matcher matcher = Pattern.compile("[^0-9]*([0-9]+).*").matcher(previousName);
        if (matcher.matches()) {
          current = current + Integer.valueOf(matcher.group(1));
          newName =
              current + "/" + currentIssueMonth + "/" + invoiceToRename.getIssueDate().getYear();
        } else {
          newName =
              current + "/" + currentIssueMonth + "/" + invoiceToRename.getIssueDate().getYear();
        }
      } else {
        newName =
            current + "/" + currentIssueMonth + "/" + invoiceToRename.getIssueDate().getYear();
      }
    } else {
      newName =
          current + "/" + currentIssueMonth + "/" + invoiceToRename.getIssueDate().getYear();
    }
    invoiceToRename.setName(newName);
  }

  /**
   * Returns ArrayList of invoices from the given time range inclusively.
   */
  public List<Invoice> getInvoicesByDateRange(LocalDateTime startDate, LocalDateTime endDate) {
    List<Invoice> resultList = new ArrayList<>();
    if (getInvoices().size() > 0) {
      for (Invoice invoice : getInvoices()) {
        if (invoice.getIssueDate().isAfter(startDate.minusSeconds(1))
            && invoice.getIssueDate().isBefore(endDate.plusSeconds(1))) {
          resultList.add(invoice);
        }
      }
    }
    return resultList;
  }

  public void removeInvoice(Invoice invoice) {

  }
}

