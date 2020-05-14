package be.vdab.groenetenen.domain;

import be.vdab.groenetenen.adapters.LocalDateAdapter;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collections;
import java.util.Set;

@Entity
@Table(name = "filialen")
@XmlRootElement(name = "filiaal")
@XmlAccessorType(XmlAccessType.FIELD) @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class Filiaal {
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
    @XmlJavaTypeAdapter(value = LocalDateAdapter.class)
    private LocalDate inGebruikName;

    @Valid
    @Embedded
    private Adres adres;

    @Version
    private long versie;

    @OneToMany(mappedBy = "filiaal")
    @XmlTransient @JsonIgnore
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

    public void afschrijven(){
        waardeGebouw = BigDecimal.ZERO;
    }

}