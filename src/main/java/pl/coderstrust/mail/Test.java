package pl.coderstrust.mail;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

public class Test {
    public static void main(String[] args) {

        Resource r = new ClassPathResource("applicationContext.xml");
        BeanFactory b = new XmlBeanFactory(r);
        MailMail m = (MailMail) b.getBean("mailMail");
        String sender = "pl.coderstrust@gmail.com";
        String[] receiver = new String[]{"juliuszdokrzewski@gmail.com", "julekdorzewski@gmail.com"};
        m.sendMail(sender, receiver, "New invoice", "New invoice just came out, check the attachment");

        System.out.println("success");
    }
}


