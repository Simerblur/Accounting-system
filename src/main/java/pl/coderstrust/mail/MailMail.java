package pl.coderstrust.mail;

import javax.mail.Message;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;

import java.io.File;

public class MailMail {
    private JavaMailSender mailSender;

    public void setMailSender(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendMail(String from, String[] to, String subject, String msg) {

        MimeMessagePreparator messagePreparator = new MimeMessagePreparator() {

            public void prepare(MimeMessage mimeMessage) throws Exception {
                MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

                helper.setTo(to);
                helper.setFrom(new InternetAddress(from));
                helper.setSubject(subject);
                helper.setText(msg);

                FileSystemResource file = new FileSystemResource(new File("d:\\invoice.txt"));
                helper.addAttachment("invoice.txt", file);

            }
        };
        mailSender.send(messagePreparator);
    }
}