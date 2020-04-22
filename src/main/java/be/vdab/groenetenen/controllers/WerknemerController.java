package be.vdab.groenetenen.controllers;

import be.vdab.groenetenen.services.WeknemerService;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("werknemers")
class WerknemerController {

    private final WeknemerService service;

    public WerknemerController(WeknemerService service) {
        this.service = service;
    }

//    @GetMapping
//    public ModelAndView lijst(){
//        return new ModelAndView("werknemers", "werknemers", service.findAll());
//    }

    @GetMapping
    public ModelAndView lijst(Pageable pageable){
        return new ModelAndView("werknemers", "page", service.findAll(pageable));
    }

}
