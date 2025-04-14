package com.umc.produto.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    // Página inicial após o login bem-sucedido
    @GetMapping("/home")
    public String home() {
        return "home"; // Renderiza o template home.html
    }
}
