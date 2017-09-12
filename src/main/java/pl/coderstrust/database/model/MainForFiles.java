package pl.coderstrust.database.model;

import com.fasterxml.jackson.databind.ObjectMapper;
import pl.coderstrust.database.Database;
import pl.coderstrust.database.file.InFileDatabase;
import pl.coderstrust.database.fileprocessor.FileProcessor;
import pl.coderstrust.database.fileprocessor.InvoiceConverter;
import pl.coderstrust.database.memory.InMemoryDatabase;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;

public class MainForFiles {

  public static void main(String[] args) throws IOException {
    Database db = new InFileDatabase("src/main/resources/pl.coderstrust/InvoiceBook.txt");
    InvoiceConverter invCo = new InvoiceConverter();
    FileProcessor fp = new FileProcessor();
    File invBook = new File("src/main/resources/pl.coderstrust/InvoiceBook.txt");
    InvoiceBook invoiceBook = new InvoiceBook(db);
    Invoice first = new Invoice(1, "Starter:", new Money(new BigDecimal("234.45"), Currency.PLN));
    Invoice second = new Invoice(2, "conti:", new Money(new BigDecimal("88.88"), Currency.PLN));
    ObjectMapper mapper = new ObjectMapper();

    String invOutString = mapper.writeValueAsString(first);
    fp.writeLineToFile(invOutString,"src/main/resources/pl.coderstrust/TestOutput.txt" );

    fp.writeLineToFile(mapper.writeValueAsString(second),"src/main/resources/pl.coderstrust/TestOutput.txt");
//    System.out.println("JSON string out" + invOutString);
    invOutString = "{\"id\":3,\"description\":\"New 3:\",\"amount\":{\"amount\":333.33,\"currency\":\"PLN\"}}";
    Invoice third = invCo.convertJsonToInvoice(invOutString);
    fp.writeLineToFile(mapper.writeValueAsString(third),"src/main/resources/pl.coderstrust/TestOutput.txt" );
    mapper.writeValue(invBook, first);
    mapper.writeValue(invBook, second);
    invoiceBook.addInvoice(first);
    invoiceBook.addInvoice(second);
    invoiceBook.addInvoice(third);
    invoiceBook.printInvoiceBook();

//    fp.writeLinesToFile(invoiceBook.getInvoices(),"src/main/resources/pl.coderstrust/InvoiceBook.txt");

  }

}
