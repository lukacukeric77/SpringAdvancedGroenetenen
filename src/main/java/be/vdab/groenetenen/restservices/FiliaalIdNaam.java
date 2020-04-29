package be.vdab.groenetenen.restservices;

import be.vdab.groenetenen.domain.Filiaal;
import com.fasterxml.jackson.annotation.JsonAutoDetect;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
class FiliaalIdNaam {
    private long id;
    private String naam;

    FiliaalIdNaam() { }
    FiliaalIdNaam(Filiaal filiaal) {
        this.id = filiaal.getId();
        this.naam = filiaal.getNaam();
    }
}
