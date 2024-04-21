package io.github.fernandobello;

import io.github.fernandobello.domain.entity.Cliente;
import io.github.fernandobello.domain.entity.Produto;
import io.github.fernandobello.domain.repository.ClientesRepository;
import io.github.fernandobello.domain.repository.ProdutosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;

@SpringBootApplication //O Spring reconhece que essa classe vai inicializar uma aplicação Spring Boot
public class VendasApplication {


    @Bean
    public CommandLineRunner commandLineRunner(@Autowired ClientesRepository clientes, ProdutosRepository produtos) {
        return args -> {
            Cliente c1 = new Cliente(null, "Fulano", "87965423589");
            Cliente c2 = new Cliente(null, "Ciclano", "58796516511");
            Cliente c3 = new Cliente(null, "Aelton", "16516516511");
            clientes.save(c1);
            clientes.save(c2);
            clientes.save(c3);

            Produto p1 = new Produto(null, "TV", new BigDecimal(1500));
            Produto p2 = new Produto(null, "Notebook", new BigDecimal(1800));
            Produto p3 = new Produto(null, "Smartphone", new BigDecimal(1000));

            produtos.save(p1);
            produtos.save(p2);
            produtos.save(p3);



        };
    }

    public static void main(String[] args) {
        SpringApplication.run(VendasApplication.class, args);
    }
}
