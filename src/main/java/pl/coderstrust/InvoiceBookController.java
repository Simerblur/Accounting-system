package pl.coderstrust;

import javax.websocket.server.PathParam;
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

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@RestController
public class InvoiceBookController {

  private Database fileDb = new InFileDatabase("src/main/resources/pl.coderstrust/InvoiceBook.txt");
  private Database memDb = new InMemoryDatabase();
  private InvoiceBook ibFile = new InvoiceBook(fileDb);
  private InvoiceBook ibMem = new InvoiceBook(memDb);
  private List<Invoice> invoices = new ArrayList<>();

  /**
   * Request all invoices from Database.
   */

  @RequestMapping(value = "/system", method = RequestMethod.GET)
  public List<Invoice> getAllInvoices(
      @RequestParam(value = "getall", defaultValue = "all", required = false) String requestAll) {
    if (requestAll.equals("all")) {
      invoices = ibFile.getInvoices();
    } else {
      invoices.add(0, new Invoice("Wrong request", "Wrong request", new ArrayList<InvoiceEntry>()));
    }
    return invoices;
  }

  /**
   * Request single invoice from Database by Inoivoce ID.
   */

  @RequestMapping(value = "/system/{id}", method = RequestMethod.GET)
  public Invoice getSingleInvoice(@PathVariable int id) {

    return invoices
        .stream()
        .filter(invoice -> Integer.valueOf(invoice.getId()) == id)
        .findFirst().get();
  }

  /**
   * Post invoice to the Database.
   */

  @RequestMapping(value = "/system", method = RequestMethod.POST)
  public String postInvoice(@RequestBody Invoice jsonString) {
    ibFile.addInvoice(jsonString);
    return "Invoice added succesfully";

  }
}