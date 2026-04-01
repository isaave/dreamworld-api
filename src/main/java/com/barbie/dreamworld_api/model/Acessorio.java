package com.barbie.dreamworld_api.model;

import jakarta.persistence.*;

@Entity
public class Acessorio {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nomeItem;

    @ManyToOne
    @JoinColumn(name = "profissao_id")
    private Profissao profissao;

    public Acessorio() {}
    // Gere Getters e Setters
}