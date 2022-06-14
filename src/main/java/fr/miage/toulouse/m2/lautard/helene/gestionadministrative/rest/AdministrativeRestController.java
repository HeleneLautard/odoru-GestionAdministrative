package fr.miage.toulouse.m2.lautard.helene.gestionadministrative.rest;

import fr.miage.toulouse.m2.lautard.helene.gestionadministrative.DTO.CoursEnseignantParticipants;
import fr.miage.toulouse.m2.lautard.helene.gestionadministrative.exceptions.MauvaisNiveauException;
import fr.miage.toulouse.m2.lautard.helene.gestionadministrative.repositories.CoursEnseignantParticipantsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping(path = "/admin")
public class AdministrativeRestController {

    @Autowired
    CoursEnseignantParticipantsRepository coursEnseignantParticipantsRepository;

    @GetMapping(path="/cours/{id}")
    public CoursEnseignantParticipants getCoursParticipantsEnseignant(@PathVariable("id") Long id){
        return this.coursEnseignantParticipantsRepository.getCoursEnseignantParticipants(id);
    }

    @PostMapping(path="/cours")
    public CoursEnseignantParticipants creerCours(@RequestBody CoursEnseignantParticipants cours){
        try{
            return this.coursEnseignantParticipantsRepository.creerCours(cours);
        } catch(MauvaisNiveauException ex){
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, ex.getMessage());
        }
    }

}
