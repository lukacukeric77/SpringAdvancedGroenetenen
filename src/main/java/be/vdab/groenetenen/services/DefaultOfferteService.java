package be.vdab.groenetenen.services;

import be.vdab.groenetenen.domain.Offerte;
import be.vdab.groenetenen.repositories.OfferteRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service @Transactional
class DefaultOfferteService implements OfferteService{

 private final OfferteRepository repository;

    public DefaultOfferteService(OfferteRepository repository) {
        this.repository = repository;
    }

    @Override
    public void create(Offerte offerte) {
        repository.save(offerte);
    }
}
