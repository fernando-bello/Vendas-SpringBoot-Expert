package io.github.fernandobello.service.impl;

import io.github.fernandobello.domain.entity.Cliente;
import io.github.fernandobello.domain.entity.ItemPedido;
import io.github.fernandobello.domain.entity.Pedido;
import io.github.fernandobello.domain.entity.Produto;
import io.github.fernandobello.domain.repository.ClientesRepository;
import io.github.fernandobello.domain.repository.ItensPedidoRepository;
import io.github.fernandobello.domain.repository.PedidosRepository;
import io.github.fernandobello.domain.repository.ProdutosRepository;
import io.github.fernandobello.exception.RegraNegocioException;
import io.github.fernandobello.rest.dto.ItemPedidoDTO;
import io.github.fernandobello.rest.dto.PedidoDTO;
import io.github.fernandobello.service.PedidoService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PedidoServiceImpl implements PedidoService {

    private PedidosRepository pedidosRepository;
    private ClientesRepository clientesRepository;
    private ProdutosRepository produtosRepository;
    private ItensPedidoRepository itensPedidoRepository;

    public PedidoServiceImpl(PedidosRepository pedidosRepository, ClientesRepository clientesRepository, ItensPedidoRepository itensPedidoRepository, ProdutosRepository produtosRepository) {
        this.pedidosRepository = pedidosRepository;
        this.clientesRepository = clientesRepository;
        this.produtosRepository = produtosRepository;
        this.itensPedidoRepository = itensPedidoRepository;
    }

    @Override
    @Transactional //Se algo der errado no processo, desfaz tudo (rollback)
    public Pedido salvar(PedidoDTO dto) {
        Integer idCliente = dto.getCliente();
        Cliente cliente =
                clientesRepository.findById(idCliente)
                        .orElseThrow(() -> new RegraNegocioException("Código de cliente inválido."));

        Pedido pedido = new Pedido();
        pedido.setTotal(dto.getTotal());
        pedido.setDataPedido(LocalDate.now());
        pedido.setCliente(cliente);

        List<ItemPedido> itensPedido = converterItens(pedido, dto.getItens());
        pedidosRepository.save(pedido);
        itensPedidoRepository.saveAll(itensPedido);
        pedido.setItens(itensPedido);
        return pedido;

    }

    private List<ItemPedido> converterItens(Pedido pedido, List<ItemPedidoDTO> itens) {
        if (itens.isEmpty()) {
            throw new RegraNegocioException("Não é possível realizar um pedido sem itens.");
        }
        return itens.stream().map( dto -> {
            ItemPedido itemPedido = new ItemPedido();
            Produto produto = produtosRepository.findById(dto.getProduto()).orElseThrow(() -> new RegraNegocioException("Código de produto inválido."));
            itemPedido.setProduto(produto);
            itemPedido.setQuantidade(dto.getQuantidade());
            itemPedido.setPedido(pedido);
            return itemPedido;
        }).collect(Collectors.toList());

    }
}
