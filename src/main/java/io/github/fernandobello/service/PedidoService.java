package io.github.fernandobello.service;

import io.github.fernandobello.domain.entity.Pedido;
import io.github.fernandobello.domain.enums.StatusPedido;
import io.github.fernandobello.rest.dto.PedidoDTO;

import java.util.Optional;

public interface PedidoService {

    Pedido salvar(PedidoDTO dto);

    Optional<Pedido> obterPedidoCompleto(Integer id);

    void atualizaStatus(Integer id, StatusPedido statusPedido);
}
