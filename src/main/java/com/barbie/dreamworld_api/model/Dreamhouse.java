package com.barbie.dreamworld_api.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data // Gera Getters, Setters, ToString...
@AllArgsConstructor // Construtor com todos os campos
@NoArgsConstructor // Construtor vazio (JPA exige)
public class Dreamhouse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String nomeCasa;

    @OneToOne(mappedBy = "dreamhouse")
    private Barbie barbie; // O Java agora usa a Barbie da pasta model automaticamente!
}