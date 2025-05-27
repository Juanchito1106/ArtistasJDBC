package com.juancho.artistas.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class InicioController {

    //    Metodo para ir a la interfaz de inicio
    @GetMapping("/")
    public String paginaInicial() {
        return "initialScreen"; // se busca en templates/initialScreen.html
    }
}
