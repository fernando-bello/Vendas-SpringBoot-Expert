package io.github.fernandobello.domain.repository;

import io.github.fernandobello.domain.entity.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Produtos extends JpaRepository<Produto, Integer> {
}
