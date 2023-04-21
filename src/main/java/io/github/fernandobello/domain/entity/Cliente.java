package io.github.fernandobello.domain.entity;

import javax.persistence.*;

@Entity
@Table (name = "cliente") //Não é obrigatorio colocar essa annotation, a menos que o nome da classe seja diferente do nome da tabela
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id") //Não é obrigatorio colocar essa annotation, a menos que o nome da propriedade seja diferente do nome da propriedade na tabela
    private Integer id;

    @Column(name = "nome", length = 100)
    private String nome;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Cliente() {
    }

    public Cliente(String nome) {
        this.nome = nome;
    }

    public Cliente(Integer id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                '}';
    }
}
