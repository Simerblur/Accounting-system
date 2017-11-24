package pl.coderstrust.mail;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import pl.coderstrust.fileprocessor.InvoiceConverter;
import pl.coderstrust.model.Invoice;

import java.util.Date;

@Component
@Service
public class MailSender {

    private JavaMailSender javaMailSender;

    public MailSender(JavaMailSender sender) {
        this.javaMailSender = sender;
    }

    public void sendMail(String from, String[] to, String subject, String msg) {

        MimeMessagePreparator messagePreparator = new MimeMessagePreparator() {

            public void prepare(MimeMessage mimeMessage) throws Exception {
                MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

                helper.setTo(to);
                helper.setFrom(new InternetAddress(from));
                helper.setSubject(subject);
                helper.setText(msg);


//                FileSystemResource file = new FileSystemResource(new File("d:\\invoice.txt"));
//                helper.addAttachment("invoice.txt", file);

            }
        };
        javaMailSender.send(messagePreparator);
    }

}

