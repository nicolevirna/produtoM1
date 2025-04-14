package com.umc.produto.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    // Mapeia a página de login personalizada
    @GetMapping("/login")
    public String login(@RequestParam(value = "error", required = false) String error,
                        @RequestParam(value = "logout", required = false) String logout,
                        Model model) {
        // Se houver erro no login, adiciona mensagem de erro
        if (error != null) {
            model.addAttribute("error", true);
        }

        // Se o usuário acabou de sair (logout), adiciona mensagem de sucesso
        if (logout != null) {
            model.addAttribute("logout", true);
        }

        return "login"; // Retorna para a view de login
    }
}
