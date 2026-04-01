package com.barbie.dreamworld_api.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Entity
@Data

public class Barbie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String colecao;

    // Relacionamento 1:1 com a Casa (Resolve o erro do seu último log!)
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "dreamhouse_id", referencedColumnName = "id")
    private Dreamhouse dreamhouse;

    // Relacionamento N:N com Eventos
    @ManyToMany
    @JoinTable(
            name = "barbie_evento",
            joinColumns = @JoinColumn(name = "barbie_id"),
            inverseJoinColumns = @JoinColumn(name = "evento_id")
    )
    private List<Evento> eventos;

    // Relacionamento 1:N com Profissões
    @OneToMany(mappedBy = "barbie", cascade = CascadeType.ALL)
    private List<Profissao> profissoes;


    // 1. Construtor Vazio (Obrigatório para o JPA/Hibernate)
    public Barbie() {
    }

    // 2. Construtor com Nome e Coleção (Para usar no LoadDatabase)
    public Barbie(String nome, String colecao) {
        this.nome = nome;
        this.colecao = colecao;
    }

    // 3. Construtor Completo (Caso precise passar ID e as relações)
    public Barbie(Long id, String nome, String colecao, Dreamhouse dreamhouse, List<Evento> eventos, List<Profissao> profissoes) {
        this.id = id;
        this.nome = nome;
        this.colecao = colecao;
        this.dreamhouse = dreamhouse;
        this.eventos = eventos;
        this.profissoes = profissoes;
    }

}