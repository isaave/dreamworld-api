package com.barbie.dreamworld_api.repository;

// O IMPORT CORRETO É ESTE (apontando para model):
import com.barbie.dreamworld_api.model.Barbie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BarbieRepository extends JpaRepository<Barbie, Long> {
    // Aqui não precisa de código, o JpaRepository já faz tudo!
}