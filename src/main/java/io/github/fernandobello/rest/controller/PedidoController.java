package io.github.fernandobello.rest.controller;

import io.github.fernandobello.domain.entity.Pedido;
import io.github.fernandobello.domain.repository.PedidosRepository;
import io.github.fernandobello.service.PedidoService;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {

    private PedidoService service;

    public PedidoController(PedidoService service) {
        this.service = service;
    }

}
