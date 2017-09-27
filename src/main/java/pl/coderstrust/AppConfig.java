package pl.coderstrust;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.coderstrust.database.Database;
import pl.coderstrust.database.file.InFileDatabase;

@Configuration
public class AppConfig {

  @Bean
  public Database inFIleDatabase() {
    return new InFileDatabase(dbPath());
  }

  @Bean
  public String dbPath() {
    return "src\\main\\resources\\InvoiceBook.txt";
  }
}
