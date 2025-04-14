package com.umc.produto.security;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    // Configuração de autenticação em memória com usuário padrão
    @Bean
    public InMemoryUserDetailsManager userDetailsService() {
        return new InMemoryUserDetailsManager(
                User.withDefaultPasswordEncoder()
                        .username("admin") // Usuário padrão
                        .password("1234") // Senha padrão
                        .roles("USER")
                        .build());
    }

    // Configurações de segurança (autorização, login, logout)
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/css/**", "/js/**").permitAll() // Permite acesso a recursos estáticos
                        .anyRequest().authenticated() // Qualquer outra requisição precisa estar autenticada
                )
                .formLogin(form -> form
                        .loginPage("/login").permitAll() // Página customizada de login
                        .defaultSuccessUrl("/home", true) // Redireciona para /home após login com sucesso
                )
                .logout(logout -> logout
                        .logoutUrl("/logout") // Endpoint de logout
                        .logoutSuccessUrl("/login?logout") // Redireciona para login com mensagem de logout
                );

        return http.build();
    }
}
