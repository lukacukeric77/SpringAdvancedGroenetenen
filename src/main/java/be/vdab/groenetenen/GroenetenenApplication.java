package be.vdab.groenetenen;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.hateoas.config.EnableHypermediaSupport;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
//@EnableWebMvc
//@EnableHypermediaSupport(type = EnableHypermediaSupport.HypermediaType.HAL)
public class GroenetenenApplication {

    public static void main(String[] args) {
        SpringApplication.run(GroenetenenApplication.class, args);
    }

}
