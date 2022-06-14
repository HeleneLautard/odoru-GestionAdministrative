package fr.miage.toulouse.m2.lautard.helene.gestionadministrative.rest;

import fr.miage.toulouse.m2.lautard.helene.gestionadministrative.DTO.CoursEnseignantParticipants;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "gestionCours")
public interface CoursMSFeignClient {

    @RequestMapping(method = RequestMethod.POST,
            value="/cours",
            produces= "application/json",
            consumes = "application/json")
    CoursEnseignantParticipants creerCours(CoursEnseignantParticipants coursFinal);

    @RequestMapping(method = RequestMethod.GET,
            value="/cours/{id}",
            produces= "application/json"
    )
    CoursEnseignantParticipants getCours(@PathVariable("id") Long idCours);
}
