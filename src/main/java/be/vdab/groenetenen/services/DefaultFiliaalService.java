package be.vdab.groenetenen.services;

import be.vdab.groenetenen.domain.Filiaal;
import be.vdab.groenetenen.exceptions.FiliaalHeeftNogWerknemersException;
import be.vdab.groenetenen.repositories.FiliaalRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class DefaultFiliaalService implements FiliaalService{

    private final FiliaalRepository repository;

    public DefaultFiliaalService(FiliaalRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Filiaal> findByPostcode(int van, int tot) {
        return repository.findByAdresPostcodeBetweenOrderByAdresPostcode(van, tot);
    }

    @Override
    public void delete(Filiaal filiaal) {
        if (!filiaal.getWerknemers().isEmpty()){
            throw new FiliaalHeeftNogWerknemersException();
        } repository.delete(filiaal);
    }

    @Override
    public void create(Filiaal filiaal) {
        repository.save(filiaal);
    }

    @Override
    public void update(Filiaal filiaal) {
        repository.save(filiaal);
    }
}
