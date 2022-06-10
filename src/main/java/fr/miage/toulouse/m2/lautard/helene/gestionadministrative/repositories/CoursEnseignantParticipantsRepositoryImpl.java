package fr.miage.toulouse.m2.lautard.helene.gestionadministrative.repositories;

import fr.miage.toulouse.m2.lautard.helene.gestionadministrative.DTO.CoursEnseignantParticipants;
import fr.miage.toulouse.m2.lautard.helene.gestionadministrative.DTO.Enseignant;
import fr.miage.toulouse.m2.lautard.helene.gestionadministrative.DTO.Participant;
import fr.miage.toulouse.m2.lautard.helene.gestionadministrative.exceptions.MauvaisNiveauException;
import fr.miage.toulouse.m2.lautard.helene.gestionadministrative.rest.MembreMSFeignClient;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

public class CoursEnseignantParticipantsRepositoryImpl implements CoursEnseignantParticipantsRepository{

    @Autowired
    MembreMSFeignClient membreMSFeignClient;

    @Override
    public void checkNiveauEnseignant(Long idEnseignant, int niveauCours) throws MauvaisNiveauException {
        Enseignant enseignant = this.membreMSFeignClient.getEnseignant(idEnseignant);
        if(enseignant.getNiveau()<niveauCours){
            throw new MauvaisNiveauException("L'enseignant n'a pas le niveau requis pour enseigner ce cours");
        }
    }

    @Override
    public CoursEnseignantParticipants ajouterParticipants(int niveau, CoursEnseignantParticipants cours) {
        List<Participant> listeParticipantsBase = this.membreMSFeignClient.getParticipantsNiveau(niveau);
        List<String> listeIds = listeParticipantsBase
                .stream()
                .map(Participant::getId)
                .collect(Collectors.toList());
        List<Integer> listF = null;
        for(String s : listeIds) listF.add(Integer.valueOf(s));
        cours.setListeParticipants(listeParticipantsBase);
        return cours;
    }

    @Override
    public CoursEnseignantParticipants participerCours(CoursEnseignantParticipants cours, Long idParticipant) {
        return null;
    }
}
