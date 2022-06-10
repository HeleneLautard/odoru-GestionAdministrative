package fr.miage.toulouse.m2.lautard.helene.gestionadministrative;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication

@EnableDiscoveryClient
@EnableFeignClients
public class GestionAdministrativeApplication {

    public static void main(String[] args) {
        SpringApplication.run(GestionAdministrativeApplication.class, args);
    }

}
