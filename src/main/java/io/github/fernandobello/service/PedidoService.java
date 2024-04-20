package io.github.fernandobello.service;

import io.github.fernandobello.domain.entity.Pedido;
import io.github.fernandobello.rest.dto.PedidoDTO;

public interface PedidoService {
    Pedido salvar(PedidoDTO dto);
}
