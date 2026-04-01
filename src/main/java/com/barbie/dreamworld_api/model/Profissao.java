package com.barbie.dreamworld_api.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Entity
@Data // Gera Getters, Setters, ToString e Equals/HashCode automaticamente
@AllArgsConstructor // Gera o construtor com todos os campos
@NoArgsConstructor // Gera o construtor vazio (essencial para o JPA)
public class Profissao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nomeProfissao;

    @ManyToOne
    @JoinColumn(name = "barbie_id")
    private Barbie barbie; // O Java agora usa a Barbie da pasta model (sem import de infrastructure)

    @OneToMany(mappedBy = "profissao")
    private List<Acessorio> acessorios;
}