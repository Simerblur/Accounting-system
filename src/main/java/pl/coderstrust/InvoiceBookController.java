package pl.coderstrust;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.coderstrust.database.Database;
import pl.coderstrust.database.file.InFileDatabase;
import pl.coderstrust.database.memory.InMemoryDatabase;
import pl.coderstrust.model.Invoice;
import pl.coderstrust.model.InvoiceBook;
import pl.coderstrust.model.InvoiceEntry;
import pl.coderstrust.model.counterparts.Buyer;
import pl.coderstrust.model.counterparts.Counterparts;
import pl.coderstrust.model.counterparts.Seller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


@RestController
public class InvoiceBookController {

  private Database fileDb = new InFileDatabase("src/main/resources/InvoiceBook.txt");
  private Database memDb = new InMemoryDatabase();
  private InvoiceBook ibFile = new InvoiceBook(fileDb);
  private InvoiceBook ibMem = new InvoiceBook(memDb);
  private List<Invoice> invoices = new ArrayList<>();

  /**
   * Request all invoices from Database.
   */

  @RequestMapping(value = "/invoices", method = RequestMethod.GET)
  public List<Invoice> getAllInvoices(
      @RequestParam(value = "getall", defaultValue = "all", required = false) String requestAll) {
    if (requestAll.equals("all")) {
      invoices = ibFile.getInvoices();
    } else {
      invoices.add(0, new Invoice(new Counterparts(new Buyer("Kupiec Jas", "PL12345678"),
          new Seller("Sprzedawca Jacek", "PL999888777")), "Wrong request",
          new ArrayList<InvoiceEntry>()));
    }
    return invoices;
  }

  /**
   * Request single invoice from Database by Inoivoce ID.
   */

  @RequestMapping(value = "/invoices/{id}", method = RequestMethod.GET)
  public Invoice getSingleInvoice(@PathVariable int id) {

    return invoices
        .stream()
        .filter(invoice -> invoice.getInvoiceId() == id)
        .findFirst().get();
  }

  /**
   * Post invoice to the Database.
   */

  @RequestMapping(value = "/invoices", method = RequestMethod.POST)
  public String postInvoice(@RequestBody Invoice jsonString) {
    ibFile.addInvoice(jsonString);
    return "Invoice added succesfully";
  }

  /**
   * Removes the invoice with the provided ID from the Database.
   */
  @RequestMapping(value = "/invoices/{id}", method = RequestMethod.DELETE)
  public ResponseEntity<?> deleteInvoice(@PathVariable Integer id) {
    List<Invoice> listToPut = ibFile.getInvoices();
    Iterator<Invoice> iterator = listToPut.iterator();
    while (iterator.hasNext()) {
      if (iterator.next().getInvoiceId() == id) {
        iterator.remove();
        //write list to put to the Db
        return ResponseEntity.ok("Removed succes!");
      }
    }
    return ResponseEntity.status(404).body("Invoice ID not found!");
  }

  /**
   * Updates the invoice with the provided ID from the Database.
   * To be redone.
   */
  @RequestMapping(value = "/invoices/{id}", method = RequestMethod.PUT)
  public ResponseEntity<?> updateInvoice(@PathVariable Integer id, @RequestBody Invoice invoice) {
    List<Invoice> listToPut = ibFile.getInvoices();
    for (Invoice inv : listToPut) {
      if (inv.getInvoiceId() == id) {
        inv = invoice;
      }
    }

    return ResponseEntity.status(200).body("sucess");
  }
}