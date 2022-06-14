package fr.miage.toulouse.m2.lautard.helene.gestionadministrative.DTO;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Adherent {

    private Long numMembre;
    private String nom;
    private String prenom;
    private int niveau;
    private String aptitudeMedicale;
    private String statutInscription;
    private String statutPaiement;
}
