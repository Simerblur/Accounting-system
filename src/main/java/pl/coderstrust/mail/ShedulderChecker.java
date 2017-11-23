package pl.coderstrust.mail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import pl.coderstrust.database.Database;
import pl.coderstrust.database.memory.InMemoryDatabase;
import pl.coderstrust.fileprocessor.InvoiceConverter;
import pl.coderstrust.model.Invoice;

@Component
@Service
public class ShedulderChecker {

    @Autowired
    InvoiceConverter invoiceConverter;

    @Autowired
    private MailSender mailSender;
    @Autowired
    ShedulderChecker shedulderChecker;
    @Autowired
    Database database;

    private static final org.slf4j.Logger log = LoggerFactory.getLogger(ShedulderChecker.class);

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");


    @Scheduled(cron = "0 20 20 * * *", zone = "CET")
    public void reportCurrentTime() {
        log.info("The time is now {}", dateFormat.format(new Date()));
    }

    public String invConv() {
        List<Invoice> invoicesFromCurrentDay = database.getInvoicesFromCurrentDay();
        StringBuilder stringBuilder = new StringBuilder();
        invoicesFromCurrentDay.forEach(invoice -> stringBuilder.append(invoiceConverter.convertToJsonString(invoice)));
        return stringBuilder.toString();
    }

    @Scheduled(cron = "0 48 19 * * *", zone = "CET")
    public void sendMailAtSpecifiedTime() {
        mailSender.sendMail("pl.coderstrust@gmail.com", new String[]{"juliuszdokrzewski@gmail.com"}, "Invoice", invConv());
    }


}