package pl.coderstrust.rest;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
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
public class InvoiceControllerTest {

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
    this.mockMvc.perform(post("/invoices").contentType(MediaType.APPLICATION_JSON).content(
        "{\"invoiceId\":8,\"name\":\"7/10/2017\",\"description\":\"default description\",\"buyer"
            + "\":{\"name\":\"Jacek\",\"address1\":null,\"address2\":null,\"zip\":null,\"vatId\":"
            + "\"PL33333333\",\"accountNumber\":null},\"seller\":{\"name\":\"My Compnay name\","
            + "\"address1\":\"Building, street\",\"address2\":\"Street, City\",\"zip\":\"00-333"
            + "\",\"vatId\":\"PL9988555333\",\"accountNumber\":\"12 0000 0000 0000 1111 1111\"},"
            + "\"entries\":[{\"entryName\":\"Opona\",\"entryId\":1,\"entryQuantity\":4,"
            + "\"entryNetPrice\":{\"amount\":15.7,\"currency\":\"PLN\"},\"entryNetValue"
            + "\":{\"amount\":62.8,\"currency\":\"PLN\"},\"entryVatRate\":23,\"entryVatValue"
            + "\":{\"amount\":14.44,\"currency\":\"PLN\"},\"entryGrossValue\":{\"amount\":77.24,"
            + "\"currency\":\"PLN\"}},{\"entryName\":\"Felga\",\"entryId\":2,\"entryQuantity\":4,"
            + "\"entryNetPrice\":{\"amount\":20,\"currency\":\"PLN\"},\"entryNetValue\":{\"amount"
            + "\":80,\"currency\":\"PLN\"},\"entryVatRate\":23,\"entryVatValue\":{\"amount"
            + "\":18.4,\"currency\":\"PLN\"},\"entryGrossValue\":{\"amount\":98.4,\"currency"
            + "\":\"PLN\"}},{\"entryName\":\"Sruba\",\"entryId\":3,\"entryQuantity\":20,"
            + "\"entryNetPrice\":{\"amount\":5.3,\"currency\":\"PLN\"},\"entryNetValue\":{"
            + "\"amount\":106,\"currency\":\"PLN\"},\"entryVatRate\":23,\"entryVatValue\":{"
            + "\"amount\":24.38,\"currency\":\"PLN\"},\"entryGrossValue\":{\"amount\":130.38,"
            + "\"currency\":\"PLN\"}}],\"netTotalAmount\":{\"amount\":248.80,\"currency\":"
            + "\"PLN\"},\"grossTotalAmount\":{\"amount\":306.02,\"currency\":\"PLN\"},"
            + "\"issueDate\":\"2017-10-17 23:16:46\"}"))
        .andDo(print())
        .andExpect(status().isOk());
  }

  @Test
  public void shouldReturnSingleInvoiceForInMemoryDatabase() throws Exception {
    this.mockMvc.perform(get("/invoices/1"))
        .andDo(print())
        .andExpect(status().isNotFound())
        .andExpect(content().string(containsString("")));
  }

  @Test
  public void shouldReturnDefaultMessageAfterInvoiceUnSuccessfulDeletionForInMemoryDatabase()
      throws Exception {
    this.mockMvc.perform(delete("/invoices/1"))
        .andDo(print())
        .andExpect(status().isNotFound())
        .andExpect(content().string(containsString("Invoice ID not found!")));
  }

  @Test
  public void shouldReturnDefaultMessageAfterInvoiceSuccessfulDeletionH2()
      throws Exception {
    this.mockMvc.perform(post("/invoices").contentType(MediaType.APPLICATION_JSON).content(
        "{\"invoiceId\":8,\"name\":\"7/10/2017\",\"description\":\"default description\",\"buyer"
            + "\":{\"name\":\"Jacek\",\"address1\":null,\"address2\":null,\"zip\":null,\"vatId\":"
            + "\"PL33333333\",\"accountNumber\":null},\"seller\":{\"name\":\"My Compnay name\","
            + "\"address1\":\"Building, street\",\"address2\":\"Street, City\",\"zip\":\"00-333"
            + "\",\"vatId\":\"PL9988555333\",\"accountNumber\":\"12 0000 0000 0000 1111 1111\"},"
            + "\"entries\":[{\"entryName\":\"Opona\",\"entryId\":1,\"entryQuantity\":4,"
            + "\"entryNetPrice\":{\"amount\":15.7,\"currency\":\"PLN\"},\"entryNetValue"
            + "\":{\"amount\":62.8,\"currency\":\"PLN\"},\"entryVatRate\":23,\"entryVatValue"
            + "\":{\"amount\":14.44,\"currency\":\"PLN\"},\"entryGrossValue\":{\"amount\":77.24,"
            + "\"currency\":\"PLN\"}},{\"entryName\":\"Felga\",\"entryId\":2,\"entryQuantity\":4,"
            + "\"entryNetPrice\":{\"amount\":20,\"currency\":\"PLN\"},\"entryNetValue\":{\"amount"
            + "\":80,\"currency\":\"PLN\"},\"entryVatRate\":23,\"entryVatValue\":{\"amount"
            + "\":18.4,\"currency\":\"PLN\"},\"entryGrossValue\":{\"amount\":98.4,\"currency"
            + "\":\"PLN\"}},{\"entryName\":\"Sruba\",\"entryId\":3,\"entryQuantity\":20,"
            + "\"entryNetPrice\":{\"amount\":5.3,\"currency\":\"PLN\"},\"entryNetValue\":{"
            + "\"amount\":106,\"currency\":\"PLN\"},\"entryVatRate\":23,\"entryVatValue\":{"
            + "\"amount\":24.38,\"currency\":\"PLN\"},\"entryGrossValue\":{\"amount\":130.38,"
            + "\"currency\":\"PLN\"}}],\"netTotalAmount\":{\"amount\":248.80,\"currency\":"
            + "\"PLN\"},\"grossTotalAmount\":{\"amount\":306.02,\"currency\":\"PLN\"},"
            + "\"issueDate\":\"2017-10-17 23:16:46\"}"))
        .andDo(print())
        .andExpect(status().isOk());
    this.mockMvc.perform(delete("/invoices/1"))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(content().string(containsString("success")));
  }


  @Test
  public void shouldReturnDefaultMessageAfterInvoiceModificationForInMemoryDatabase()
      throws Exception {
    this.mockMvc.perform(post("/invoices").contentType(MediaType.APPLICATION_JSON).content(
        "{\"invoiceId\":8,\"name\":\"7/10/2017\",\"description\":\"default description\",\"buyer"
            + "\":{\"name\":\"Jacek\",\"address1\":null,\"address2\":null,\"zip\":null,\"vatId\":"
            + "\"PL33333333\",\"accountNumber\":null},\"seller\":{\"name\":\"My Compnay name\","
            + "\"address1\":\"Building, street\",\"address2\":\"Street, City\",\"zip\":\"00-333"
            + "\",\"vatId\":\"PL9988555333\",\"accountNumber\":\"12 0000 0000 0000 1111 1111\"},"
            + "\"entries\":[{\"entryName\":\"Opona\",\"entryId\":1,\"entryQuantity\":4,"
            + "\"entryNetPrice\":{\"amount\":15.7,\"currency\":\"PLN\"},\"entryNetValue"
            + "\":{\"amount\":62.8,\"currency\":\"PLN\"},\"entryVatRate\":23,\"entryVatValue"
            + "\":{\"amount\":14.44,\"currency\":\"PLN\"},\"entryGrossValue\":{\"amount\":77.24,"
            + "\"currency\":\"PLN\"}},{\"entryName\":\"Felga\",\"entryId\":2,\"entryQuantity\":4,"
            + "\"entryNetPrice\":{\"amount\":20,\"currency\":\"PLN\"},\"entryNetValue\":{\"amount"
            + "\":80,\"currency\":\"PLN\"},\"entryVatRate\":23,\"entryVatValue\":{\"amount"
            + "\":18.4,\"currency\":\"PLN\"},\"entryGrossValue\":{\"amount\":98.4,\"currency"
            + "\":\"PLN\"}},{\"entryName\":\"Sruba\",\"entryId\":3,\"entryQuantity\":20,"
            + "\"entryNetPrice\":{\"amount\":5.3,\"currency\":\"PLN\"},\"entryNetValue\":{"
            + "\"amount\":106,\"currency\":\"PLN\"},\"entryVatRate\":23,\"entryVatValue\":{"
            + "\"amount\":24.38,\"currency\":\"PLN\"},\"entryGrossValue\":{\"amount\":130.38,"
            + "\"currency\":\"PLN\"}}],\"netTotalAmount\":{\"amount\":248.80,\"currency\":"
            + "\"PLN\"},\"grossTotalAmount\":{\"amount\":306.02,\"currency\":\"PLN\"},"
            + "\"issueDate\":\"2017-10-17 23:16:46\"}"))
        .andDo(print())
        .andExpect(status().isOk());
    this.mockMvc
        .perform(put("/invoices/1").content("{\"invoiceId\":25,\"name\":\"20/10/2017"
            + "\",\"description\":\"default description\",\"buyer\":{\"name\":\"Jacek\","
            + "\"address1\":null,\"address2\":null,\"zip\":null,\"vatId\":\"PL33333333\","
            + "\"accountNumber\":null},\"seller\":{\"name\":\"My Compnay name\",\"address1"
            + "\":\"Building, street\",\"address2\":\"Street, City\",\"zip\":\"00-333\","
            + "\"vatId\":\"PL9988555333\",\"accountNumber\":\"12 0000 0000 0000 1111 1111"
            + "\"},\"entries\":[{\"entryName\":\"Opona\",\"entryId\":1,\"entryQuantity\":4,"
            + "\"entryNetPrice\":{\"amount\":15.7,\"currency\":\"PLN\"},\"entryNetValue\":{"
            + "\"amount\":62.8,\"currency\":\"PLN\"},\"entryVatRate\":23,\"entryVatValue\":{"
            + "\"amount\":14.44,\"currency\":\"PLN\"},\"entryGrossValue\":{\"amount\":77.24,"
            + "\"currency\":\"PLN\"}},{\"entryName\":\"Felga\",\"entryId\":2,\"entryQuantity"
            + "\":4,\"entryNetPrice\":{\"amount\":20,\"currency\":\"PLN\"},\"entryNetValue\":{"
            + "\"amount\":80,\"currency\":\"PLN\"},\"entryVatRate\":23,\"entryVatValue\":{"
            + "\"amount\":18.4,\"currency\":\"PLN\"},\"entryGrossValue\":{\"amount\":98.4,"
            + "\"currency\":\"PLN\"}},{\"entryName\":\"Sruba\",\"entryId\":3,\"entryQuantity"
            + "\":20,\"entryNetPrice\":{\"amount\":5.3,\"currency\":\"PLN\"},\"entryNetValue"
            + "\":{\"amount\":106,\"currency\":\"PLN\"},\"entryVatRate\":23,\"entryVatValue"
            + "\":{\"amount\":24.38,\"currency\":\"PLN\"},\"entryGrossValue\":{\"amount"
            + "\":130.38,\"currency\":\"PLN\"}}],\"netTotalAmount\":{\"amount\":248.80,"
            + "\"currency\":\"PLN\"},\"grossTotalAmount\":{\"amount\":306.02,\"currency\":"
            + "\"PLN\"},\"issueDate\":\"2017-10-18 17:16:46\"}")
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(content().string(containsString("Success!")));
  }
}
