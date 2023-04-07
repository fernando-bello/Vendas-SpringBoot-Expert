package io.github.fernandobello;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MinhaConfiguration {

    @Bean(name = "applicationName") //Diz pro string criar essa String no contexto da aplicação
    public String applicationName() {
        return "Sistema de Vendas";
    }
}
