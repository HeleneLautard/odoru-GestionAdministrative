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
public class CoursEnseignantParticipants {
    private String id;
    private String titre;
    private String lieu;
    private Date date;
    private int niveau;
    private long duree;
    private Enseignant enseignant;
    private List<Participant> listeParticipants;
}
