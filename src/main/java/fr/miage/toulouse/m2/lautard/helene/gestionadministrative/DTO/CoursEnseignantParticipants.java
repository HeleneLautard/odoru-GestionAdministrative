package fr.miage.toulouse.m2.lautard.helene.gestionadministrative.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CoursEnseignantParticipants extends CoursEnseignant {
    private List<Participant> listeParticipants;

    public CoursEnseignantParticipants(String numCours, String titre, int niveau, Date date, String lieu, long duree, Long idEnseignant, List<Participant> listeParticipants) {
        super(numCours, titre, niveau, date, lieu, duree, idEnseignant);
        this.listeParticipants = listeParticipants;
    }
}
