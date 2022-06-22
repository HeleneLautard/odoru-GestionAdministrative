package fr.miage.toulouse.m2.lautard.helene.gestionadministrative.rest;

import fr.miage.toulouse.m2.lautard.helene.gestionadministrative.DTO.CoursDTO;
import fr.miage.toulouse.m2.lautard.helene.gestionadministrative.DTO.CoursEnseignant;
import fr.miage.toulouse.m2.lautard.helene.gestionadministrative.DTO.CoursEnseignantParticipants;
import fr.miage.toulouse.m2.lautard.helene.gestionadministrative.exceptions.MauvaisNiveauException;
import fr.miage.toulouse.m2.lautard.helene.gestionadministrative.repositories.CoursEnseignantParticipantsRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping(path = "/admin")
public class AdministrativeRestController {

    @Autowired
    CoursEnseignantParticipantsRepository coursEnseignantParticipantsRepository;
    ModelMapper modelMapper = new ModelMapper();

    @GetMapping(path="/cours/{id}")
    public CoursDTO getCoursParticipantsEnseignant(@PathVariable("id") Long id){
        return this.coursEnseignantParticipantsRepository.getCoursEnseignantParticipants(id);
    }

    @PostMapping(path="/cours")
    public CoursDTO creerCours(@RequestBody CoursDTO cours){
        try{
            return this.coursEnseignantParticipantsRepository.creerCours(cours);
        } catch(MauvaisNiveauException ex){
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, ex.getMessage());
        }
    }

}
