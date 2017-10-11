package pl.coderstrust;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.coderstrust.database.Database;
import pl.coderstrust.model.Invoice;
import pl.coderstrust.model.InvoiceBook;

import java.util.Iterator;
import java.util.List;

@Api(value = "/invoices", description = "API to controll invoices, read, add, delete,"
    + " modify invoice in the InvoiceBook", consumes = "application/json")
@RestController
public class InvoiceBookController {

  private InvoiceBook invoiceBook;

  @Autowired
  InvoiceBookController(InvoiceBook invoiceBook) {
    this.invoiceBook = invoiceBook;
  }

  /**
   * Request all invoices from Database.
   */
  @ApiOperation(value = "Return all invoices, or single invoice by ID, optionally filtered"
      + " by its fields", notes = "Multiple parameters can be provided",
      response = Invoice.class, responseContainer = "List")
  @RequestMapping(value = "/invoices", method = RequestMethod.GET)
  public List<Invoice> getAllInvoices(
      @RequestParam(value = "getall", defaultValue = "all", required = false) String requestAll) {
    return invoiceBook.getInvoices();
  }

  /**
   * Request single invoice from Database by Inoivoce ID.
   */

  @RequestMapping(value = "/invoices/{id}", method = RequestMethod.GET)
  public Invoice getSingleInvoice(@PathVariable int id) {

    return invoiceBook
        .getInvoices()
        .stream()
        .filter(invoice -> invoice.getInvoiceId() == id)
        .findFirst().get();
  }

  /**
   * Post invoice to the Database.
   */

  @RequestMapping(value = "/invoices", method = RequestMethod.POST)
  public String postInvoice(@RequestBody Invoice jsonString) {
    invoiceBook.addInvoice(jsonString);
    return "Invoice added succesfully";
  }

  /**
   * Removes the invoice with the provided ID from the Database.
   */
  @RequestMapping(value = "/invoices/{id}", method = RequestMethod.DELETE)
  public ResponseEntity<?> deleteInvoice(@PathVariable Integer id) {
    Iterator<Invoice> iterator = invoiceBook.getInvoices().iterator();
    while (iterator.hasNext()) {
      if (iterator.next().getInvoiceId() == id) {
        invoiceBook.removeInvoice(id);
        return ResponseEntity.ok("Removed succes!");
      }
    }
    return ResponseEntity.status(404).body("Invoice ID not found!");
  }

  /**
   * Updates the invoice with the provided ID from the Database. To be redone.
   */
  @RequestMapping(value = "/invoices/{id}", method = RequestMethod.PUT)
  public ResponseEntity<?> updateInvoice(@PathVariable Integer id, @RequestBody Invoice invoice) {
    List<Invoice> listToPut = invoiceBook.getInvoices();
    for (Invoice inv : listToPut) {
      if (inv.getInvoiceId() == id) {
        inv = invoice;
      }
    }
    return ResponseEntity.status(200).body("sucess");
  }
}