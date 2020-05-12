package be.vdab.groenetenen.services;

import be.vdab.groenetenen.domain.Offerte;
import be.vdab.groenetenen.mail.MailSender;
import be.vdab.groenetenen.messaging.OfferteEnOffertesURL;
import be.vdab.groenetenen.repositories.OfferteRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service @Transactional
class DefaultOfferteService implements OfferteService{

 private final OfferteRepository repository;
 private final MailSender sender;
 private final JmsTemplate template;
 private final String nieuweOfferteQueue;

    public DefaultOfferteService(OfferteRepository repository, MailSender sender,
                                 JmsTemplate template, @Value("${nieuweOfferteQueue}") String nieuweOfferteQueue) {
        this.repository = repository;
        this.sender = sender;
        this.template = template;
        this.nieuweOfferteQueue = nieuweOfferteQueue;
    }

    @Override
    public void create(Offerte offerte, String offertesURL) {
        repository.save(offerte);
//        sender.nieuweOfferte(offerte, offertesURL);
        OfferteEnOffertesURL offerteEnOffertesURL = new OfferteEnOffertesURL(offerte, offertesURL);
        template.convertAndSend(nieuweOfferteQueue, offerteEnOffertesURL);
    }

    @Override
    @Scheduled(cron = "0 0 0 1 1/1 ?")
    public void aantalOffertesMail() {
        sender.aantalOffertesMail(repository.count());
    }
}
