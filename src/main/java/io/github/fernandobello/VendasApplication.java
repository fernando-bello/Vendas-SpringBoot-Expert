package io.github.fernandobello;

import io.github.fernandobello.domain.entity.Cliente;
import io.github.fernandobello.domain.repository.ClientesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication //O Spring reconhece que essa classe vai inicializar uma aplicação Spring Boot
public class VendasApplication {


    @Bean
    public CommandLineRunner commandLineRunner(@Autowired ClientesRepository clientes) {
        return args -> {
            Cliente c1 = new Cliente(null, "Fulano");
            Cliente c2 = new Cliente(null, "Ciclano");
            Cliente c3 = new Cliente(null, "Aelton");
            clientes.save(c1);
            clientes.save(c2);
            clientes.save(c3);

        };
    }

    public static void main(String[] args) {
        SpringApplication.run(VendasApplication.class, args);
    }
}
