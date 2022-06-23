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

    /**
     * Récupérer un cours avec les informations de l'enseignant et des participants
     * @param id id du cours
     * @return CoursDTO
     */
    @GetMapping(path="/cours/{id}")
    public CoursDTO getCoursParticipantsEnseignant(@PathVariable("id") Long id){
        return this.coursEnseignantParticipantsRepository.getCoursEnseignantParticipants(id);
    }

    /**
     * Création d'un cours avec vérification métier (date et niveau de l'enseignant)
     * @param cours CoursDTO
     * @return CoursDTO
     */
    @PostMapping(path="/cours")
    public CoursDTO creerCours(@RequestBody CoursDTO cours){
        try{
            return this.coursEnseignantParticipantsRepository.creerCours(cours);
        } catch(MauvaisNiveauException ex){
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, ex.getMessage());
        }
    }

    /**
     * Enregistrer la participation d'une personne à un cours
     * @param idCours numéro du cours
     * @param idMembre id du membre
     * @return Cours mis à jour
     */
    @PutMapping(path="/cours/{idCours}/participer/{idMembre}")
    public CoursDTO participerCours(@PathVariable("idCours") Long idCours, @PathVariable("idMembre") Long idMembre){
        return this.coursEnseignantParticipantsRepository.participerCours(idCours, idMembre);
    }

}
