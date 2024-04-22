package io.github.fernandobello.rest.controller;

import io.github.fernandobello.domain.entity.ItemPedido;
import io.github.fernandobello.domain.entity.Pedido;
import io.github.fernandobello.domain.enums.StatusPedido;
import io.github.fernandobello.domain.repository.PedidosRepository;
import io.github.fernandobello.rest.dto.AtualizacaoPedidoStatusDTO;
import io.github.fernandobello.rest.dto.InformacaoItemPedidoDTO;
import io.github.fernandobello.rest.dto.InformacoesPedidoDTO;
import io.github.fernandobello.rest.dto.PedidoDTO;
import io.github.fernandobello.service.PedidoService;
import org.springframework.http.HttpStatus;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {

    private PedidoService service;

    public PedidoController(PedidoService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Integer save(@RequestBody PedidoDTO dto) {
        Pedido pedido = service.salvar(dto);
        return pedido.getId();
    }

    @GetMapping("{id}")
    public InformacoesPedidoDTO getById(@PathVariable Integer id) {
        return service.obterPedidoCompleto(id).map(p -> converter(p)).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pedido não encontrado"));

    }

    @PatchMapping("{id}")
    public void updateStatus(@PathVariable Integer id, @RequestBody AtualizacaoPedidoStatusDTO dto) {
        String novoStatus = dto.getNovoStatus();
        service.atualizaStatus(id, StatusPedido.valueOf(novoStatus));
    }

    private InformacoesPedidoDTO converter(Pedido pedido) {
        return InformacoesPedidoDTO
                .builder()
                .codigo(pedido.getId())
                .dataPedido(pedido.getDataPedido().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")))
                .cpf(pedido.getCliente().getCpf())
                .nomeCliente(pedido.getCliente().getNome())
                .total(pedido.getTotal())
                .itens(converter(pedido.getItens()))
                .status(pedido.getStatus().name())
                .build();
    }

    private List<InformacaoItemPedidoDTO> converter(List<ItemPedido> itens) {
        if (CollectionUtils.isEmpty(itens)) {
            return Collections.emptyList();
        }
        return itens.stream().map(
                item -> InformacaoItemPedidoDTO.builder()
                        .descricaoProduto(item.getProduto().getDescricao())
                        .quantidade(item.getQuantidade()).precoUnitario(item.getProduto().getPreco()).build()
        ).collect(Collectors.toList());
    }

}
