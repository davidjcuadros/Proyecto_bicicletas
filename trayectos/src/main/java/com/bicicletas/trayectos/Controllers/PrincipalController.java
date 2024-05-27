package com.bicicletas.trayectos.Controllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PrincipalController {

    @GetMapping("/")
    public String mostrarPantallaPrincipal() {
        return "index";
    }
}