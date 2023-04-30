package io.github.fernandobello.domain.repository;

import io.github.fernandobello.domain.entity.Cliente;
import io.github.fernandobello.domain.entity.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Set;

public interface PedidosRepository extends JpaRepository<Pedido, Integer> {

    List<Pedido> findByCliente(Cliente cliente);
}
