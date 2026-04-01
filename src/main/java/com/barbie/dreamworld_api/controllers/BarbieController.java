package com.barbie.dreamworld_api.controllers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.barbie.dreamworld_api.model.Barbie;
import com.barbie.dreamworld_api.repository.BarbieRepository;
import com.barbie.dreamworld_api.exceptions.BarbieNotFoundException;

// Importações para Documentação (Swagger)
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@Tag(name = "Barbie", description = "Endpoints para gerenciar a coleção de Barbies")
public class BarbieController {

    private final BarbieRepository repository;

    BarbieController(BarbieRepository repository) {
        this.repository = repository;
    }

    @Operation(summary = "Listar todas as Barbies", description = "Retorna uma lista com HATEOAS")
    @GetMapping("/barbies")
    public CollectionModel<EntityModel<Barbie>> all() {
        List<EntityModel<Barbie>> barbies = repository.findAll().stream()
                .map(barbie -> EntityModel.of(barbie,
                        linkTo(methodOn(BarbieController.class).one(barbie.getId())).withSelfRel(),
                        linkTo(methodOn(BarbieController.class).all()).withRel("barbies")))
                .collect(Collectors.toList());

        return CollectionModel.of(barbies, linkTo(methodOn(BarbieController.class).all()).withSelfRel());
    }

    @Operation(summary = "Cadastrar nova Barbie", description = "Cria um novo registro no banco de dados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Barbie criada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro na requisição (JSON inválido)")
    })
    @PostMapping("/barbies")
    @ResponseStatus(HttpStatus.CREATED) // Convenção: 201 para novos recursos
    public Barbie newBarbie(@RequestBody Barbie newBarbie) {
        return repository.save(newBarbie);
    }

    @Operation(summary = "Buscar por ID", description = "Retorna os detalhes de uma única Barbie")
    @GetMapping("/barbies/{id}")
    public EntityModel<Barbie> one(@PathVariable Long id) {
        Barbie barbie = repository.findById(id)
                .orElseThrow(() -> new BarbieNotFoundException(id));

        return EntityModel.of(barbie,
                linkTo(methodOn(BarbieController.class).one(id)).withSelfRel(),
                linkTo(methodOn(BarbieController.class).all()).withRel("barbies"));
    }

    @Operation(summary = "Atualizar Barbie", description = "Substitui os dados de uma Barbie existente")
    @PutMapping("/barbies/{id}")
    public Barbie replaceBarbie(@RequestBody Barbie newBarbie, @PathVariable Long id) {
        return repository.findById(id)
                .map(barbie -> {
                    barbie.setNome(newBarbie.getNome());
                    barbie.setColecao(newBarbie.getColecao());
                    return repository.save(barbie);
                })
                .orElseGet(() -> {
                    newBarbie.setId(id);
                    return repository.save(newBarbie);
                });
    }

    @Operation(summary = "Deletar Barbie", description = "Remove permanentemente uma Barbie pelo ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Barbie deletada com sucesso")
    })
    @DeleteMapping("/barbies/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT) // Convenção: 204 quando não há conteúdo para retornar
    public void deleteBarbie(@PathVariable Long id) {
        if (!repository.existsById(id)) {
            throw new BarbieNotFoundException(id);
        }
        repository.deleteById(id);
    }
}