package fr.miage.toulouse.m2.lautard.helene.gestionadministrative.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Participant {

    private Long numMembre;
    private boolean presence = false;

    public Participant(Long id) {
        this.numMembre=id;
    }
}
