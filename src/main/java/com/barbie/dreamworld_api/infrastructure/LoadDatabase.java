package com.barbie.dreamworld_api.infrastructure;

import com.barbie.dreamworld_api.model.Barbie;
import com.barbie.dreamworld_api.repository.BarbieRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoadDatabase {

    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(BarbieRepository repository) {
        return args -> {
            // Criando a primeira Barbie
            Barbie b1 = new Barbie();
            b1.setNome("Barbie Sereia");
            b1.setColecao("Dreamtopia");
            log.info("Preloading " + repository.save(b1));

            // Criando a segunda Barbie
            Barbie b2 = new Barbie();
            b2.setNome("Barbie Astronauta");
            b2.setColecao("Carreiras");
            log.info("Preloading " + repository.save(b2));
        };
    }
}