package pl.coderstrust.mail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

public class SimpleMailMessage implements MailingSystem {
    @Bean
    public SimpleMailMessage templateSimpleMessage() {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setText(
                "This is the test email template for your email:\n%s\n");
        return message;
    }

    @Autowired
    public SimpleMailMessage template;

    String text = String.format(template.getText(), templateArgs);

    sendSimpleMessage(to, subject, text);
}
