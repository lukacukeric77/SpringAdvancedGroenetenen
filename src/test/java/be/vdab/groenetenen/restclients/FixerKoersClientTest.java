package be.vdab.groenetenen.restclients;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class FixerKoersClientTest {

    private final FixerKoersClient client;

    public FixerKoersClientTest(FixerKoersClient client) {
        this.client = client;
    }

    @Test
    void deKoersMoetPositiefZijn() {
        assertThat(client.getDollarsKoers()).isGreaterThan(BigDecimal.ZERO);
    }
}