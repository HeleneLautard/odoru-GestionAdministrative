package fr.miage.toulouse.m2.lautard.helene.gestionadministrative;

import fr.miage.toulouse.m2.lautard.helene.gestionadministrative.repositories.CoursEnseignantParticipantsRepository;
import fr.miage.toulouse.m2.lautard.helene.gestionadministrative.repositories.CoursEnseignantParticipantsRepositoryImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

@SpringBootApplication

@EnableDiscoveryClient
@EnableFeignClients
public class GestionAdministrativeApplication {

    @Bean
    public CoursEnseignantParticipantsRepository coursEnseignantParticipantsRepository(){
        return new CoursEnseignantParticipantsRepositoryImpl();
    }

    public static void main(String[] args) {
        SpringApplication.run(GestionAdministrativeApplication.class, args);
    }

}
