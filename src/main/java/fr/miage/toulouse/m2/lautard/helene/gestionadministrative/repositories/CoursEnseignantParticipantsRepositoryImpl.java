package fr.miage.toulouse.m2.lautard.helene.gestionadministrative.repositories;

import fr.miage.toulouse.m2.lautard.helene.gestionadministrative.DTO.*;
import fr.miage.toulouse.m2.lautard.helene.gestionadministrative.exceptions.MauvaisNiveauException;
import fr.miage.toulouse.m2.lautard.helene.gestionadministrative.rest.CoursMSFeignClient;
import fr.miage.toulouse.m2.lautard.helene.gestionadministrative.rest.MembreMSFeignClient;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CoursEnseignantParticipantsRepositoryImpl implements CoursEnseignantParticipantsRepository{

    @Autowired
    MembreMSFeignClient membreMSFeignClient;

    @Autowired
    CoursMSFeignClient coursMSFeignClient;

    ModelMapper modelMapper = new ModelMapper();

    @Override
    public void checkNiveauEnseignant(Long idEnseignant, int niveauCours) throws MauvaisNiveauException {
        Enseignant enseignant = this.membreMSFeignClient.getEnseignant(idEnseignant);
        if(enseignant.getNiveau()<niveauCours){
            throw new MauvaisNiveauException("L'enseignant n'a pas le niveau requis pour enseigner ce cours");
        }
    }


    @Override
    public CoursDTO ajouterParticipants(int niveau, CoursDTO cours) {
        List<Adherent> listeParticipantsBase = this.membreMSFeignClient.getParticipantsNiveau(niveau);
        ArrayList<Participant> participantList = new ArrayList<>();
        for(Adherent ad: listeParticipantsBase){
            Participant p = new Participant(ad.getNumMembre(), ad.getPrenom()+" "+ad.getNom());
            participantList.add(p);
        }
        cours.setListeParticipants(participantList);
        return cours;
    }

    @Override
    public CoursDTO participerCours(Long idCours, Long idParticipant) {
        CoursDTO cours = this.getCoursEnseignantParticipants(idCours);
        List<Participant> liste = cours.getListeParticipants();
        for(Participant p: liste){
            if(Objects.equals(p.getNumMembre(), idParticipant)){
                p.setPresence(true);
            }
        }
        cours.setListeParticipants(liste);
        //Envoyer le nouveau cours sur ms-cours pour modification en base
        this.coursMSFeignClient.updateCours(cours);
        return cours;
    }

    @Override
    public CoursDTO creerCours(CoursDTO cours) throws MauvaisNiveauException {
        Long idEnseignant = cours.getIdEnseignant();
        int niveauCours = cours.getNiveau();
        checkNiveauEnseignant(idEnseignant, niveauCours);
        // Ajouter les participants
        CoursDTO coursFinal = ajouterParticipants(niveauCours, cours);
        System.out.println(coursFinal.toString());
        // Appel REST sur MS-gestion-cours pour ajouter le cours avec les bonnes informations
        return this.coursMSFeignClient.creerCours(coursFinal);
    }

    @Override
    public CoursDTO getCoursEnseignantParticipants(Long idCours) {
        CoursDTO cours = this.coursMSFeignClient.getCours(idCours);
        return ajouterParticipants(cours.getNiveau(), cours);
    }
}
