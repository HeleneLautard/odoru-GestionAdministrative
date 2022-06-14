package fr.miage.toulouse.m2.lautard.helene.gestionadministrative.DTO;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Participant {

    private Long numMembre;
    private String NomPrenom;
    private boolean presence = false;

    public Participant(Long id, String nomPrenom) {
        this.numMembre=id;
        this.NomPrenom = nomPrenom;
    }
}
