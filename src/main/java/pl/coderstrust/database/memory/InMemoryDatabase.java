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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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

    @Scheduled(cron = "0 0 24 * * *", zone = "CET")
    @Override
    public void sendEmail(Invoice invoice) {
        invoiceConverter.convertToJsonString(invoice);
        String invoiceContent = invoiceConverter.convertToJsonString(invoice);
        mailSender.sendMail("pl.coderstrust@gmail.com", new String[]{"juliuszdokrzewski@gmail.com"}, "Invoice", invoiceContent);
    }

//    @Scheduled(cron = "0 0 21 * * *", zone = "Warsaw, Poland")
//
//    public void sendMailEveryMidnight(Invoice invoice) {
//        System.out.println("Scheduled task running");
//
//    }


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
