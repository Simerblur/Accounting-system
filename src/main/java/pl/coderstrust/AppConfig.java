package pl.coderstrust;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import pl.coderstrust.database.Database;
import pl.coderstrust.database.file.InFileDatabase;
import pl.coderstrust.database.memory.InMemoryDatabase;
import pl.coderstrust.model.InvoiceBook;

@Configuration
public class AppConfig {

 /* @Bean
  public InvoiceBook invoiceBook() {
    return new InvoiceBook(inMemoryDatabase());
  }

  // @Primary
  @Bean
  public Database inMemoryDatabase() {
    return new InMemoryDatabase();
  }

  @Bean
  public Database inFIleDatabase() {
    return new InFileDatabase(dbPath());
  }

  @Bean
  public String dbPath() {
    return "src\\main\\resources\\InvoiceBook.txt";
  }*/
}
