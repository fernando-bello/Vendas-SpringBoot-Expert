package io.github.fernandobello.domain.repository;

import io.github.fernandobello.domain.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface Clientes extends JpaRepository<Cliente, Integer> {


    List<Cliente> findByNomeLike(String nome);

    List<Cliente> findByNomeOrIdOrderById(String nome, Integer id);

    Boolean existsByNome(String nome);
}
