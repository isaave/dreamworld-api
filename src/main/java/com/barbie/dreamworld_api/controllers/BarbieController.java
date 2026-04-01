package com.barbie.dreamworld_api.controllers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.barbie.dreamworld_api.model.Barbie;
import com.barbie.dreamworld_api.repository.BarbieRepository;
import com.barbie.dreamworld_api.exceptions.BarbieNotFoundException;

@RestController
public class BarbieController {

    private final BarbieRepository repository;

    BarbieController(BarbieRepository repository) {
        this.repository = repository;
    }

    // Listar todas com Links (HATEOAS)
    @GetMapping("/barbies")
    public CollectionModel<EntityModel<Barbie>> all() {

        List<EntityModel<Barbie>> barbies = repository.findAll().stream()
                .map(barbie -> EntityModel.of(barbie,
                        linkTo(methodOn(BarbieController.class).one(barbie.getId())).withSelfRel(),
                        linkTo(methodOn(BarbieController.class).all()).withRel("barbies")))
                .collect(Collectors.toList());

        return CollectionModel.of(barbies, linkTo(methodOn(BarbieController.class).all()).withSelfRel());
    }

    // Cadastrar nova Barbie
    @PostMapping("/barbies")
    public Barbie newBarbie(@RequestBody Barbie newBarbie) {
        return repository.save(newBarbie);
    }

    // Buscar uma única (com exceção personalizada)
    @GetMapping("/barbies/{id}")
    public EntityModel<Barbie> one(@PathVariable Long id) {

        Barbie barbie = repository.findById(id)
                .orElseThrow(() -> new BarbieNotFoundException(id));

        return EntityModel.of(barbie,
                linkTo(methodOn(BarbieController.class).one(id)).withSelfRel(),
                linkTo(methodOn(BarbieController.class).all()).withRel("barbies"));
    }

    // Atualizar (Replace)
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

    @DeleteMapping("/barbies/{id}")
    public void deleteBarbie(@PathVariable Long id) {
        repository.deleteById(id);
    }
}