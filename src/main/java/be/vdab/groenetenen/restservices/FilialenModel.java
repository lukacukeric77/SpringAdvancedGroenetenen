package be.vdab.groenetenen.restservices;

import be.vdab.groenetenen.domain.Filiaal;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.EntityLinks;

import java.util.ArrayList;
import java.util.List;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
class FilialenModel extends RepresentationModel<FilialenModel> {
    @JsonProperty("filialen")
    private final List <FiliaalIdNaam> filiaalIdNaams = new ArrayList<>();

    FilialenModel() {
    }

    FilialenModel(Iterable<Filiaal> filialen, EntityLinks entityLinks) {
        for (Filiaal filiaal : filialen){
            this.filiaalIdNaams.add(new FiliaalIdNaam(filiaal));
            super.add(entityLinks.linkToItemResource(Filiaal.class, filiaal.getId())
                    .withRel("detail." + filiaal.getId()));
        }
        super.add(entityLinks.linkToCollectionResource(Filiaal.class));
    }

}
