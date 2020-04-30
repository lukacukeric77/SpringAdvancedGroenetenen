package be.vdab.groenetenen.services;

import be.vdab.groenetenen.restclients.KoersClient;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
public class EuroServiceImpl implements EuroService{

    private final KoersClient client;

    public EuroServiceImpl(KoersClient client) {
        this.client = client;
    }

    @Override
    public BigDecimal naarDollar(BigDecimal euro) {
        return euro.multiply(client.getDollarsKoers()).setScale(2, RoundingMode.HALF_UP);
    }
}
