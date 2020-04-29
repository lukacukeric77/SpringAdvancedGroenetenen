package be.vdab.groenetenen.restservices;

import be.vdab.groenetenen.domain.Filiaal;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.EntityLinks;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
class FiliaalModel extends RepresentationModel<FiliaalModel> {
    @SuppressWarnings(("unused"))
    private Filiaal filiaal;

    public FiliaalModel() {}

    public FiliaalModel(Filiaal filiaal, EntityLinks entityLinks) {
        this.filiaal = filiaal;
        super.add(entityLinks.linkToItemResource(Filiaal.class, this.filiaal.getId()));
        super.add(entityLinks.linkForItemResource(Filiaal.class, this.filiaal.getId())
        .slash("werknemers").withRel("werknemers"));
    }
}
