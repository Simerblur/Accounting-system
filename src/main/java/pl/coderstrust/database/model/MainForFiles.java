package pl.coderstrust.database.model;

import pl.coderstrust.database.Database;
import pl.coderstrust.database.file.InFileDatabase;
import pl.coderstrust.database.fileprocessor.FileProcessor;
import pl.coderstrust.database.fileprocessor.InvoiceConverter;

import java.io.IOException;

public class MainForFiles {

  public static void main(String[] args) throws IOException {
    Database db = new InFileDatabase("src/main/resources/pl.coderstrust/InvoiceBook.txt");
    InvoiceConverter invConverter = new InvoiceConverter();
//    InvoiceConverter invCo = new InvoiceConverter();
    FileProcessor fp = new FileProcessor();
 //   File invBook = new File("src/main/resources/pl.coderstrust/InvoiceBook.txt");
    InvoiceBook invoiceBook = new InvoiceBook(db);
 //   System.out.println(invoiceBook.toString());
    Invoice first = invoiceBook.getInvoices().get(1);
    System.out.println(invConverter.convertToJsonString(first));
    Invoice second = invoiceBook.getInvoices().get(1);
    System.out.println(first.equals(second));


//    invoiceBook.printInvoiceBook();
 /*   Invoice first = new Invoice(1, "Starter:", new Money(new BigDecimal("234.45"), Currency.PLN));
    Invoice second = new Invoice(2, "conti:", new Money(new BigDecimal("88.88"), Currency.PLN));
    ObjectMapper mapper = new ObjectMapper();

    String invOutString = mapper.writeValueAsString(first);
    fp.appendInvoiceToFile(invOutString,"src/main/resources/pl.coderstrust/TestOutput.txt" );

    fp.appendInvoiceToFile(mapper.writeValueAsString(second),"src/main/resources/pl.coderstrust/TestOutput.txt");
//    System.out.println("JSON string out" + invOutString);
    invOutString = "{\"id\":3,\"description\":\"New 3:\",\"amount\":{\"amount\":333.33,\"currency\":\"PLN\"}}";
    Invoice third = invCo.convertJsonToInvoice(invOutString);
    fp.appendInvoiceToFile(mapper.writeValueAsString(third),"src/main/resources/pl.coderstrust/TestOutput.txt" );*/
 //   mapper.writeValue(invBook, first);
 //   mapper.writeValue(invBook, second);
 /*   invoiceBook.addInvoice(first);
    invoiceBook.addInvoice(second);
    invoiceBook.addInvoice(third);*/
 //   invoiceBook.printInvoiceBook();

//    fp.writeLinesToFile(invoiceBook.getInvoices(),"src/main/resources/pl.coderstrust/InvoiceBook.txt");

  }

}
