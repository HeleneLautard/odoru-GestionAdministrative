package fr.miage.toulouse.m2.lautard.helene.gestionadministrative.repositories;

import fr.miage.toulouse.m2.lautard.helene.gestionadministrative.DTO.CoursDTO;
import fr.miage.toulouse.m2.lautard.helene.gestionadministrative.DTO.CoursEnseignant;
import fr.miage.toulouse.m2.lautard.helene.gestionadministrative.DTO.CoursEnseignantParticipants;
import fr.miage.toulouse.m2.lautard.helene.gestionadministrative.exceptions.MauvaisNiveauException;
import org.springframework.stereotype.Repository;

@Repository
public interface CoursEnseignantParticipantsRepository {

    // Vérifier que le niveau de l'enseignant lors de la création d'un cours est bien supérieur ou égal au cours que l'on crée.

    /**
     * Vérifier que l'enseignant désigné peut enseigner un cours d'un niveau donné
     * @param idEnseignant identifiant de l'enseignant
     * @param niveauCours niveau du cours souhaité
     * @throws MauvaisNiveauException
     */
    public void checkNiveauEnseignant(Long idEnseignant, int niveauCours) throws MauvaisNiveauException;

    /**
     * Ajouter tous les adhérents du niveau donné comme participants à un cours
     * @param niveau niveau
     * @param cours cours que l'on souhaite créer
     * @return cours
     */
    public CoursDTO ajouterParticipants(int niveau, CoursDTO cours);

    /**
     * Enregistrer la participation d'un élève à un cours
     * @param idCours
     * @param idParticipant
     * @return
     */
    public CoursDTO participerCours(Long idCours, Long idParticipant);

    /**
     * Créer un nouveau cours
     * @param cours cours que l'on souhaite créer
     * @return
     */
    public CoursDTO creerCours(CoursDTO cours) throws MauvaisNiveauException;

    /**
     * Récupérer un cours avec les informations de l'enserignant et des participations
     * @param idCours identifiant du cours
     * @return
     */
    public CoursDTO getCoursEnseignantParticipants(Long idCours);
}
