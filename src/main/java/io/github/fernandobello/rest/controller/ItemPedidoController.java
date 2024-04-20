package io.github.fernandobello.rest.controller;

import io.github.fernandobello.domain.entity.ItemPedido;
import io.github.fernandobello.domain.repository.ItensPedidoRepository;
import io.github.fernandobello.domain.repository.PedidosRepository;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/itenspedido")

public class ItemPedidoController {

    private ItensPedidoRepository itenspedido;

    public ItemPedidoController(PedidosRepository pedidos) {this.itenspedido = itenspedido;}

    @GetMapping("/{id}")
    public ItemPedido getItemPedidoById(@PathVariable Integer id) {
        return itenspedido.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Item do pedido não encontrado"));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ItemPedido save(@RequestBody ItemPedido itemPedido) {
        return itenspedido.save(itemPedido);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id) {
        itenspedido.findById(id)
                .map(itemPedido -> {itenspedido.delete(itemPedido);
                    return itemPedido;})
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Item do pedido não encontrado"));
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable Integer id, @RequestBody ItemPedido itemPedido) {
        itenspedido
                .findById(id)
                .map(itemPedidoExistente -> {
                    itemPedido.setId(itemPedidoExistente.getId());
                    itenspedido.save(itemPedido);
                    return itemPedidoExistente;
                }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Item do pedido não encontrado"));
    }

    @GetMapping
    public List<ItemPedido> find(ItemPedido filtro) {
        ExampleMatcher matcher = ExampleMatcher
                .matching()
                .withIgnoreCase()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
        Example example = Example.of(filtro, matcher);

        return itenspedido.findAll(example);

    }



}
