package pl.coderstrust.database.memory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import pl.coderstrust.database.Database;
import pl.coderstrust.fileprocessor.InvoiceConverter;
import pl.coderstrust.mail.MailSender;
import pl.coderstrust.model.Invoice;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Component
@Service
@ConditionalOnProperty(name = "pl.coderstrust.database", havingValue = "inMemoryDatabase")
public class InMemoryDatabase implements Database {

    @Autowired
    private MailSender mailSender;
    private List<Invoice> invoices = new ArrayList<>();
    @Autowired
    private InvoiceConverter invoiceConverter;

    @Override
    public void saveInvoice(Invoice invoice) {
        invoices.add(invoice);
    }

    @Override
    public void saveInvoice(String jsonString) {
        invoices.add(invoiceConverter.convertJsonToInvoice(jsonString));
    }

    @Override
    public List<Invoice> getInvoices() {
        return Collections.unmodifiableList(invoices);
    }

    @Override
    public List<Invoice> getInvoicesFromCurrentDay() {
        return invoices.stream().filter(invoice -> invoice.getIssueDate().toLocalDate().isEqual(LocalDate.now())).collect(Collectors.toList());
    }

    @Override
    public void sendEmail(Invoice invoice) {
        invoiceConverter.convertToJsonString(invoice);
        String invoiceContent = invoiceConverter.convertToJsonString(invoice);
        mailSender.sendMail("pl.coderstrust@gmail.com", new String[]{"juliuszdokrzewski@gmail.com"}, "Invoice", invoiceContent);
    }


    @Override
    public void removeInvoice(int invoiceId) {
        int index = -1;
        try {
            for (Invoice invoice : invoices) {
                if (invoice.getInvoiceId() == invoiceId) {
                    index = invoices.indexOf(invoice);
                }
            }
            invoices.remove(index);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Invoice not found!");
        }
    }

}
