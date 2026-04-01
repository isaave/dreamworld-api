package com.barbie.dreamworld_api.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Entity
@Data // Gera Getters, Setters, ToString...
@AllArgsConstructor // Construtor com todos os campos
@NoArgsConstructor // Construtor vazio para o Hibernate
public class Evento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nomeEvento;

    @ManyToMany(mappedBy = "eventos")
    private List<Barbie> barbies; // O Java agora vai achar a Barbie certa na mesma pasta!
}