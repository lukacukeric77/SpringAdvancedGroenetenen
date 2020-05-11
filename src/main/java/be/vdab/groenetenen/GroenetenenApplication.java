package be.vdab.groenetenen;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.hateoas.config.EnableHypermediaSupport;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
//@EnableWebMvc
//@EnableHypermediaSupport(type = EnableHypermediaSupport.HypermediaType.HAL)
@EnableAsync @EnableScheduling
public class GroenetenenApplication {

    public static void main(String[] args) {
        SpringApplication.run(GroenetenenApplication.class, args);

//        System.out.println(System.getProperty("java.home"));

    }

}
