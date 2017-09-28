package pl.coderstrust.rest;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class MockMvcTest {
  @Autowired
  private MockMvc mockMvc;

  @Test
  public void shouldReturnDefaultMessageForInMemoryDatabase() throws Exception {
    this.mockMvc.perform(get("/invoices"))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(content().string(containsString("[]")));
  }

  @Test
  public void shouldReturnDefaultMessageAfterPostingInvoiceToTheDatabase() throws Exception {
    this.mockMvc.perform(post("/invoices").contentType(MediaType.APPLICATION_JSON).content("{\"invoiceId\":14,\"name\":\"7/9/2017\",\"description\":\"First Inv\",\"counterparts\":{\"buyer\":{\"name\":\"Kupiec Jas\",\"address1\":null,\"address2\":null,\"zip\":null,\"vatId\":\"PL12345678\",\"accountNumber\":null},\"seller\":{\"name\":\"Sprzedawca Jacek\",\"address1\":null,\"address2\":null,\"zip\":null,\"vatId\":\"PL999888777\",\"accountNumber\":null}},\"entries\":[{\"name\":\"Opona\",\"quantity\":4,\"netPrice\":{\"amount\":15.70,\"currency\":\"PLN\"},\"netValue\":{\"amount\":62.80,\"currency\":\"PLN\"},\"vatRate\":23,\"vatValue\":{\"amount\":14.44,\"currency\":\"PLN\"},\"grossValue\":{\"amount\":77.24,\"currency\":\"PLN\"}},{\"name\":\"Felga\",\"quantity\":4,\"netPrice\":{\"amount\":20.00,\"currency\":\"PLN\"},\"netValue\":{\"amount\":80.00,\"currency\":\"PLN\"},\"vatRate\":23,\"vatValue\":{\"amount\":18.40,\"currency\":\"PLN\"},\"grossValue\":{\"amount\":98.40,\"currency\":\"PLN\"}},{\"name\":\"Sruba\",\"quantity\":20,\"netPrice\":{\"amount\":5.30,\"currency\":\"PLN\"},\"netValue\":{\"amount\":106.00,\"currency\":\"PLN\"},\"vatRate\":23,\"vatValue\":{\"amount\":24.38,\"currency\":\"PLN\"},\"grossValue\":{\"amount\":130.38,\"currency\":\"PLN\"}}],\"netTotalAmount\":{\"amount\":248.80,\"currency\":\"PLN\"},\"grossTotalAmount\":{\"amount\":306.02,\"currency\":\"PLN\"},\"issueDate\":\"2017-09-27 15:33:14\"}"))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(content().string(containsString("Invoice added succesfully")));
  }
}
