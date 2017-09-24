package pl.coderstrust;

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


@RestController
public class InvoiceBookController {

  private Database fileDb = new InFileDatabase("src/main/resources/pl.coderstrust/InvoiceBook.txt");
  private Database memDb = new InMemoryDatabase();
  private InvoiceBook ibFile = new InvoiceBook(fileDb);
  private InvoiceBook ibMem = new InvoiceBook(memDb);

  /**
   * Request all invoices from Database.
   */

  @RequestMapping(value = "/system", method = RequestMethod.GET)
  public List<Invoice> getAllInvoices(
      @RequestParam(value = "getall", defaultValue = "all", required = false) String requestAll) {
    List<Invoice> allList = new ArrayList<>();
    if (requestAll.equals("all")) {
      allList = ibFile.getInvoices();
    } else {
      allList.add(0, new Invoice("Wrong request", "Wrong request", new ArrayList<InvoiceEntry>()));
    }

    return allList;
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