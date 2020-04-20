package be.vdab.groenetenen.domain;

import org.springframework.format.annotation.NumberFormat;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;

@Entity
@Table(name = "werknemers")
public class Werknemer {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank
    private String wvoornaam;

    @NotBlank
    private String famialienaam;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "filiaalId")
    @NotNull
    private Filiaal filiaal;

    @NotNull @PositiveOrZero
    @NumberFormat(style = NumberFormat.Style.NUMBER) @Digits(integer = 10, fraction = 2)
    private BigDecimal wedde;

    private long rijksregisterNr;

    protected Werknemer() {
    }

    public long getId() {
        return id;
    }

    public String getWvoornaam() {
        return wvoornaam;
    }

    public String getFamialienaam() {
        return famialienaam;
    }

    public Filiaal getFiliaal() {
        return filiaal;
    }

    public BigDecimal getWedde() {
        return wedde;
    }

    public long getRijksregisterNr() {
        return rijksregisterNr;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Werknemer)) return false;

        Werknemer werknemer = (Werknemer) o;

        return rijksregisterNr == werknemer.rijksregisterNr;
    }

    @Override
    public int hashCode() {
        return (int) (rijksregisterNr ^ (rijksregisterNr >>> 32));
    }
}
