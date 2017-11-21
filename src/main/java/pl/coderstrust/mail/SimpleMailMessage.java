//package pl.coderstrust.mail;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//
//
//public class SimpleMailMessage implements JavaMailSenderImpl {
//    @Bean
//    public SimpleMailMessage templateSimpleMessage() {
//        SimpleMailMessage message = new SimpleMailMessage();
//        message.setText(
//                "This is the test email template for your email:\n%s\n");
//        return message;
//    }
//
//    public void sendMail(String from, String[] to, String subject, String msg) {
//
//        SimpleMailMessage message = new SimpleMailMessage();
//        message.setFrom(from);
//        message.setTo(to);
//        message.setSubject(subject);
////        message.setText(msg);
//        mailSender.send(message);
//    }
//
//    @Autowired
//    public SimpleMailMessage template;
//
//    String text = String.format(template.getText(), templateArgs);
//
//    sendSimpleMessage(to, subject, text);
//}