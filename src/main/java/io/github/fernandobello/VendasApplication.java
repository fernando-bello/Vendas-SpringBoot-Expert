package io.github.fernandobello;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication //O Spring reconhece que essa classe vai inicializar uma aplicação Spring Boot
@RestController //Essa classe será o controlador rest (posso controlar e mandar mensagens ao Browser)
public class VendasApplication {

    @GetMapping("/hello")
    public String helloWorld() {
        return "hello world";
    }

    public static void main(String[] args) {
        SpringApplication.run(VendasApplication.class, args);
    }
}
