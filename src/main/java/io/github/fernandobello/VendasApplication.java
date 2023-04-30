package io.github.fernandobello;

import io.github.fernandobello.domain.entity.Cliente;
import io.github.fernandobello.domain.entity.Pedido;
import io.github.fernandobello.domain.repository.ClientesRepository;
import io.github.fernandobello.domain.repository.PedidosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@SpringBootApplication //O Spring reconhece que essa classe vai inicializar uma aplicação Spring Boot
@RestController //Essa classe será o controlador rest (posso controlar e mandar mensagens ao Browser)
public class VendasApplication {

    @Bean
    public CommandLineRunner init(@Autowired ClientesRepository clientes, @Autowired PedidosRepository pedidos) {
        return args -> {
            System.out.println("Salvando clientes");
            Cliente fulano = new Cliente("Fulano");
            clientes.save(fulano);

            Pedido p = new Pedido();
            p.setCliente(fulano);
            p.setDataPedido(LocalDate.now());
            p.setTotal(BigDecimal.valueOf(100));

            pedidos.save(p);

//            Cliente cliente = clientes.findClienteFetchPedidos(fulano.getId());
//            System.out.println(cliente);
//            System.out.println(cliente.getPedidos());

            pedidos.findByCliente(fulano).forEach(System.out::println);
             

        };
    }

    public static void main(String[] args) {
        SpringApplication.run(VendasApplication.class, args);
    }
}
