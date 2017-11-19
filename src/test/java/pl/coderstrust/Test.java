//package pl.coderstrust;
//
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.mail.javamail.JavaMailSender;
//import org.springframework.test.context.junit4.SpringRunner;
//import pl.coderstrust.mail.MailSender;
//
//@SpringBootTest
//@RunWith(SpringRunner.class)
//public class Test {
//
//    @Autowired
//    JavaMailSender javaMailSender;
//
//    @org.junit.Test
//    public void testMail() {
//
//        MailSender m = new MailSender(javaMailSender);
//        String sender = "pl.coderstrust@gmail.com";
//        String[] receiver = new String[]{"juliuszdokrzewski@gmail.com", "rymybrzechwy123@gmail.com"};
//        m.sendMail(sender, receiver, "New invoice", "New invoice just came out, check the attachment");
//
//        System.out.println("success");
//    }
//}
//
//
