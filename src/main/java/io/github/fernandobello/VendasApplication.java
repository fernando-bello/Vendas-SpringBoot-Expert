package io.github.fernandobello;

import io.github.fernandobello.domain.entity.Cliente;
import io.github.fernandobello.domain.repositorio.Clientes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@SpringBootApplication //O Spring reconhece que essa classe vai inicializar uma aplicação Spring Boot
@RestController //Essa classe será o controlador rest (posso controlar e mandar mensagens ao Browser)
public class VendasApplication {

    @Bean
    public CommandLineRunner init(@Autowired Clientes clientes) {
        return args -> {
            System.out.println("Salvando clientes");
            Cliente cliente = new Cliente();
            cliente.setNome("Douglas");
            clientes.save(cliente);
            clientes.save(new Cliente("Outro Cliente"));

            boolean existe = clientes.existsByNome(("Douglas"));
            System.out.println("Existe um cliente chamado Douglas? Resposta: " + existe);


        };
    }

    public static void main(String[] args) {
        SpringApplication.run(VendasApplication.class, args);
    }
}
