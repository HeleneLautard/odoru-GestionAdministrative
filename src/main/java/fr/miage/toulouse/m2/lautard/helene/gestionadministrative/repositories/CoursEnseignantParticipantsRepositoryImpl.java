package fr.miage.toulouse.m2.lautard.helene.gestionadministrative.repositories;

import fr.miage.toulouse.m2.lautard.helene.gestionadministrative.DTO.*;
import fr.miage.toulouse.m2.lautard.helene.gestionadministrative.exceptions.MauvaisNiveauException;
import fr.miage.toulouse.m2.lautard.helene.gestionadministrative.rest.CoursMSFeignClient;
import fr.miage.toulouse.m2.lautard.helene.gestionadministrative.rest.MembreMSFeignClient;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class CoursEnseignantParticipantsRepositoryImpl implements CoursEnseignantParticipantsRepository{

    @Autowired
    MembreMSFeignClient membreMSFeignClient;

    @Autowired
    CoursMSFeignClient coursMSFeignClient;

    @Override
    public void checkNiveauEnseignant(Long idEnseignant, int niveauCours) throws MauvaisNiveauException {
        Enseignant enseignant = this.membreMSFeignClient.getEnseignant(idEnseignant);
        if(enseignant.getNiveau()<niveauCours){
            throw new MauvaisNiveauException("L'enseignant n'a pas le niveau requis pour enseigner ce cours");
        }
    }


    @Override
    public CoursEnseignantParticipants ajouterParticipants(int niveau, CoursEnseignant cours) {
        List<Adherent> listeParticipantsBase = this.membreMSFeignClient.getParticipantsNiveau(niveau);
        ArrayList<Participant> participantList = new ArrayList<>();
        for(Adherent ad: listeParticipantsBase){
            Participant p = new Participant(ad.getId());
            participantList.add(p);
        }
        CoursEnseignantParticipants coursResultat = new CoursEnseignantParticipants(
                cours.getNumCours(),
                cours.getTitre(),
                cours.getNiveau(),
                cours.getDate(),
                cours.getLieu(),
                cours.getDuree(),
                cours.getIdEnseignant(),
                participantList
                );
        return coursResultat;
    }

    @Override
    public CoursEnseignantParticipants participerCours(CoursEnseignantParticipants cours, Long idParticipant) {
        return null;
    }

    @Override
    public CoursEnseignantParticipants creerCours(CoursEnseignant cours) throws MauvaisNiveauException {
        Long idEnseignant = cours.getIdEnseignant();
        int niveauCours = cours.getNiveau();
        checkNiveauEnseignant(idEnseignant, niveauCours);
        // Ajouter les participants
        CoursEnseignantParticipants coursFinal = ajouterParticipants(niveauCours, cours);
        // Appel REST sur MS-gestion-cours pour ajouter le cours avec les bonnes informations
        this.coursMSFeignClient.creerCours(coursFinal);
        return null;
    }
}
