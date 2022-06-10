package fr.miage.toulouse.m2.lautard.helene.gestionadministrative.repositories;

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

    // Pour la création d'un cours, on souhaite inscrire tous les membres du même niveau que le cours
    public CoursEnseignantParticipants ajouterParticipants(int niveau, CoursEnseignantParticipants cours);

    // Lorsqu'un élève badge pour un cours, on le note présent pour le cours donné.
    public CoursEnseignantParticipants participerCours(CoursEnseignantParticipants cours, Long idParticipant);
}
