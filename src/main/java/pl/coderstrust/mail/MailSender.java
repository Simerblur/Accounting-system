package pl.coderstrust.mail;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;
import pl.coderstrust.model.Invoice;

@Service
public class MailSender {


    private final JavaMailSender javaMailSender;

    public MailSender(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }


    public void sendEmail(Invoice invoice) {
        //  invoiceConverter.convertToJsonString(invoice)
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
    //        javaMailSender.send(messagePreparator);
    //        MimeMessage messagePreparator;
    //        List<Invoice> listOfAllInvoices = getInvoices();
    //  //     invoiceList.get(invoice);
    //  //      List<Invoice> invoiceList = new ArrayList<>();
//    public void sendInvoice(Invoice invoice){

//    }
}