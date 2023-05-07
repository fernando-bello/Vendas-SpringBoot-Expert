package io.github.fernandobello.domain.rest.controller;


import io.github.fernandobello.domain.entity.Cliente;
import io.github.fernandobello.domain.repository.ClientesRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
public class ClienteController {

    private ClientesRepository clientes;

    public ClienteController(ClientesRepository clientes) {
        this.clientes = clientes;
    }

    @GetMapping("/api/clientes/{id}")
    @ResponseBody
    public ResponseEntity getClienteById(@PathVariable("id") Integer id) {
        Optional<Cliente> cliente = clientes.findById(id);

        if (cliente.isPresent()) {
            //ResponseEntity<Cliente> = new ResponseEntity<>(cliente.get(), HttpStatus.OK); -> função que acontece no ok(cliente.get()) abaixo
            return ResponseEntity.ok(cliente.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/api/clientes")
    @ResponseBody
    public ResponseEntity save(@RequestBody Cliente cliente) {
        Cliente clienteSalvo = clientes.save(cliente);
        return ResponseEntity.ok(clienteSalvo);
    }

    @DeleteMapping("api/clientes/{id}")
    @ResponseBody
    public ResponseEntity delete(@PathVariable Integer id) {
        Optional<Cliente> cliente = clientes.findById(id);

        if (cliente.isPresent()) {
            clientes.delete(cliente.get());
            return ResponseEntity.noContent().build(); //noContent() é um codigo de status de sucesso, que não retorna NADA
        }
        return ResponseEntity.notFound().build();
    }
}

