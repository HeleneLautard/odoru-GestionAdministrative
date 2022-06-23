package fr.miage.toulouse.m2.lautard.helene.gestionadministrative.rest;

import fr.miage.toulouse.m2.lautard.helene.gestionadministrative.DTO.CoursDTO;
import fr.miage.toulouse.m2.lautard.helene.gestionadministrative.DTO.CoursEnseignantParticipants;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.awt.*;

@FeignClient(value = "gestionCours")
public interface CoursMSFeignClient {

    @RequestMapping(method = RequestMethod.POST,
            value="/cours",
            produces= "application/json",
            consumes = "application/json" )
    CoursDTO creerCours(@RequestBody CoursDTO coursFinal);

    @RequestMapping(method = RequestMethod.PUT,
            value="/cours",
            produces= "application/json",
            consumes = "application/json" )
    CoursDTO updateCours(@RequestBody CoursDTO coursFinal);

    @RequestMapping(method = RequestMethod.GET,
            value="/cours/{id}",
            produces= "application/json"
    )
    CoursDTO getCours(@PathVariable("id") Long idCours);
}
