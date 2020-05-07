package be.vdab.groenetenen.mail;

import be.vdab.groenetenen.domain.Offerte;
import be.vdab.groenetenen.exceptions.KanMailNietZendenException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Component
class DefaultMailSender implements MailSender{

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final JavaMailSender sender;

    DefaultMailSender(JavaMailSender sender) {
        this.sender = sender;
    }

    @Override
    public void nieuweOfferte(Offerte offerte, String offertesURL) {
        try{
//            SimpleMailMessage message = new SimpleMailMessage();
//            message.setTo(offerte.getEmailadres());
//            message.setSubject("Nieuwe offerte");
//            message.setText("Uw offerte heeft het nummer " + offerte.getId());
//            sender.send(message);
            MimeMessage message = sender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message);
            helper.setTo(offerte.getEmailadres());
            helper.setSubject("Nieuwe offerte - deel 2");
            String offerteURL = offertesURL + offerte.getId();
            helper.setText("Uw offerte heeft het nummer <strong>"+offerte.getId()+"</strong>." +
                    "Je vindt de offerte <a href='" + offerteURL + "'>hier</a>", true);
            sender.send(message);
        } catch (MailException | MessagingException exception){
            logger.error("Kan mail nieuwe offerte niet versturen", exception);
            throw new KanMailNietZendenException();
        }
    }
}
