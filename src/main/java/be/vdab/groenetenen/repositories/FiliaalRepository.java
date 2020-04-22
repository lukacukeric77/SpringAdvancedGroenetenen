package be.vdab.groenetenen.repositories;

import be.vdab.groenetenen.domain.Filiaal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;

public interface FiliaalRepository extends JpaRepository <Filiaal, Long> {
    List<Filiaal> findByAdresPostcodeBetweenOrderByAdresPostcode(int van, int tot);
    List<Filiaal> findMetHoogsteWaardeGebouw();
    BigDecimal findGemiddeldeWaardeGebouwInGemeente(@Param("gemeente") String gemeente);

}
