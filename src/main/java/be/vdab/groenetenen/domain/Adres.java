package be.vdab.groenetenen.domain;

import org.hibernate.validator.constraints.Range;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Embeddable
@Access(AccessType.FIELD)
public class Adres {

    @NotBlank
    private String straat;

    @NotBlank
    private String huisNr;

    @NotNull @Range(min = 1000, max = 9999)
    private int postcode;

    @NotBlank
    private String gemeente;

    protected Adres() {
    }

    public Adres(String straat, String huisNr,int postcode, String gemeente) {
        this.straat = straat;
        this.huisNr = huisNr;
        this.postcode = postcode;
        this.gemeente = gemeente;
    }

    public String getStraat() {
        return straat;
    }

    public String getHuisNr() {
        return huisNr;
    }

    public int getPostcode() {
        return postcode;
    }

    public String getGemeente() {
        return gemeente;
    }
}
