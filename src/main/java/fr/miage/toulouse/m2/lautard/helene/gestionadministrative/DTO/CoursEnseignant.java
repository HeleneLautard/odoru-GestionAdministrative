package fr.miage.toulouse.m2.lautard.helene.gestionadministrative.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CoursEnseignant {
    private String numCours;
    private String titre;
    private int niveau;
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd/MM/yyyy")
    private Date date;
    private String lieu;
    private long duree;
    private Long idEnseignant;
}
