package be.vdab.groenetenen.controllers;

import be.vdab.groenetenen.exceptions.KanKoersNietLezenException;
import be.vdab.groenetenen.services.EuroService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.math.BigDecimal;


@Controller
@RequestMapping("euro")
class EuroController {

    private final EuroService service;

    public EuroController(EuroService service) {
        this.service = service;
    }

    @GetMapping("{euro}/naardollar")
    public ModelAndView toDollar (@PathVariable BigDecimal euro){
        ModelAndView modelAndView = new ModelAndView("naardollar");
        try {
            modelAndView.addObject("dollar", service.naarDollar(euro));
        } catch (KanKoersNietLezenException ex){

        }
        return modelAndView;
    }
}
