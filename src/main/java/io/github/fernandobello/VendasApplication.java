package io.github.fernandobello;

import io.github.fernandobello.domain.entity.Cliente;
import io.github.fernandobello.domain.repositorio.Clientes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;  

import java.util.List;

@SpringBootApplication //O Spring reconhece que essa classe vai inicializar uma aplicação Spring Boot
@RestController //Essa classe será o controlador rest (posso controlar e mandar mensagens ao Browser)
public class VendasApplication {

    @Bean
    public CommandLineRunner init(@Autowired Clientes clientes) {
        return args -> {
            Cliente cliente = new Cliente();
            cliente.setNome("Douglas");
            clientes.salvar(cliente);

            clientes.salvar(new Cliente("Outro Cliente"));

            List<Cliente> todosClientes = clientes.obterTodos();
            todosClientes.forEach(System.out::println);
        };
    }

    public static void main(String[] args) {
        SpringApplication.run(VendasApplication.class, args);
    }
}
