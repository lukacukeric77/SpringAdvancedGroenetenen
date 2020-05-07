package be.vdab.groenetenen.services;

import be.vdab.groenetenen.domain.Offerte;
import be.vdab.groenetenen.mail.MailSender;
import be.vdab.groenetenen.repositories.OfferteRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service @Transactional
class DefaultOfferteService implements OfferteService{

 private final OfferteRepository repository;
 private final MailSender sender;

    public DefaultOfferteService(OfferteRepository repository, MailSender sender) {
        this.repository = repository;
        this.sender = sender;
    }

    @Override
    public void create(Offerte offerte, String offertesURL) {
        repository.save(offerte);
        sender.nieuweOfferte(offerte, offertesURL);
    }
}
