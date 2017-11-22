package pl.coderstrust.mail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Component
@Service
public class ShedulderChecker {

    @Autowired
    private MailSender mailSender;

    private static final org.slf4j.Logger log = LoggerFactory.getLogger(ShedulderChecker.class);

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    @Scheduled(cron = "0 20 20 * * *", zone = "CET")
    public void reportCurrentTime() {
        log.info("The time is now {}", dateFormat.format(new Date()));
    }

    @Scheduled(cron = "0 20 33 * * *", zone = "CET")
    public void sendMailAtSpecifiedTime() {

        // MailSender.sendMail("pl.coderstrust@gmail.com", new String[]{"juliuszdokrzewski@gmail.com"}, "Invoice", "Its 20:33");
    }
}