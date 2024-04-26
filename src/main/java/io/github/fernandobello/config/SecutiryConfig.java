package io.github.fernandobello.config;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class SecutiryConfig extends WebSecurityConfigurerAdapter {

    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .passwordEncoder((passwordEncoder()))
                .withUser("fulano")//usuario
                .password(passwordEncoder().encode("123"))//password criptografado
                .roles("USER", "ADMIN");//perfil de usuario
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.csrf().disable() //configuração que permite que haja segurança em uma aplicação web
                .authorizeRequests()
                .antMatchers("/api/clientes/**").hasAnyRole("USER", "ADMIN") //obs: ** são os urls que recebem parametro
                .antMatchers("api/produtos/**").hasRole("ADMIN")
                .antMatchers("/api/pedidos/**").hasAnyRole("USER", "ADMIN")
                .and()
                .httpBasic();
    }
}
