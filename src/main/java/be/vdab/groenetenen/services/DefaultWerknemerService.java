package be.vdab.groenetenen.services;

import be.vdab.groenetenen.domain.Werknemer;
import be.vdab.groenetenen.repositories.WerknemerRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service @Transactional
public class DefaultWerknemerService implements WeknemerService{

    private final WerknemerRepository repository;

    public DefaultWerknemerService(WerknemerRepository repository) {
        this.repository = repository;
    }

//    @Override @Transactional(readOnly = true)
//    public List<Werknemer> findAll(){
//        return repository.findAll(Sort.by("familienaam", "voornaam"));
//    }


    @Override @Transactional(readOnly = true)
    public Page<Werknemer> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }
}
