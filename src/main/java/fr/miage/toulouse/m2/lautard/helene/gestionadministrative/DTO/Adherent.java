package fr.miage.toulouse.m2.lautard.helene.gestionadministrative.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Adherent {

    private String id;
    private String nom;
    private String prenom;
    private int niveau;
    private String aptitudeMedicale;
    private String statutInscription;
    private String statutPaiement;
}
