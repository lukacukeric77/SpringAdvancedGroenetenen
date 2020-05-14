package be.vdab.groenetenen.controllers;

import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("login")
class LoginController {
    @GetMapping
    public String login(){
        return "login";
    }
}
