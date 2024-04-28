package io.github.fernandobello.config;

import io.github.fernandobello.service.impl.UsuarioServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class SecutiryConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UsuarioServiceImpl usuarioService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(usuarioService)
                .passwordEncoder(passwordEncoder());
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.csrf().disable() //configuração que permite que haja segurança em uma aplicação web
                .authorizeRequests()
                .antMatchers("/api/clientes/**").hasAnyRole("USER", "ADMIN") //obs: ** são os urls que recebem parametro
                .antMatchers("api/produtos/**").hasRole("ADMIN")
                .antMatchers("/api/pedidos/**").hasAnyRole("USER", "ADMIN")
                .antMatchers(HttpMethod.POST, "/api/usuarios/**").permitAll() //Permite que todos possam acessar o cadastro de usuario (apenas metodo POST)
                .anyRequest().authenticated() //mapeia algum request que foi esquecido
                .and()
                .httpBasic();
    }
}
