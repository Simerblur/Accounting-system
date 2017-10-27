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
public class TaxControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @Test
  public void shouldReturnDefaultMessageForInMemoryDatabase() throws Exception {
    this.mockMvc.perform(get("/taxes/VAT/1"))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(content().string(containsString("amount")));
  }

  @Test
  public void shouldReturnDefaultMessageAfterPostingInvoiceToTheDatabase() throws Exception {
    this.mockMvc.perform(get("/taxes/income/1").contentType(MediaType.APPLICATION_JSON).content(
        "{\"amount\": 0,\"currency\": null}"))
        .andDo(print())
        .andExpect(status().isOk());
  }
}
