package io.github.fernandobello.domain.repository;

import io.github.fernandobello.domain.entity.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Pedidos extends JpaRepository<Pedido, Integer> {
}
