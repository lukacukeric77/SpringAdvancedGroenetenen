package be.vdab.groenetenen.domain;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collections;
import java.util.Set;

@Entity
@Table(name = "filialen")
class Filiaal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank
    private String naam;

    private boolean hoofdFiliaal;

    @NumberFormat(style = NumberFormat.Style.NUMBER)
    @NotNull
    @PositiveOrZero
    @Digits(integer = 10, fraction = 2)
    private BigDecimal waardeGebouw;

    @DateTimeFormat(style = "S-")
    @NotNull
    private LocalDate inGebruikName;

    @Valid
    @Embedded
    private Adres adres;

    @Version
    private long versie;

    @OneToMany(mappedBy = "filiaal")
    private Set<Werknemer> werknemers;

    protected Filiaal() {
    }

    public long getId() {
        return id;
    }

    public String getNaam() {
        return naam;
    }

    public boolean isHoofdFiliaal() {
        return hoofdFiliaal;
    }

    public BigDecimal getWaardeGebouw() {
        return waardeGebouw;
    }

    public LocalDate getInGebruikName() {
        return inGebruikName;
    }

    public Adres getAdres() {
        return adres;
    }

    public long getVersie() {
        return versie;
    }

    public Set<Werknemer> getWerknemers() {
        return Collections.unmodifiableSet(werknemers);
    }
}